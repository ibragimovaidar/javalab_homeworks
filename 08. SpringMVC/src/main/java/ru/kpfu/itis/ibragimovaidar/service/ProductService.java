package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	List<ProductDTO> findAll();
	Optional<ProductDTO> findById(Long id);
	void addProduct(ProductDTO productDTO);
}
