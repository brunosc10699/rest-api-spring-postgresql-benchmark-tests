package com.bruno.restapi.services;

import com.bruno.restapi.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDTO> findAll(Pageable pageable);
}
