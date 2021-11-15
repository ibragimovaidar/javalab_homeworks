package ru.kpfu.itis.ibragimovaidar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDTO;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.ProductRepository;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream()
				.map(ProductDTO::from)
				.collect(Collectors.toList());
	}

	@Override
	public void save(ProductDTO productDTO) {
		Product product = Product.builder()
				.name(productDTO.getName())
				.price(productDTO.getPrice())
				.description(productDTO.getDescription())
				.availableAmount(productDTO.getAvailableAmount())
				.build();
		productRepository.save(product);
	}
}
