package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.dto.FileDto;

import java.util.Optional;

public interface FileService {

	Optional<FileDto> getByFilename(String filename);
	void upload(FileDto fileDto);
}
