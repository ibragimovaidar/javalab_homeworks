package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	Optional<ProductDto> findById(Long id);
	ProductDto save(Long categoryId, ProductDto productDto);
	List<ProductDto> searchByName(String name);
	List<ProductDto> searchByCategoryName(String name);
}
