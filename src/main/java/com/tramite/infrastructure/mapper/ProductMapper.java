package com.tramite.infrastructure.mapper;

import com.tramite.application.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Named("mapFromObjectArray")
    default ProductDto mapFromObjectArray(Object[] resultado) {
        if (resultado == null || resultado.length < 5) {
            return null;
        }

        // Convertir BigDecimal a Double de forma segura
        Double price = null;
        if (resultado[3] != null) {
            if (resultado[3] instanceof BigDecimal) {
                price = ((BigDecimal) resultado[3]).doubleValue();
            } else if (resultado[3] instanceof Double) {
                price = (Double) resultado[3];
            }
        }

        return ProductDto.builder()
                .idProduct((Integer) resultado[0])      // p.id_product
                .name((String) resultado[1])            // p.name
                .description((String) resultado[2])     // p.description
                .price(price)                           // p.price (BigDecimal → Double)
                .active((Boolean) resultado[4])         // p.active
                .build();
    }

    @Named("mapFromObjectArrayList")
    default List<ProductDto> mapFromObjectArrayList(List<Object[]> resultados) {
        if (resultados == null) {
            return null;
        }

        return resultados.stream()
                .map(this::mapFromObjectArray)
                .toList();
    }
}