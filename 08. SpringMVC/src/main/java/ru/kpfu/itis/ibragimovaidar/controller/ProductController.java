package ru.kpfu.itis.ibragimovaidar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDTO;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public String getProductsList(Model model){
		model.addAttribute("products", productService.findAll());
		return "products";
	}

	@GetMapping("/{id}")
	public String getProduct(@PathVariable Long id, Model model){
		model.addAttribute("product", productService.findById(id));
		return "product";
	}

	@PostMapping
	public String addProduct(ProductDTO productDTO){
		productService.addProduct(productDTO);
		return("redirect:/product");
	}

	@GetMapping("/add")
	public String getProductForm(){
		return "productForm";
	}
}
