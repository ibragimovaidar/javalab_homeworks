package ru.kpfu.itis.ibragimovaidar.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.ibragimovaidar.dto.CategoryDto;
import ru.kpfu.itis.ibragimovaidar.model.Category;
import ru.kpfu.itis.ibragimovaidar.repository.CategoryRepository;
import ru.kpfu.itis.ibragimovaidar.service.CategoryService;

import java.util.Optional;

import static ru.kpfu.itis.ibragimovaidar.dto.CategoryDto.from;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public Optional<CategoryDto> findById(Long id) {
		return categoryRepository.findById(id).map(CategoryDto::from);
	}

	@Transactional
	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category parentCategory = null;
		if (categoryDto.getParentId() != null){
			parentCategory = categoryRepository.findById(categoryDto.getParentId()).orElse(null);
		}
		var newCategory = Category.builder()
				.name(categoryDto.getName())
				.description(categoryDto.getDescription())
				.parent(parentCategory)
				.build();
		return from(categoryRepository.save(newCategory));
	}
}
