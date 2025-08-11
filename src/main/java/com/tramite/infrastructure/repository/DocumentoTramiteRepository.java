package com.tramite.infrastructure.repository;

import com.tramite.domain.entity.DocumentoTramiteRequeridoEntity;
import com.tramite.domain.entity.DocumentoTramiteRequeridoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentoTramiteRepository extends JpaRepository<DocumentoTramiteRequeridoEntity, DocumentoTramiteRequeridoId> {

    @Query(value = """
        SELECT 
            d.id_documento,
            d.nombre,
            d.descripcion,
            d.activo,
            gd.id_grupo_documento,
            gd.nombre,
            gd.descripcion,
            dtr.obligatorio
        FROM tramite.documentos d
        INNER JOIN tramite.documentos_tramite_requeridos dtr ON d.id_documento = dtr.id_documento
        INNER JOIN tramite.grupos_documentos gd ON dtr.id_grupo_documento = gd.id_grupo_documento
        WHERE dtr.id_tipo_tramite = :idTipoTramite
        AND d.activo = 1
        AND gd.activo = 1
        AND dtr.activo = 1
        ORDER BY gd.nombre, d.nombre
        """, nativeQuery = true)
    List<Object[]> findDocumentosByTipoTramite(@Param("idTipoTramite") Integer idTipoTramite);
}
