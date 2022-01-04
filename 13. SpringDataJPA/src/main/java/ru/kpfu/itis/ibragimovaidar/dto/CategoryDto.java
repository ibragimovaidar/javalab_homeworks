package ru.kpfu.itis.ibragimovaidar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.ibragimovaidar.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

	private Long id;
	private String name;
	private String description;
	private Long parentId;

	public static CategoryDto from(Category category){
		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.description(category.getDescription())
				.parentId(Optional.ofNullable(category.getParent()).orElse(new Category()).getId())
				.build();
	}

	public static List<CategoryDto> from(List<Category> category){
		return category.stream()
				.map(CategoryDto::from)
				.collect(Collectors.toList());
	}
}
