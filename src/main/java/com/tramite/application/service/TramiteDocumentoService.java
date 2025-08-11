package com.tramite.application.service;

import com.tramite.application.dto.DocumentoTramiteDto;
import java.util.List;

public interface TramiteDocumentoService {

    /**
     * Obtiene la lista de documentos requeridos para un tipo de trámite específico
     * @param idTipoTramite ID del tipo de trámite
     * @return Lista de documentos requeridos
     */
    List<DocumentoTramiteDto> obtenerDocumentosPorTipoTramite(Integer idTipoTramite);
}
