package ru.kpfu.itis.ibragimovaidar.repository;

import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.model.Product;

import java.util.List;

@Repository
public interface ProductRepository {

	List<Product> findAll();
	Product save(Product product);
}
