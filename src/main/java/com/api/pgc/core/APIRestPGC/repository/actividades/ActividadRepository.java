/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<TblActividad, Integer> {

    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param idActividad
     * @return Actividad de la BD, por paramtro de ID_ACTIVIDAD
     * @autor Nahum Martinez | NAM
     * @version 11/01/2019/v1.0
     */
    TblActividad findByIdActividad(long idActividad);


    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param codActividad
     * @return Actividad de la BD, por paramtro de COD_ACTIVIDAD
     * @autor Nahum Martinez | NAM
     * @version 28/01/2019/v1.0
     */
    TblActividad findByCodigoActividad(String codActividad);


    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param codActividad
     * @return Actividad de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 11/01/2019/v1.0
     */
    long countByCodigoActividad(String codActividad);


    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param nombreActividad
     * @return Actividad de la BD, por parametro de Nombre de Proyecto
     * @autor Nahum Martinez | NAM
     * @version 08/02/2019/v1.0
     */
    long countByNombreActividad(String nombreActividad);


    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param idProyecto
     * @return Actividad de la BD, por parametro de Id
     * @autor Nahum Martinez | NAM
     * @version 11/01/2019/v1.0
     */
    long countByIdActividad(long idProyecto);

}
