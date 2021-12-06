package ru.kpfu.itis.ibragimovaidar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.ProductRepository;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public List<ProductDto> findAll() {
		return productRepository.findAll().stream()
				.map(ProductDto::from)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDto> findById(Long id) {
		return productRepository.findById(id).map(ProductDto::from);
	}

	@Override
	public void save(ProductDto productDto) {
		Product product = Product.builder()
				.name(productDto.getName())
				.description(productDto.getDescription())
				.price(productDto.getPrice())
				.amount(productDto.getAmount())
				.build();
		productRepository.save(product);
	}

	@Override
	public List<ProductDto> search(String query) {
		return productRepository.search(query).stream()
				.map(ProductDto::from)
				.collect(Collectors.toList());
	}
}
