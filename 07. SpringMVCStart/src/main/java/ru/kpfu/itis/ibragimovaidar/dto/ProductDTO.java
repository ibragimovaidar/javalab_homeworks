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
	private Long price;
	private String description;
	private Integer availableAmount;

	public static ProductDTO from(Product product){
		return ProductDTO.builder()
				.name(product.getName())
				.price(product.getPrice())
				.description(product.getDescription())
				.availableAmount(product.getAvailableAmount())
				.build();
	}
}
