package ru.kpfu.itis.ibragimovaidar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDTO;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.ProductsRepository;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductsRepository productsRepository;

	@Override
	public List<ProductDTO> findAll() {
		return productsRepository.findAll().stream()
				.map(ProductDTO::from)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDTO> findById(Long id) {
		return productsRepository.findById(id).map(ProductDTO::from);
	}

	@Override
	public void addProduct(ProductDTO productDTO) {
		Product product = Product.builder()
				.name(productDTO.getName())
				.price(productDTO.getPrice())
				.availableAmount(productDTO.getAvailableAmount())
				.description(productDTO.getDescription())
				.build();
		productsRepository.save(product);
	}
}
