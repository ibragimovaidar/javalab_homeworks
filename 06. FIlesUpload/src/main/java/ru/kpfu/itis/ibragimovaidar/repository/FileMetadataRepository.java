package ru.kpfu.itis.ibragimovaidar.repository;

import ru.kpfu.itis.ibragimovaidar.model.FileMetadata;

import java.util.Optional;

public interface FileMetadataRepository {

	Optional<FileMetadata> findByFilename(String filename);
	FileMetadata save(FileMetadata fileMetadata);
}
