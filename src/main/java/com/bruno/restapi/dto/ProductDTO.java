package com.bruno.restapi.dto;

import com.bruno.restapi.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private Double price;

    public ProductDTO(Product product){
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }
}
