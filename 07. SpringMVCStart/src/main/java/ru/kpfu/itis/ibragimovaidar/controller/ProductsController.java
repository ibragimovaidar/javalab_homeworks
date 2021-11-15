package ru.kpfu.itis.ibragimovaidar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDTO;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProductsController implements Controller {

	private final ProductService productService;

	@Autowired
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("POST".equals(request.getMethod())){
			ProductDTO productDTO = ProductDTO.builder()
					.name(request.getParameter("name"))
					.description(request.getParameter("description"))
					.price(Long.parseLong(request.getParameter("price")))
					.availableAmount(Integer.parseInt(request.getParameter("availableAmount")))
					.build();
			productService.save(productDTO);
			response.setStatus(200);
			return null;
		}
		else if ("GET".equals(request.getMethod())){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("products", productService.findAll());
			modelAndView.setViewName("products");
			return modelAndView;
		}
		response.setStatus(400);
		return null;
	}
}
