/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.ubicacion_geografica;

import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.NivelImplementacionRepository;
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
@Api(value = "NivelImplementacionApi", description = "Operaciones sobre el Modulo de Ubicaciones - Nivel de Implementacion", tags = "Implementacion de Proyecto")
public class NivelImplementacionResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    NivelImplementacionRepository _nivelImplementacionRepository;


    /**
     * Metodo que despliega la Lista de todos los Niveles de implemnetacion de la BD
     *
     * @return Lista de Niveles de Implementacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 20/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Niveles de Implementacion de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = NIVEL_IMPLEMENTACION_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllNivelImplementacion() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Niveles de Implementacion registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _nivelImplementacionRepository.findAll());

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Nivel de Implementacion de la BD
     *
     * @param idNivel Identificador del Nivel de Implementacion a Buscar
     * @return Nivel de Implementacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 20/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Nivel de Implemntacion enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = NIVEL_IMPLEMENTACION_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getNivelImplementacion(@ApiParam(value = "Identificador del Nivel de Implementacion a Buscar", required = true)
                                              @PathVariable("idNivel") long idNivel) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if ( _nivelImplementacionRepository.findByIdNivel(idNivel) == null ) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Nivel de Implementación consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Nivel de Implementación consultado";
                msgExeptions.map.put("data", _nivelImplementacionRepository.findByIdNivel( idNivel));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN
}
