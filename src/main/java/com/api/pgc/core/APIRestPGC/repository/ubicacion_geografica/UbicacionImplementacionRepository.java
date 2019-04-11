/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica;

import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblNivelImplementacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblNivelUbicacionImplementacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblUbicacionImplementacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UbicacionImplementacionRepository extends JpaRepository<TblUbicacionImplementacion, Integer> {

    /**
     * Metodo que despliega la Ubicacion de Implementacion de la BD
     * @autor Nahum Martinez | NAM
     * @version  25/02/2019/v1.0
     * @return Ubicacion de Implementacion de la BD, por parametro de ID
     * @param idUbicacionImplementacion
     */
    TblUbicacionImplementacion findByIdUbicacionImplementacion(long idUbicacionImplementacion);


    /**
     * Metodo que despliega el Ubicacion de Implementacion de la BD con el Nivel de Implementacion como parametro
     *
     * @param tblNivelImplementacion
     * @return Ubicacion Implementacion de la BD, por parametro de ID Nivel de Implementacion
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @Query("SELECT e FROM TblUbicacionImplementacion e WHERE e.idNivelImplementacion = :idNivelImplementacion ")
    List<TblUbicacionImplementacion> findByIdNivelImplementacion(@Param("idNivelImplementacion") TblNivelImplementacion tblNivelImplementacion);


    /**
     * Metodo que despliega el Ubicacion de Implementacion de la BD con el Nivel de Implementacion como parametro
     *
     * @param tblNivelUbicacionImplementacion
     * @return Ubicacion Implementacion de la BD, por parametro de ID Nivel de Implementacion
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @Query("SELECT e FROM TblUbicacionImplementacion e WHERE e.idNivelUbicacion = :idNivelUbicacion")
    List<TblUbicacionImplementacion> findByIdNivelUbicacionImplementacion(@Param("idNivelUbicacion") TblNivelUbicacionImplementacion tblNivelUbicacionImplementacion);


    /**
     * Metodo que despliega el Ubicacion de Implementacion de la BD con el Nivel de Implementacion y
     * Nivel de Ubicacion como parametro
     *
     * @param tblNivelUbicacionImplementacion
     * @param  tblNivelImplementacion
     * @return Ubicacion Implementacion de la BD, por parametro de ID Nivel de Implementacion y Id
     * Nivel de Ubicacion
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @Query("SELECT e FROM TblUbicacionImplementacion e WHERE e.idNivelUbicacion = :idNivelUbicacion AND e.idNivelImplementacion = :idNivelImplementacion")
    List<TblUbicacionImplementacion> findByIdNivelImplementacionAndIdNivelUbicacion(@Param("idNivelImplementacion") TblNivelImplementacion tblNivelImplementacion,
            @Param("idNivelUbicacion") TblNivelUbicacionImplementacion tblNivelUbicacionImplementacion);


    /**
     * Metodo que despliega el Ubicacion de Implementacion de la BD con el Nivel de Implementacion y
     * Nivel de Ubicacion como parametro
     *
     * @param tblNivelUbicacionImplementacion
     * @param  tblNivelImplementacion
     * @return Ubicacion Implementacion de la BD, por parametro de ID Nivel de Implementacion y Id
     * Nivel de Ubicacion
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @Query("SELECT COUNT(e) FROM TblUbicacionImplementacion e WHERE e.idNivelUbicacion = :idNivelUbicacion AND e.idNivelImplementacion = :idNivelImplementacion")
    long countByIdNivelImplementacionAndIdNivelUbicacion(@Param("idNivelImplementacion") TblNivelImplementacion tblNivelImplementacion,
                                                                                    @Param("idNivelUbicacion") TblNivelUbicacionImplementacion tblNivelUbicacionImplementacion);
}
