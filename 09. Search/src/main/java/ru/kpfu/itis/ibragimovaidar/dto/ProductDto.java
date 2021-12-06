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
public class ProductDto {

	private String name;
	private String description;
	private Integer price;
	private Integer amount;

	public static ProductDto from(Product product){
		return ProductDto.builder()
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.amount(product.getAmount())
				.build();
	}
}
