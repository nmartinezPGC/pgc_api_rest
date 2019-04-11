/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.ubicacion_geografica;

import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblNivelImplementacion;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.NivelImplementacionRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.NivelUbicacionImplementacionRepository;
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
@Api(value = "NivelImplementacionApi", description = "Operaciones sobre el Modulo de Ubicaciones - Nivel de Ubicación de Implementacion", tags = "Implementacion de Proyecto")
public class NivelUbicacionImplementacionResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    NivelUbicacionImplementacionRepository _nivelUbicacionImplementacionRepository;

    @Autowired
    NivelImplementacionRepository _nivelImplementacionRepository;


    /**
     * Metodo que despliega la Lista de todos los Niveles de Ubicaciones implemnetacion de la BD
     *
     * @return Lista de Niveles de Ubicaciones Implementacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 20/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Niveles de Ubicaciones Implementacion de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = NIVEL_UBICACION_IMPLEMENTACION_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllNivelUbicacionImplementacion() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Niveles de Ubicaciones de Implementacion registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _nivelUbicacionImplementacionRepository.findAll());

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Nivel de Ubicacion de Implementacion de la BD
     *
     * @param idNivelUbicacion Identificador del Nivel de Implementacion a Buscar
     * @return Nivel de Ubicacion de Implementacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 20/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Nivel de Ubicacion de Implemntacion enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = NIVEL_UBICACION_IMPLEMENTACION_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getNivelUbicacionImplementacion(@ApiParam(value = "Identificador del Nivel de Ubicacion de Implementacion a Buscar", required = true)
                                              @PathVariable("idNivelUbicacion") long idNivelUbicacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if ( _nivelUbicacionImplementacionRepository.findByIdNivelUbicacion(idNivelUbicacion) == null ) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Nivel de Ubicacion de Implementación consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Nivel de Ubicacion de Implementacion consultado";
                msgExeptions.map.put("data", _nivelUbicacionImplementacionRepository.findByIdNivelUbicacion( idNivelUbicacion ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN



    /**
     * Metodo que despliega el Nivel de Ubicacion de Implementacion de la BD
     * segun el Nivel de Implementacion
     * @param idNivelImplementacion Identificador del Nivel de Implementacion a Buscar
     * @return Nivel de Ubicacion de Implementacion de la BD, segun el Nivel de Implementacion
     * @autor Nahum Martinez | NAM
     * @version 20/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Nivel de Ubicacion de Implemntacion enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = NIVEL_UBICACION_IMPLEMENTACION_FIND_BY_ID_NIVEL_IMPL, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getFindByIdNivelImplementacion(@ApiParam(value = "Identificador del Nivel de Ubicacion de Implementacion a Buscar, segun el Nivel de Implementación", required = true)
                                                                   @PathVariable("idNivelImplementacion") long idNivelImplementacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca el Nivel de Implementacion Solicitado
            TblNivelImplementacion _tblNivelImplementacion = _nivelImplementacionRepository.findByIdNivel( idNivelImplementacion );

            try {
                if ( _nivelUbicacionImplementacionRepository.findByIdNivelImplementacion( _tblNivelImplementacion ) == null) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado dato del Nivel de Ubicacion de Implementación consultado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 400);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle del Nivel de Ubicacion de Implementacion consultado";
                    msgExeptions.map.put("data", _nivelUbicacionImplementacionRepository.findByIdNivelImplementacion( _tblNivelImplementacion ));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "No se ha encontrado dato del Nivel de Ubicacion de Implementación consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No se ha encontrado dato del Nivel de Implementación consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN
}
