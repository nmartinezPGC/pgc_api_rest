/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica;

import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblNivelImplementacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblNivelUbicacionImplementacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NivelUbicacionImplementacionRepository extends JpaRepository<TblNivelUbicacionImplementacion, Integer> {

    /**
     * Metodo que despliega el Nivel de Ubicacion de Implementacion de la BD
     * @autor Nahum Martinez | NAM
     * @version  20/02/2019/v1.0
     * @return Nivel de Ubicacion de  Implementacion de la BD, por paramtro de ID
     * @param idNivelUbicacion
     */
    TblNivelUbicacionImplementacion findByIdNivelUbicacion(long idNivelUbicacion);


    /**
     * Metodo que despliega el Nivel de Ubicacion de Implementacion de la BD con el Nivel de Implementacion como parametro
     *
     * @param tblNivelImplementacion
     * @return Nivel Ubicacion Implementacion de la BD, por paramtro de ID Nivel de Implementacion
     * @autor Nahum Martinez | NAM
     * @version 20/02/2019/v1.0
     */
    @Query("SELECT e FROM TblNivelUbicacionImplementacion e WHERE e.idNivelImplementacion = :idNivelImplementacion ")
    List<TblNivelUbicacionImplementacion> findByIdNivelImplementacion(@Param("idNivelImplementacion") TblNivelImplementacion tblNivelImplementacion);
}
