package com.intellias.marketplace.repo;

import com.intellias.marketplace.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
