package ru.kpfu.itis.ibragimovaidar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.CategoryRepository;
import ru.kpfu.itis.ibragimovaidar.repository.ProductRepository;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import java.util.List;
import java.util.Optional;

import static ru.kpfu.itis.ibragimovaidar.dto.ProductDto.from;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public Optional<ProductDto> findById(Long id) {
		return productRepository.findById(id).map(ProductDto::from);
	}

	@Transactional
	@Override
	public ProductDto save(Long categoryId, ProductDto productDto) {
		var category = categoryRepository.findById(categoryId).orElseThrow();
		var newProduct = Product.builder()
				.name(productDto.getName())
				.description(productDto.getDescription())
				.price(productDto.getPrice())
				.category(category)
				.build();
		return from(productRepository.save(newProduct));
	}

	@Override
	public List<ProductDto> searchByName(String name) {
		return from(productRepository.findAllByNameLike("%" + name + "%"));
	}

	@Override
	public List<ProductDto> searchByCategoryName(String name) {
		return from(productRepository.findAllByCategory_NameLike("%" + name + "%"));
	}
}
