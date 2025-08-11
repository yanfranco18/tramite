package com.tramite.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoTramiteRequeridoId implements Serializable {

    private Integer idTipoTramite;
    private Integer idDocumento;
    private Integer idGrupoDocumento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentoTramiteRequeridoId that = (DocumentoTramiteRequeridoId) o;
        return Objects.equals(idTipoTramite, that.idTipoTramite) &&
                Objects.equals(idDocumento, that.idDocumento) &&
                Objects.equals(idGrupoDocumento, that.idGrupoDocumento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoTramite, idDocumento, idGrupoDocumento);
    }
}
