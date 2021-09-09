package com.bruno.restapi.resources;

import com.bruno.restapi.dto.ProductDTO;
import com.bruno.restapi.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductResourceTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductResource productResource;

    private static final String URN = "/api/v1/products/";

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(productResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    @DisplayName("Must return status code 200 OK")
    void MustReturnStatusCode200Ok() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        ProductDTO productDTO = new ProductDTO("1", "Vacuum", 199.00);
        Page<ProductDTO> page = new PageImpl<ProductDTO>(Collections.singletonList(productDTO));
        when(productService.findAll(pageable)).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders.get(URN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
