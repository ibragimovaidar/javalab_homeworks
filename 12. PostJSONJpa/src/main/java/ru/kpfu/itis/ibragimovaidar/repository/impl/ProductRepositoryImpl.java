package ru.kpfu.itis.ibragimovaidar.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Product> findAll() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public Optional<Product> findById(Long id) {
		return Optional.ofNullable(entityManager.find(Product.class, id));
	}

	@Override
	@Transactional
	public Product save(Product product) {
		entityManager.persist(product);
		entityManager.flush();
		return product;
	}
}
