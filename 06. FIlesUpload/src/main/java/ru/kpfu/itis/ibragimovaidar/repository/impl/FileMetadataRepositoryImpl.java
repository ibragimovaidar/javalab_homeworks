package ru.kpfu.itis.ibragimovaidar.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.model.FileMetadata;
import ru.kpfu.itis.ibragimovaidar.repository.FileMetadataRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class FileMetadataRepositoryImpl implements FileMetadataRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private final RowMapper<FileMetadata> fileMetadataRowMapper = (ResultSet rs, int rowNum) -> {
		int id = rs.getInt("id");
		String filename = rs.getString("filename");
		String originalFilename = rs.getString("original_filename");
		String mimeType = rs.getString("mime_type");
		int size = rs.getInt("size");
		return new FileMetadata(id, filename, originalFilename, size, mimeType);
	};

	@Autowired
	public FileMetadataRepositoryImpl(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	//language=SQL
	private static final String SQL_FIND_BY_FILENAME = "SELECT id, filename, original_filename, mime_type, size " +
			"FROM file_metadata WHERE filename = :filename";

	@Override
	public Optional<FileMetadata> findByFilename(String filename) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("filename", filename);
		return Optional.ofNullable(
				jdbcTemplate.queryForObject(SQL_FIND_BY_FILENAME, paramSource, fileMetadataRowMapper)
		);
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO file_metadata(filename, original_filename, mime_type, size) " +
			"VALUES (:filename, :originalFilename, :mimeType, :size)";

	@Override
	public FileMetadata save(FileMetadata fileMetadata) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("filename", fileMetadata.getFilename());
		paramSource.addValue("originalFilename", fileMetadata.getOriginalFilename());
		paramSource.addValue("mimeType", fileMetadata.getMimeType());
		paramSource.addValue("size", fileMetadata.getSize());

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		int affectedRows = jdbcTemplate.update(SQL_SAVE, paramSource, keyHolder, new String[]{"id"});
		if (affectedRows != 1){
			throw new RuntimeException("Save error");
		}
		fileMetadata.setId(keyHolder.getKeyAs(Integer.class));
		return fileMetadata;
	}
}
