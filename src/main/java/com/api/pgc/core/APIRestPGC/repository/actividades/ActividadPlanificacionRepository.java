/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadPlanificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadPlanificacionRepository extends JpaRepository<TblActividadPlanificacion, Integer> {
    /**
     * Metodo que despliega las Planificaciones de la BD
     * @autor Nahum Martinez | NAM
     * @version  24/01/2019/v1.0
     * @return Planificaciones de la BD, por parametro de ID
     * @param codigoActividad
     */
    TblActividadPlanificacion findByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega las Id Internas de la BD
     *
     * @param codActividad
     * @return La Planificacion de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 24/01/2019/v1.0
     */
    long countByCodigoActividad(String codActividad);


    /**
     * Metodo que despliega las Planificaciones de la BD, segun la
     * Entidad del proyecto que se envia
     * @autor Nahum Martinez | NAM
     * @version  29/01/2019/v1.0
     * @return Planificaciones de la BD, por parametro de ID_ACTIVIDAD_PLAN
     * @param _tblActividad
     */
    TblActividadPlanificacion findByIdActividadPlan(TblActividad _tblActividad);


    /**
     * Metodo que despliega las Planificaciones de la BD, segun la
     * Entidad del proyecto que se envia
     * @autor Nahum Martinez | NAM
     * @version  29/01/2019/v1.0
     * @return Planificaciones de la BD, por parametro de ID_ACTIVIDAD_PLAN
     * @param _tblActividad
     */
    long countByIdActividadPlan(TblActividad _tblActividad);
}
