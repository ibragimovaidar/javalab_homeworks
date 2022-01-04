package ru.kpfu.itis.ibragimovaidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByNameLike(String name);
	List<Product> findAllByCategory_NameLike(String name);
}
