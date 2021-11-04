package ru.kpfu.itis.ibragimovaidar.dto;

import lombok.*;
import ru.kpfu.itis.ibragimovaidar.model.FileMetadata;

import java.io.InputStream;

@AllArgsConstructor
@Getter
@Builder
public class FileDto {

	private final String originalFilename;
	private final String mimeType;
	private final Integer size;
	private final InputStream fileInputStream;
}
