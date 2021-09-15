package com.example.springcloud.dao.es;

import com.example.springcloud.dao.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductDao extends ElasticsearchRepository<Product, Long> {

    List<Product> findAll();
}
