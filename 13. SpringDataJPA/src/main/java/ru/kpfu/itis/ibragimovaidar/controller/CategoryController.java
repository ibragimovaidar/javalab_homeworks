package ru.kpfu.itis.ibragimovaidar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.ibragimovaidar.dto.CategoryDto;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;
import ru.kpfu.itis.ibragimovaidar.service.CategoryService;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;
	private final ProductService productService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
		return ResponseEntity.of(categoryService.findById(id));
	}

	@PostMapping
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
		return ResponseEntity.ok(categoryService.addCategory(categoryDto));
	}

	@PostMapping(path = "/{id}/product")
	public ResponseEntity<ProductDto> addProduct(@PathVariable("id") Long categoryId, @RequestBody ProductDto productDto){
		return ResponseEntity.ok(productService.save(categoryId, productDto));
	}
}
