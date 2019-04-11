/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.TblTipoIniciativaCss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIniciativaCssRepository extends JpaRepository<TblTipoIniciativaCss, Integer> {

    /**
     * Metodo que despliega el Tipo de Iniciativa CSS de la BD
     * @autor Nahum Martinez | NAM
     * @version  06/02/2019/v1.0
     * @return Tipo de Iniciativa CSS de la BD, por paramtro de ID
     * @param idTipoIniciativa
     */
    TblTipoIniciativaCss findByIdTipoIniciativa(long idTipoIniciativa);

}
