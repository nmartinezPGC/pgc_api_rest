/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadIdInterna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ActividadIdInternaRepository extends JpaRepository<TblActividadIdInterna, Integer> {
    /**
     * Metodo que despliega las IdInternas de la BD
     * @autor Nahum Martinez | NAM
     * @version  19/12/2018/v1.0
     * @return IdInterna de la BD, por paramtro de ID
     * @param codIdInterna
     */
    TblActividadIdInterna findByCodIdInterna(String codIdInterna);

    /**
     * Metodo que despliega las Id Internas de la BD
     *
     * @param codIdInterna
     * @return Id Interna de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 09/01/2019/v1.0
     */
    long countByCodIdInterna(String codIdInterna);

    /**
     * Metodo que despliega las Id Internas de la BD
     *
     * @param tblActividad
     * @return Id Interna de la BD, por paramtro de Id
     * @autor Nahum Martinez | NAM
     * @version 11/02/2019/v1.0
     */
    long countByIdInterna(TblActividad tblActividad);


    /**
     * Metodo que Borra la Organizacion de la BD con el Id como parametro
     * en la Tabla Id Interna
     * @param codIdInterna
     * @return Organizacion de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 13/02/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadIdInterna e WHERE e.codIdInterna = :codIdInterna")
    void  deleletedCodIdInterna(@Param("codIdInterna") String codIdInterna);
}
