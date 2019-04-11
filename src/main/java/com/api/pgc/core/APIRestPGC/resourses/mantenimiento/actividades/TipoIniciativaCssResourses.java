/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.mantenimiento.actividades;


import com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades.SectorEjecutorRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades.TipoIniciativaCssRepository;
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
@Api(value = "tipoIniciativaApi" , description = "Operaciones sobre el Modulo de Mantenimiento de Actividades", tags = "Tipo de Iniciativa CSS")
public class TipoIniciativaCssResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    TipoIniciativaCssRepository _tipoIniciativaCssRepository;


    /**
     * Metodo que despliega la Lista de todos los Tipos de Iniciativa de un Proyecto CSS de la BD
     * @autor Nahum Martinez | NAM
     * @version  06/02/2019/v1.0
     * @return Lista de Tipo de Iniciativa de un Proyecto CSS de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Tipo de Iniciativa de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = TIPO_INICIATIVA_ENDPOINT, produces = "application/json; charset=UTF-8" )
    public HashMap<String, Object> getAllTipoIniciativaCss() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Tipos de Iniciativas CSS, para un Proyecto registrados en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _tipoIniciativaCssRepository.findAll() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Tipo de Iniciativa de la BD
     * @autor Nahum Martinez | NAM
     * @version  06/02/2019/v1.0
     * @return Tipo de Iniciativa de la BD
     * @param idTipoIniciativa Identificador del Tipo de Iniciativa a Buscar
     */
    @ApiOperation(value = "Retorna el Tipo de Iniciativa enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = TIPO_INICIATIVA_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getTipoIniciativa( @ApiParam(value="Identificador del Tipo de Iniciativa a Buscar", required=true)
                                               @PathVariable("idTipoIniciativa") long idTipoIniciativa ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if ( _tipoIniciativaCssRepository.findByIdTipoIniciativa(idTipoIniciativa) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Tipo de Iniciativa consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de la Iniciativa Consultada";
                msgExeptions.map.put("data", _tipoIniciativaCssRepository.findByIdTipoIniciativa(idTipoIniciativa));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


}
