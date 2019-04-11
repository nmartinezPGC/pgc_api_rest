/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica;

import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblDepartamentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptoRepository extends JpaRepository<TblDepartamentos, Integer> {

    /**
     * Metodo que despliega el Depto de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/02/2019/v1.0
     * @return Pais de la BD, por paramtro de ID
     * @param idDepartamento
     */
    TblDepartamentos findByIdDepartamento(long idDepartamento);
}
