package com.tramite.presentation.controller;

import com.tramite.application.dto.DocumentoTramiteDto;
import com.tramite.application.service.TramiteDocumentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/tramites")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TramiteController {

    private final TramiteDocumentoService tramiteDocumentoService;

    @GetMapping("/{idTipoTramite}/documentos")
    public ResponseEntity<List<DocumentoTramiteDto>> obtenerDocumentosPorTramite(
            @PathVariable
            @NotNull(message = "El ID del tipo de trámite es obligatorio")
            @Min(value = 1, message = "El ID del tipo de trámite debe ser mayor a 0")
            Integer idTipoTramite) {

        log.info("GET /api/v1/tramites/{}/documentos - Iniciando consulta", idTipoTramite);

        try {
            // Obtener documentos usando el service
            List<DocumentoTramiteDto> documentos = tramiteDocumentoService
                    .obtenerDocumentosPorTipoTramite(idTipoTramite);

            // Validar si existen documentos
            if (documentos.isEmpty()) {
                log.info("No se encontraron documentos para el tipo de trámite: {}", idTipoTramite);
                return ResponseEntity.noContent().build();
            }

            log.info("Consulta exitosa para tipo de trámite: {}. Documentos encontrados: {}",
                    idTipoTramite, documentos.size());

            return ResponseEntity.ok(documentos);

        } catch (IllegalArgumentException e) {
            log.warn("Parámetro inválido para tipo de trámite: {}", idTipoTramite, e);
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            log.error("Error interno al procesar consulta para tipo de trámite: {}", idTipoTramite, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}