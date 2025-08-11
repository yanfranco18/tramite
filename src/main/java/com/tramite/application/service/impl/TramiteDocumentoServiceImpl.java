package com.tramite.application.service.impl;

import com.tramite.application.dto.DocumentoTramiteDto;
import com.tramite.application.service.TramiteDocumentoService;
import com.tramite.infrastructure.repository.DocumentoTramiteRepository;
import com.tramite.infrastructure.mapper.DocumentoTramiteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TramiteDocumentoServiceImpl implements TramiteDocumentoService {

    private final DocumentoTramiteRepository documentoTramiteRepository;
    private final DocumentoTramiteMapper documentoTramiteMapper;

    @Override
    public List<DocumentoTramiteDto> obtenerDocumentosPorTipoTramite(Integer idTipoTramite) {
        log.info("Iniciando búsqueda de documentos para tipo de trámite: {}", idTipoTramite);

        try {
            List<Object[]> resultados = documentoTramiteRepository.findDocumentosByTipoTramite(idTipoTramite);

            // Usando MapStruct para el mapeo
            List<DocumentoTramiteDto> documentos = documentoTramiteMapper.mapFromObjectArrayList(resultados);

            log.info("Se encontraron {} documentos para el tipo de trámite: {}",
                    documentos != null ? documentos.size() : 0, idTipoTramite);

            return documentos != null ? documentos : List.of();

        } catch (Exception e) {
            log.error("Error al obtener documentos para tipo de trámite {}: {}", idTipoTramite, e.getMessage(), e);
            throw new RuntimeException("Error interno al consultar documentos", e);
        }
    }
}