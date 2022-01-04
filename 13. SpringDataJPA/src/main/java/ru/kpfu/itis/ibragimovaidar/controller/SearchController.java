package ru.kpfu.itis.ibragimovaidar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

	private final ProductService productService;

	@GetMapping(path = "/product/byName")
	public List<ProductDto> searchProductsByName(@RequestParam String name){
		return productService.searchByName(name);
	}

	@GetMapping(path = "/product/byCategoryName")
	public List<ProductDto> searchProductsByCategoryName(@RequestParam String name){
		return productService.searchByCategoryName(name);
	}
}
