package com.tramite.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documentos_tramite_requeridos", schema = "tramite")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DocumentoTramiteRequeridoId.class)
public class DocumentoTramiteRequeridoEntity {

    @Id
    @Column(name = "id_tipo_tramite")
    private Integer idTipoTramite;

    @Id
    @Column(name = "id_documento")
    private Integer idDocumento;

    @Id
    @Column(name = "id_grupo_documento")
    private Integer idGrupoDocumento;

    @Column(name = "obligatorio")
    private Boolean obligatorio;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "usuario_registro", length = 50, nullable = false)
    private String usuarioRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "usuario_actualizacion", length = 50)
    private String usuarioActualizacion;
}