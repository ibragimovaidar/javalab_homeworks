package ru.kpfu.itis.ibragimovaidar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/search")
public class SearchController {

	private final ProductService productService;

	@RequestMapping(path = "/product", method = RequestMethod.GET)
	public String getSearchProductPage(){
		return "search_products";
	}

	@RequestMapping(path = "/product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProductDto> searchProduct(@RequestParam("q") String query){
		return productService.search(query);
	}
}
