package ru.kpfu.itis.ibragimovaidar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer price;

	private String description;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Product product = (Product) o;
		return id != null && Objects.equals(id, product.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
