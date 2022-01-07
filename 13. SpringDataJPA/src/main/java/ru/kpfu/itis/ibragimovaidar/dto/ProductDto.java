package ru.kpfu.itis.ibragimovaidar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.ibragimovaidar.model.Product;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

	private Long id;
	@NotNull(message = "name must not be null")
	private String name;
	private Integer price;
	private String description;
	private Long categoryId;

	public static ProductDto from(Product product){
		return ProductDto.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.categoryId(product.getCategory().getId())
				.build();
	}

	public static List<ProductDto> from(List<Product> products){
		return products.stream()
				.map(ProductDto::from)
				.collect(Collectors.toList());
	}
}
