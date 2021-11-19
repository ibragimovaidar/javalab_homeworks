package ru.kpfu.itis.ibragimovaidar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.ibragimovaidar.model.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

	private String name;
	private Integer price;
	private Integer availableAmount;
	private String description;

	public static ProductDTO from(Product product){
		return ProductDTO.builder()
				.name(product.getName())
				.price(product.getPrice())
				.availableAmount(product.getAvailableAmount())
				.description(product.getDescription())
				.build();
	}
}
