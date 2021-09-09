package com.bruno.restapi.services;

import com.bruno.restapi.dto.ProductDTO;
import com.bruno.restapi.entities.Product;
import com.bruno.restapi.repositories.ProductRepository;
import com.bruno.restapi.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Must return a page of Product")
    void MustReturnAPageOfProduct() {
        Product product = new Product("1", "Vacuum", 199.00);
        ProductDTO productDTO = new ProductDTO(product);
        Page<ProductDTO> productDTOPage;
        Pageable pageable = PageRequest.of(0, 20);
        Page<Product> page = new PageImpl<Product>(Collections.singletonList(product));
        when(productRepository.findAll(pageable)).thenReturn(page);
        productDTOPage = productService.findAll(pageable);
        assertAll(
                () -> assertThat(productDTOPage.getContent(), is(not(empty()))),
                () -> assertThat(productDTOPage.getTotalPages(), is(equalTo(1))),
                () -> assertThat(productDTOPage.getSize(), is(equalTo(1))),
                () -> assertThat(productDTOPage.getTotalElements(), is(equalTo(1L)))
        );
    }

    @Test
    @DisplayName("Must return an empty Product page")
    void MustReturnAnEmptyProductPage(){
        Page<ProductDTO> productDTOPage;
        Pageable pageable = PageRequest.of(0, 20);
        when(productRepository.findAll(pageable)).thenReturn(Page.empty());
        productDTOPage = productService.findAll(pageable);
        assertThat(productDTOPage.getContent(), is(empty()));
    }

}
