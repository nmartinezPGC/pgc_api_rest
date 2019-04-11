/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadPlanificacion;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadPlanificacionRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "PlanificacionActividadAPI" , description = "Operaciones sobre el Modulo de Planificacion de Proyectos", tags = "Fechas de Planificacion")
public class ActividadPlanificacionResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadPlanificacionRepository _actividadPlanificacionRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    /**
     * Metodo que despliega la Lista de todas las Planificaciones de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  01/01/2019/v1.0
     * @return Lista de todas las Planificaciones de la BD
     */
    @ApiOperation(value = "Buscar todas las fechas de Planificacion", notes = "Retorna el Listado de las Fechas que tiene cada Proyecto en relación a la Planificación",
            authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ID_PLANIFICACION_ENDPOINT, produces = "application/json; charset=UTF-8" )
    public HashMap<String, Object> getAllActvidadesPlanificaciones() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            // Busca las Planificaciones
            if (_actividadPlanificacionRepository.count() == 0 ){
                msgMethod = "No se ha Encontrado Información de Planificaciones registradas en la BD";
            } else {
                msgMethod = "Listado de todas las Planificaciones registradas en la BD";
            }

            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _actividadPlanificacionRepository.findAll() );
            msgExeptions.map.put( "countRecords", _actividadPlanificacionRepository.count() );
            // Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solicita un json con los datos de la Entidad Planificacion con Relacion
     * a Actividades
     * @param _planificacionJson Obtiene desde el request los datos de la Planificacion a ingresar
     * @return Mensaje de Confirmacion de Registro de de Planificacion
     * @autor Nahum Martinez | NAM
     * @version 24/01/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de la Nueva Planificacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ID_PLANIFICACION_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadPlanificacion(@ApiParam(value = "Json de la nueva Planificacion del Proyecto a Ingresar", required = true)
                                                            @RequestBody @Valid final TblActividadPlanificacion _planificacionJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadPlan": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad( _planificacionJson.getIdActividadPlan().getIdActividad() );

            try {
                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Planificacion con el Parametro del Json enviado ( "idActividadPlan": _tblActividad )

                if( _actividadPlanificacionRepository.countByIdActividadPlan( _tblActividad ) > 0 ) {
                    msgMethod = "Ya Existe un registro de Planifiacion para este Proyecto !!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {

                    // Seteo de las Fecha y Hora de Creacion
                    _planificacionJson.setFechaCreacion(dateActual);
                    _planificacionJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de la Planificacion
                    _planificacionJson.setIdActividadPlan(_tblActividad);

                    // Realizamos la Persistencia de los Datos
                    _actividadPlanificacionRepository.save(_planificacionJson);
                    _actividadPlanificacionRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "La Planificacion para este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar la Planificación del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN


    /**
     * Metodo que Solicita un json con los datos de la Entidad Planificacion con Relacion
     * a Actividades
     * @param _planificacionJson Obtiene desde el request los datos de la Planificacion a actualizar
     * @param  idActividadPlan Identificador del Proyecto a Modificar
     * @return Mensaje de Confirmacion de Actualizacion de de Planificacion
     * @autor Nahum Martinez | NAM
     * @version 08/02/2019/v1.0
     */
    @ApiOperation(value = "Actualiza a la BD, la Información enviada por el Bean de la Planificacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = ID_PLANIFICACION_ENDPOINT_EDIT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editActividadPlanificacion(@ApiParam(value = "Json de la Planificacion del Proyecto a Actualizar", required = true)
                                                             @RequestBody @Valid final TblActividadPlanificacion _planificacionJson,
                                                              @PathVariable("idActividadPlan") long idActividadPlan ) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Modificacion
        Date dateActual = new Date();

        System.out.println("ID del Proyecto ++++++ " + _planificacionJson.getIdActividadPlan().getIdActividad());
        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadPlan": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad( _planificacionJson.getIdActividadPlan().getIdActividad() );

            try {
                // Busca la Actividad dentro de la Planificacion, desde el Reporsitorio con el Parametro de la Entidad enviada: _tblActividad)
                TblActividadPlanificacion _tblActividadPlanificacion = _actividadPlanificacionRepository.findByIdActividadPlan( _tblActividad );

                if( _actividadPlanificacionRepository.countByIdActividadPlan( _tblActividad ) > 0 ) {
                    // Seteo de las Fechas de Planificacion
                    _tblActividadPlanificacion.setFechaFirma( _planificacionJson.getFechaFirma() );
                    _tblActividadPlanificacion.setFechaPropuestaFinalizacion( _planificacionJson.getFechaPropuestaFinalizacion() );
                    _tblActividadPlanificacion.setFechaCierre( _planificacionJson.getFechaCierre() );
                    _tblActividadPlanificacion.setFechaEfectividad( _planificacionJson.getFechaEfectividad() );
                    _tblActividadPlanificacion.setFechaFinalizacion( _planificacionJson.getFechaFinalizacion() );

                    // Seteo de las Fecha y Hora de Creacion
                    _tblActividadPlanificacion.setFechaModificacion(dateActual);
                    _tblActividadPlanificacion.setHoraModificacion(dateActual);

                    // Seteamos la Actividad de la Planificacion
                    _tblActividadPlanificacion.setIdActividadPlan(_tblActividad);

                    // Realizamos la Persistencia de los Datos
                    _actividadPlanificacionRepository.save(_tblActividadPlanificacion);
                    _actividadPlanificacionRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "La Planificacion para este Proyecto, se ha Actualizado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Planifiacion para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Actualizar la Planificación del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN
}
