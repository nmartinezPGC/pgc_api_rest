package com.api.pgc.core.APIRestPGC.resourses.mantenimiento.actividades;


import com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades.SectorEjecutorRepository;
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
@Api(value = "sectorejecutorapi" , description = "Operaciones sobre el Modulo de Mantenimiento de Actividades", tags = "Sectores Ejecutores")
public class SectorEjecutorResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    SectorEjecutorRepository _sectorEjecutorRepository;


    /**
     * Metodo que despliega la Lista de todos los Sectores de Ejecucion de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  22/08/2018/v1.0
     * @return Lista de Sectores de Ejecucion de una Actividad de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Sectores de Ejecucion de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTOR_EJECUTOR_ENDPOINT, produces = "application/json; charset=UTF-8" )
    public HashMap<String, Object> getAllSectorEjecutor() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Sectores Ejecutores para una Actividad registrados en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _sectorEjecutorRepository.findAll() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Sector Ejecutor de la BD
     * @autor Nahum Martinez | NAM
     * @version  22/08/2018/v1.0
     * @return Estado de la BD
     * @param idSectorEjecutor Identificador del Sector Ejecutor a Buscar
     */
    @ApiOperation(value = "Retorna el Sector Ejecutor enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTOR_EJECUTOR_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorEjecutor( @ApiParam(value="Identificador del Sector Ejecutor a Buscar", required=true)
                                               @PathVariable("idSectorEjecutor") long idSectorEjecutor ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if ( _sectorEjecutorRepository.findByIdSectorEjecutor( idSectorEjecutor ) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Sector Ejecutor consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Secotr Ejecutor Consultado";
                msgExeptions.map.put("data", _sectorEjecutorRepository.findByIdSectorEjecutor( idSectorEjecutor ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


}
