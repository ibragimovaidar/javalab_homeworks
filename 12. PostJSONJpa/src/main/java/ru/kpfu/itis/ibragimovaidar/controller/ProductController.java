package ru.kpfu.itis.ibragimovaidar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDTO;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
		return ResponseEntity.of(productService.findById(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void save(@RequestBody ProductDTO productDTO){
		productService.save(productDTO);
	}
}
