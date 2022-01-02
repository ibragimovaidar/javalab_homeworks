package ru.kpfu.itis.ibragimovaidar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.ibragimovaidar.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

	private String name;
	private String description;
	private Integer price;
	private Integer availableAmount;

	public static ProductDTO from(Product product){
		return ProductDTO.builder()
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.availableAmount(product.getAvailableAmount())
				.build();
	}
}
