package ru.kpfu.itis.ibragimovaidar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.dto.FileDto;
import ru.kpfu.itis.ibragimovaidar.model.FileMetadata;
import ru.kpfu.itis.ibragimovaidar.repository.FileMetadataRepository;
import ru.kpfu.itis.ibragimovaidar.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

	private final FileMetadataRepository fileMetadataRepository;

	private final String storagePath;

	@Autowired
	public FileServiceImpl(FileMetadataRepository fileMetadataRepository, @Value("${storage.path}") String storagePath) {
		this.fileMetadataRepository = fileMetadataRepository;
		this.storagePath = storagePath;
	}

	@Override
	public Optional<FileDto> getByFilename(String filename) {
		Optional<FileMetadata> fileMetadataOptional = fileMetadataRepository.findByFilename(filename);
		if (!fileMetadataOptional.isPresent()){
			return Optional.empty();
		}
		FileMetadata fileMetadata = fileMetadataOptional.get();
		InputStream fileInputStream;
		try {
			fileInputStream = Files.newInputStream(Paths.get(storagePath, fileMetadata.getFilename()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return Optional.of(
				FileDto.builder()
					.originalFilename(fileMetadata.getOriginalFilename())
					.mimeType(fileMetadata.getMimeType())
					.size(fileMetadata.getSize())
					.fileInputStream(fileInputStream)
					.build()
		);
	}

	@Override
	public void upload(FileDto fileFormDto) {
		String filename = UUID.randomUUID() + getFileExtension(fileFormDto.getOriginalFilename());
		try {
			Files.copy(fileFormDto.getFileInputStream(), Paths.get(storagePath, filename));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		FileMetadata fileMetadata = FileMetadata.builder()
				.originalFilename(fileFormDto.getOriginalFilename())
				.filename(filename)
				.mimeType(fileFormDto.getMimeType())
				.size(fileFormDto.getSize())
				.build();
		fileMetadataRepository.save(fileMetadata);
	}

	private static String getFileExtension(String filename){
		if (filename.lastIndexOf(".") == -1){
			return "";
		}
		return filename.substring(filename.lastIndexOf("."));
	}
}
