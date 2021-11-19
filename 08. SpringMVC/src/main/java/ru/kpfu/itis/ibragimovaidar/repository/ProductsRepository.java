package ru.kpfu.itis.ibragimovaidar.repository;

import ru.kpfu.itis.ibragimovaidar.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {

	List<Product> findAll();
	Optional<Product> findById(Long id);
	Product save(Product product);
}
