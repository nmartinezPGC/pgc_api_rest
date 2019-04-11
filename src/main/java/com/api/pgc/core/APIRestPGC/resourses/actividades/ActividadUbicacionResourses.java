/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades;


/*
 * Definicion de las Librerias a importar de la Clase
 */
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadIdInterna;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadUbicacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblUbicacionImplementacion;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadIdInternaRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadUbicacionRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.OrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.UbicacionImplementacionRepository;
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
@Api(value = "ActividadUbicacionAPI" , description = "Operaciones sobre el Modulo de Proyectos Ubicaciones", tags = "Ubicacion de Proyectos")
public class ActividadUbicacionResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadUbicacionRepository _actividadUbicacionRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    UbicacionImplementacionRepository _ubicacionImplementacionRepository;

    @Autowired
    UsuariosRepository _usuariosRepository;

    /**
     * Metodo que despliega la Lista de todas las Ubicaciones de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  28/02/2019/v1.0
     * @return Lista de Ubicaciones de una Actividad de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todas las Ubicaciones registradas de los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = UBICACIONES_ACT_ENDPOINT, produces = "application/json; charset=UTF-8" )
    public HashMap<String, Object> getAllUbicacionesActivities() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas las Ubicaciones registradas en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _actividadUbicacionRepository.findAll() );
            msgExeptions.map.put( "totalRecords", _actividadUbicacionRepository.count() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega las Ubicaciones asociadas a la Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  28/02/2019/v1.0
     * @return Ubicaciones de la BD
     * @param idActividad Identificador del Proyecto a Buscar
     */
    @ApiOperation(value = "Retorna las Ubicaciones, enviando a buscar por el Id del Proyecto de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = UBICACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUbicacionByIdActivity( @ApiParam(value="Identificador de la Id Proyecto a Buscar", required=true)
                                                  @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad( idActividad );

            try {
                if (_actividadUbicacionRepository.findByIdActividad( _tblActividad ) == null) {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de ubicaciones, por el Proyecto consultado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 400);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de las ubicaciones enocntradas por el Proyecto consultado";
                    msgExeptions.map.put("data", _actividadUbicacionRepository.findByIdActividad(_tblActividad));
                    msgExeptions.map.put("totalRecords", _actividadUbicacionRepository.countByIdActividad(_tblActividad));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encomtrado informacion de las ubicaciones para el Proyecto consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            //Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado informacion del Proyecto consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solicita un json con los datos de la Entidad Ubicaciones con Relacion
     * a Actividades
     * @param _actividadUbicacionJson Obtiene desde el request los datos de la Ubicacion a ingresar
     * @return Mensaje de Confirmacion de Registro de Relacion de Ubicacion con Proyecto
     * @autor Nahum Martinez | NAM
     * @version 28/02/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Json Bean de la nueva Ubicacion del proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = UBICACIONES_ACT_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addUbicacionImpl(@ApiParam(value = "Json de la nueva Ubicacion del Proyecto a Ingresar", required = true)
                                                             @RequestBody @Valid final TblActividadUbicacion _actividadUbicacionJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad( _actividadUbicacionJson.getIdActividad().getIdActividad() );

            try {
                // Busca la Ubicacion de Implementacion, desde el Reporsitorio con el Parametro del Json enviado ( "idUbicacionImpl": {"idUbicacionImplementacion": valor })
                TblUbicacionImplementacion _tblUbicacionImplementacion = _ubicacionImplementacionRepository.findByIdUbicacionImplementacion( _actividadUbicacionJson.getIdUbicacionImpl().getIdUbicacionImplementacion() );

                // Busca el Usuario Creador de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idUsuarioCreador": { "idUsuario": valor })
                TblUsuarios _tblUsuarios = _usuariosRepository.findByIdUsuario(  _actividadUbicacionJson.getIdUsuarioCreador().getIdUsuario() );

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Planificacion con el Parametro del Json enviado ( "idActividadIdInterna": _tblActividad )

                if( _actividadUbicacionRepository.countByIdUbicacionImplAndIdActividad( _tblUbicacionImplementacion, _tblActividad ) > 0 ) {
                    msgMethod = "Ya Existe un registro con el Código de Id Interna para este Proyecto !!";

                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadUbicacionJson.setFechaCreacion(dateActual);
                    _actividadUbicacionJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de la Id Interna y Organizacion
                    _actividadUbicacionJson.setIdActividad( _tblActividad );
                    _actividadUbicacionJson.setIdUbicacionImpl( _tblUbicacionImplementacion );
                    _actividadUbicacionJson.setIdUsuarioCreador( _tblUsuarios );

                    // Realizamos la Persistencia de los Datos
                    _actividadUbicacionRepository.save( _actividadUbicacionJson );
                    _actividadUbicacionRepository.flush();

                    // Busca la Ubicacion Recien Ingresada
                    msgExeptions.map.put("data", _actividadUbicacionRepository.findByIdUbicacionImplAndIdActividad( _tblUbicacionImplementacion, _tblActividad ));

                    // Retorno de la Funcion
                    msgMethod = "La Ubicacion para este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar la Ubicacion del Proyecto!!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN



    /**
     * Metodo que Solicita un json con los datos de la Entidad Ubicacion de Proyectos con Relacion
     * a Actividades
     * @param  idActividad Identificador de la Actividad a Eliminar
     * @param  idUbicacionImpl Identificador de la Ubicacion a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de la Ubicacion
     * @autor Nahum Martinez | NAM
     * @version 28/02/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el indentificador de Ubicacion", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = UBICACIONES_ACT_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadUbicacionImpl(@ApiParam(value = "Indentificar de la Ubicacion del Proyecto a Eliminar", required = true)
                                                             @PathVariable("idUbicacionImpl") long idUbicacionImpl,
                                                                 @ApiParam(value = "Indentificar del Proyecto a Eliminar", required = true) @PathVariable("idActividad") long idActividad ) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad( idActividad );

            try {
                // Busca la Actividad, desde el Reporsitorio con el Parametro del Id enviado ( idUbicacionImpl )
                // TblActividadUbicacion _tblActividadUbicacion= _actividadUbicacionRepository.findByIdUbicacionImpl( _tblUbicacionImplementacion );
                // Busca la Actividad, desde el Reporsitorio con el Parametro del Id enviado ( idUbicacionImpl )
                TblUbicacionImplementacion _tblUbicacionImplementacion = _ubicacionImplementacionRepository.findByIdUbicacionImplementacion( idUbicacionImpl );

                try {
                    if( _actividadUbicacionRepository.countByIdUbicacionImplAndIdActividad( _tblUbicacionImplementacion, _tblActividad ) > 0 ) {
                        // Realizamos la Persistencia de los Datos
                        _actividadUbicacionRepository.deleletedUbicacionActividad( _tblUbicacionImplementacion, _tblActividad );
                        _actividadUbicacionRepository.flush();

                        // Retorno de la Funcion
                        msgMethod = "La Ubicacion para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                        //Retorno del json
                        return msgExeptions.msgJson(msgMethod, 200);
                    } else {
                        msgMethod = "No Existe un registro de Ubicacion para este Proyecto !!";
                        throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                    }
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Eliminar la Ubicacion del Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
                }
            } catch (Exception ex) {
                msgMethod = "No Existe un registro de Ubicacion para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Proyecto en Ubicaciones , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN

}
