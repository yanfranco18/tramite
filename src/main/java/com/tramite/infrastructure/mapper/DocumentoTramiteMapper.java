package com.tramite.infrastructure.mapper;

import com.tramite.application.dto.DocumentoTramiteDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocumentoTramiteMapper {

    // Mapeo personalizado desde Object[] a DTO
    @Named("mapFromObjectArray")
    default DocumentoTramiteDto mapFromObjectArray(Object[] resultado) {
        if (resultado == null || resultado.length < 8) {
            return null;
        }

        return DocumentoTramiteDto.builder()
                .idDocumento((Integer) resultado[0])
                .nombreDocumento((String) resultado[1])
                .activo((Boolean) resultado[3])
                .idGrupoDocumento((Integer) resultado[4])
                .obligatorio((Boolean) resultado[7])
                .build();
    }

    // Mapeo de listas de Object[] a DTOs
    @Named("mapFromObjectArrayList")
    default List<DocumentoTramiteDto> mapFromObjectArrayList(List<Object[]> resultados) {
        if (resultados == null) {
            return null;
        }

        return resultados.stream()
                .map(this::mapFromObjectArray)
                .toList();
    }
}
