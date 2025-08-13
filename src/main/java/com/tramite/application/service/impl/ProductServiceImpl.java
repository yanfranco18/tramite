package com.tramite.application.service.impl;

import com.tramite.application.dto.ProductDto;
import com.tramite.application.service.ProductService;
import com.tramite.infrastructure.repository.product.ProductRepository;
import com.tramite.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, transactionManager = "productsTransactionManager")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllActiveProducts() {
        log.info("Obteniendo todos los productos activos desde bd_product");

        try {
            List<Object[]> resultados = productRepository.findAllActiveProducts();

            List<ProductDto> products = productMapper.mapFromObjectArrayList(resultados);

            log.info("Se encontraron {} productos activos en bd_product",
                    products != null ? products.size() : 0);

            return products != null ? products : List.of();

        } catch (Exception e) {
            log.error("Error al obtener productos activos: {}", e.getMessage(), e);
            throw new RuntimeException("Error interno al consultar productos", e);
        }
    }
}