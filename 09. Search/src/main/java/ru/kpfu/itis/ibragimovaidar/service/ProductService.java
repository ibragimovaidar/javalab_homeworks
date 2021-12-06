package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	List<ProductDto> findAll();
	Optional<ProductDto> findById(Long id);
	void save(ProductDto productDto);
	List<ProductDto> search(String query);
}
