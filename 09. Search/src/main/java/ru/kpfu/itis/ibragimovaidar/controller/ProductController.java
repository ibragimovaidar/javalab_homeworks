package ru.kpfu.itis.ibragimovaidar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductDto>> getAll(){
		return ResponseEntity.ok(productService.findAll());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductDto> getById(@PathVariable Long id){
		return productService.findById(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void save(@RequestBody ProductDto productDto){
		productService.save(productDto);
	}
}
