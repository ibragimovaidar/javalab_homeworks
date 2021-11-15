package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.dto.ProductDTO;

import java.util.List;

public interface ProductService {

	List<ProductDTO> findAll();
	void save(ProductDTO productDTO);
}
