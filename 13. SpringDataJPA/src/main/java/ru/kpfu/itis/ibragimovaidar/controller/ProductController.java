package ru.kpfu.itis.ibragimovaidar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
		return ResponseEntity.of(productService.findById(id));
	}
}
