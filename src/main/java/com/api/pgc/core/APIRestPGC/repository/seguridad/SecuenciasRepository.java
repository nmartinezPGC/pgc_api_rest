/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.seguridad;

import com.api.pgc.core.APIRestPGC.models.seguridad.TblSecuencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecuenciasRepository extends JpaRepository<TblSecuencia, Integer> {

    /**
     * Metodo que despliega la Secuencia de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/01/2019/v1.0
     * @return Secuencia de la BD, por parametro de ID
     * @param idSecuencia
     */
    TblSecuencia findByIdSecuencia(long idSecuencia );


    /**
     * Metodo que despliega la Secuencia de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/01/2019/v1.0
     * @return Secuencia de la BD, por parametro de Código
     * @param codSecuencia
     */
    TblSecuencia findByCodSecuencia(String codSecuencia );

}
