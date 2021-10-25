package com.example.sentinel.dao.es;

import com.example.sentinel.dao.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductDao extends ElasticsearchRepository<Product, Long> {

    List<Product> findAll();
}
