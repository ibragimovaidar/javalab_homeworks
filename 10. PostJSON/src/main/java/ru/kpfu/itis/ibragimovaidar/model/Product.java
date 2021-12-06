package ru.kpfu.itis.ibragimovaidar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	private Long id;
	private String name;
	private String description;
	private Integer price;
	private Integer amount;
}
