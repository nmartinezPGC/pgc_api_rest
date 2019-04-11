/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades;


/*
 * Definicion de las Librerias a importar de la Clase
 */
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadIdInterna;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadIdInternaRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.OrganizacionRepository;
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
@Api(value = "ActividadIdInternaAPI" , description = "Operaciones sobre el Modulo de Proyectos Id Interna", tags = "Id Internas")
public class ActividadIdInternaResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadIdInternaRepository _actividadIdInternaRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    OrganizacionRepository _organizacionRepository;

    /**
     * Metodo que despliega la Lista de todas las idInternas de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  19/12/2018/v1.0
     * @return Lista de Internas de una Actividad de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todas las Id Internas de una Actividad de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ID_INTERNA_ENDPOINT, produces = "application/json; charset=UTF-8" )
    public HashMap<String, Object> getAllActvidadesIdInternas() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas las Id Internas registradas en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _actividadIdInternaRepository.findAll() );
            msgExeptions.map.put( "countRecords", _actividadIdInternaRepository.count() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega las Id Internas asociadas a la Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  19/12/2018/v1.0
     * @return Estado de la BD
     * @param codIdInterna Identificador de la Id Interna a Buscar
     */
    @ApiOperation(value = "Retorna la Id Interna enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ID_INTERNA_ENDPOINT_FIND_BY_CODIGO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getIdInternaByCod( @ApiParam(value="Identificador de la Id Interna a Buscar", required=true)
                                                  @PathVariable("codIdInterna") String codIdInterna ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if ( _actividadIdInternaRepository.findByCodIdInterna( codIdInterna ) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de la Id Interna Consultada";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de la Id Interna Consultada";
                msgExeptions.map.put("data", _actividadIdInternaRepository.findByCodIdInterna( codIdInterna ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN

    /**
     * Metodo que despliega la Id Interna de la BD
     *
     * @param codIdInterna Codigo de Id Interna a Buscar
     * @return Id Interna de la BD
     * @autor Nahum Martinez | NAM
     * @version 09/01/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Id Interna enviado a buscar el Codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ID_INTERNA_ENDPOINT_FIND_BY_CODIGO_COUNT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getOrganizacionByCodigoCount(@ApiParam(value = "Código de Id Interna a Buscar", required = true)
                                                                @PathVariable("codIdInterna") String codIdInterna) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadIdInternaRepository.countByCodIdInterna(codIdInterna) == 0) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Organizacion consultada";

                msgExeptions.map.put("data", _actividadIdInternaRepository.countByCodIdInterna(codIdInterna));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontraddo la Organizacion Consultada";
                msgExeptions.map.put("data", _actividadIdInternaRepository.countByCodIdInterna(codIdInterna));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solicita un json con los datos de la Entidad Id Interna con Relacion
     * a Actividades
     * @param _idInternaJson Obtiene desde el request los datos de la Interna a ingresar
     * @return Mensaje de Confirmacion de Registro de Id Interna
     * @autor Nahum Martinez | NAM
     * @version 11/02/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de la Nueva Id Interna de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ID_INTERNA_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addIdInterna(@ApiParam(value = "Json de la nueva Planificacion del Proyecto a Ingresar", required = true)
                                                             @RequestBody @Valid final TblActividadIdInterna _idInternaJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadPlan": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad( _idInternaJson.getIdActividadIdInterna().getIdActividad() );

            try {
                // Busca la Organizacion, desde el Reporsitorio con el Parametro del Json enviado ( "idOrganizacion": {"idOrganizacion": valor })
                TblOrganizacion _tblOrganizacion = _organizacionRepository.findByIdOrganizacion( _idInternaJson.getIdOrganizacionIdInterna().getIdOrganizacion() );

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Planificacion con el Parametro del Json enviado ( "idActividadIdInterna": _tblActividad )

                if( _actividadIdInternaRepository.countByCodIdInterna( _idInternaJson.getCodIdInterna() ) > 0 ) {
                    msgMethod = "Ya Existe un registro con el Código de Id Interna para este Proyecto !!";

                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _idInternaJson.setFechaCreacion(dateActual);
                    _idInternaJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de la Id Interna y Organizacion
                    _idInternaJson.setIdActividadIdInterna(_tblActividad);
                    _idInternaJson.setIdOrganizacionIdInterna(_tblOrganizacion);

                    // Realizamos la Persistencia de los Datos
                    _actividadIdInternaRepository.save( _idInternaJson );
                    _actividadIdInternaRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "La Id Interna para este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar la Id Interna del Proyecto, con la Organización Indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN



    /**
     * Metodo que Solicita un json con los datos de la Entidad Id Interna con Relacion
     * a Actividades
     * @param  codIdInterna Identificador de la Id Interna a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de la Id Interna
     * @autor Nahum Martinez | NAM
     * @version 11/02/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Id Interna", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = ID_INTERNA_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadIdInterna(@ApiParam(value = "Codigo de la Id Interna del Proyecto a Eliminar", required = true)
                                                              @PathVariable("codIdInterna") String codIdInterna ) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codIdInterna )
            TblActividadIdInterna _tblActividadIdInterna = _actividadIdInternaRepository.findByCodIdInterna( codIdInterna );

            try {
                if( _actividadIdInternaRepository.countByCodIdInterna( codIdInterna ) > 0 ) {
                    // Realizamos la Persistencia de los Datos
                    System.out.println("Codigo de la Id Interna del Proyecto ++++++ " + codIdInterna);
                    System.out.println("Codigo de la Id Interna del Proyecto ++++++ " + _actividadIdInternaRepository.countByCodIdInterna( codIdInterna ));
                    //_actividadIdInternaRepository.delete( _tblActividadIdInterna );
                    _actividadIdInternaRepository.deleletedCodIdInterna( codIdInterna );
                    _actividadIdInternaRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "La Id Interna para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Id Interna para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar la Id Interna del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Id Interna para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN

}
