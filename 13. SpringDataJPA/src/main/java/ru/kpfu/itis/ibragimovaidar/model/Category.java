package ru.kpfu.itis.ibragimovaidar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<Product> products;

	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<Category> child;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Category category = (Category) o;
		return id != null && Objects.equals(id, category.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
