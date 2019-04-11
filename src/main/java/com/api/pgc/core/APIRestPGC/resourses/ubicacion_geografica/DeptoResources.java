/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.ubicacion_geografica;

import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.DeptoRepository;
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
@Api(value = "DeptoApi", description = "Operaciones sobre el Modulo de Ubicaciones | Datos Geográficos", tags = "Datos Geográficos" )
public class DeptoResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    DeptoRepository _deptoRepository;


    /**
     * Metodo que despliega la Lista de todos los Departamentos de la BD
     *
     * @return Lista de Departamentos de la BD
     * @autor Nahum Martinez | NAM
     * @version 18/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Departamentos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = DEPTO_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllDepartamentos() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Departamentos registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _deptoRepository.findAll());

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Departamento de la BD
     *
     * @param idDepartamento Identificador del Estado a Buscar
     * @return Departamento de la BD
     * @autor Nahum Martinez | NAM
     * @version 18/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Departamento enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = DEPTO_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getDepartamento(@ApiParam(value = "Identificador del Departamento a Buscar", required = true)
                                              @PathVariable("idDepartamento") long idDepartamento) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if ( _deptoRepository.findByIdDepartamento(idDepartamento) == null ) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Departamento consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Departamento consultado";
                msgExeptions.map.put("data", _deptoRepository.findByIdDepartamento( idDepartamento ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN
}
