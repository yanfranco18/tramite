package com.tramite.presentation.controller;

import com.tramite.application.dto.ProductDto;
import com.tramite.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllActiveProducts() {
        log.info("GET /api/v1/products - Obteniendo todos los productos activos");

        try {
            List<ProductDto> products = productService.getAllActiveProducts();

            if (products.isEmpty()) {
                log.info("No se encontraron productos activos");
                return ResponseEntity.noContent().build();
            }

            log.info("Se encontraron {} productos activos", products.size());
            return ResponseEntity.ok(products);

        } catch (Exception e) {
            log.error("Error al obtener productos activos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
