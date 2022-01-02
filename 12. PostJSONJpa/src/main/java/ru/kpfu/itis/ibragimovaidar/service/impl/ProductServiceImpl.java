package ru.kpfu.itis.ibragimovaidar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDTO;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.ProductRepository;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream()
				.map(ProductDTO::from)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDTO> findById(Long id) {
		return productRepository.findById(id).map(ProductDTO::from);
	}

	@Override
	public void save(ProductDTO productDTO) {
		var product = Product.builder()
				.name(productDTO.getName())
				.description(productDTO.getDescription())
				.price(productDTO.getPrice())
				.availableAmount(productDTO.getAvailableAmount())
				.build();
		productRepository.save(product);
	}
}
