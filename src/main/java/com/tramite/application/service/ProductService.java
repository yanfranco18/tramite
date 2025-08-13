package com.tramite.application.service;

import com.tramite.application.dto.ProductDto;
import java.util.List;

public interface ProductService {

    /**
     * Obtiene la lista de todos los productos activos
     * @return Lista de productos activos
     */
    List<ProductDto> getAllActiveProducts();
}
