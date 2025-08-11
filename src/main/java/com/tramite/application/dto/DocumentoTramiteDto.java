package com.tramite.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoTramiteDto {

    private Integer idDocumento;
    private String nombreDocumento;
    private Boolean activo;
    private Integer idGrupoDocumento;
    private Boolean obligatorio;
}