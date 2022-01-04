package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.dto.CategoryDto;

import java.util.Optional;

public interface CategoryService {

	Optional<CategoryDto> findById(Long id);
	CategoryDto addCategory(CategoryDto categoryDto);
}
