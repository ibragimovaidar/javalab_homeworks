package ru.kpfu.itis.ibragimovaidar.repository;

import ru.kpfu.itis.ibragimovaidar.model.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> search(String query);
}
