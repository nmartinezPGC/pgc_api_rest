/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.ubicacion_geografica;

import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblNivelImplementacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblNivelUbicacionImplementacion;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.NivelImplementacionRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.NivelUbicacionImplementacionRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.UbicacionImplementacionRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "NivelImplementacionApi", description = "Operaciones sobre el Modulo de Ubicaciones - Ubicación de Implementacion", tags = "Implementacion de Proyecto")
public class UbicacionImplementacionResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    UbicacionImplementacionRepository _ubicacionImplementacionRepository;

    @Autowired
    NivelUbicacionImplementacionRepository _nivelUbicacionImplementacionRepository;

    @Autowired
    NivelImplementacionRepository _nivelImplementacionRepository;


    /**
     * Metodo que despliega la Lista de todos las Ubicaciones Implemnetacion de la BD
     *
     * @return Lista de las Ubicaciones Implementacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos las Ubicaciones Implementacion de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = UBICACION_IMPLEMENTACION_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllUbicacionImplementacion() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos las Ubicaciones de Implementacion registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _ubicacionImplementacionRepository.findAll());

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN


    /**
     * Metodo que despliega la Ubicacion de Implementacion de la BD
     *
     * @param idUbicacion Identificador de la Ubicacion de Implementacion a Buscar
     * @return Ubicacion de Implementacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Ubicacion de Implemntacion enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUbicacionImplementacion(@ApiParam(value = "Identificador de la Ubicacion de Implementacion a Buscar", required = true)
                                              @PathVariable("idUbicacion") long idUbicacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if ( _ubicacionImplementacionRepository.findByIdUbicacionImplementacion(idUbicacion) == null ) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de la Ubicacion de Implementacion consultado";
                msgExeptions.map.put("data", _ubicacionImplementacionRepository.findByIdUbicacionImplementacion( idUbicacion ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN



    /**
     * Metodo que despliega la Ubicacion de Implementacion de la BD
     * segun el Nivel de Implementacion
     * @param idNivelImplementacion Identificador del Nivel de Implementacion a Buscar
     * @return Ubicacion de Implementacion de la BD, segun el Nivel de Implementacion
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Ubicacion de Implemntacion enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID_NIVEL_IMPL, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getFindByIdNivelImplementacion(@ApiParam(value = "Identificador de la Ubicacion de Implementacion a Buscar, segun el Nivel de Implementación", required = true)
                                                                   @PathVariable("idNivelImplementacion") long idNivelImplementacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca el Nivel de Implementacion Solicitado
            TblNivelImplementacion _tblNivelImplementacion = _nivelImplementacionRepository.findByIdNivel( idNivelImplementacion );

            try {
                if ( _ubicacionImplementacionRepository.findByIdNivelImplementacion( _tblNivelImplementacion ) == null) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 400);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de la de Ubicacion de Implementacion consultado";
                    msgExeptions.map.put("data", _ubicacionImplementacionRepository.findByIdNivelImplementacion( _tblNivelImplementacion ));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultada";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Ubicacion de Implementacion de la BD
     * segun el Nivel de Implementacion
     * @param idNivelUbicacion Identificador del Nivel de Ubicacion a Buscar
     * @return Ubicacion de Implementacion de la BD, segun el Nivel de Ubicacion
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Ubicacion de Implemntacion enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID_UBIC_NIVEL_IMPL, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getFindByIdNivelUbicacion(@ApiParam(value = "Identificador de la Ubicacion de Implementacion a Buscar, segun el Nivel de Ubicacion", required = true)
                                                                  @PathVariable("idNivelUbicacion") long idNivelUbicacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca el Nivel de Ubicacion Solicitado
            TblNivelUbicacionImplementacion _tblNivelUbicacionImplementacion = _nivelUbicacionImplementacionRepository.findByIdNivelUbicacion( idNivelUbicacion );

            try {
                if ( _ubicacionImplementacionRepository.findByIdNivelUbicacionImplementacion( _tblNivelUbicacionImplementacion ) == null) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 400);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de la de Ubicacion de Implementacion consultado";
                    msgExeptions.map.put("data", _ubicacionImplementacionRepository.findByIdNivelUbicacionImplementacion( _tblNivelUbicacionImplementacion ));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultada";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Ubicacion de Implementacion de la BD
     * segun el Nivel de Implementacion
     * @param idNivelUbicacion Identificador del Nivel de Ubicacion a Buscar
     * @param idNivelImplementacion Identificador del Nivel de Implementacion a Buscar
     * @return Ubicacion de Implementacion de la BD, segun el Nivel de Implementacion y Nivel de Ubicacion
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Ubicacion de Implemntacion enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID_UBIC_NIVEL_IMPL_2, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getFindByIdNivelImplementacionAndIdNivelUbicacion(@ApiParam(value = "Identificador de la Ubicacion de Implementacion a Buscar, segun el Nivel de Implementacion", required = true)
                                                             @PathVariable("idNivelImplementacion") long idNivelImplementacion,
                                                                                     @ApiParam(value = "Identificador de la Ubicacion de Implementacion a Buscar, segun el Nivel de Ubicacion", required = true)
                                                                                     @PathVariable("idNivelUbicacion") long idNivelUbicacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca el Nivel de Implementacion Solicitado
            TblNivelImplementacion _tblNivelImplementacion = _nivelImplementacionRepository.findByIdNivel( idNivelImplementacion );

            // Busca el Nivel de Ubicacion Solicitado
            TblNivelUbicacionImplementacion _tblNivelUbicacionImplementacion = _nivelUbicacionImplementacionRepository.findByIdNivelUbicacion( idNivelUbicacion );

            try {
                if ( _ubicacionImplementacionRepository.countByIdNivelImplementacionAndIdNivelUbicacion( _tblNivelImplementacion,  _tblNivelUbicacionImplementacion ) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de la de Ubicacion de Implementacion consultado";
                    msgExeptions.map.put("data", _ubicacionImplementacionRepository.findByIdNivelImplementacionAndIdNivelUbicacion( _tblNivelImplementacion,  _tblNivelUbicacionImplementacion ));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultada";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No se ha encontrado dato de la Ubicacion de Implementación consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN
}
