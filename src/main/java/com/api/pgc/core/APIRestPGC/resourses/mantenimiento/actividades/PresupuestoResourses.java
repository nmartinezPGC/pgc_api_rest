package com.api.pgc.core.APIRestPGC.resourses.mantenimiento.actividades;


import com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades.EstrategiasRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades.PresupuestoRepository;
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
@Api(value = "sectorejecutorapi" , description = "Operaciones sobre el Modulo de Mantenimiento de Actividades", tags = "Presupuestos")
public class PresupuestoResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    PresupuestoRepository _presupuestoRepository;


    /**
     * Metodo que despliega la Lista de todos las Estrategias de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  22/08/2018/v1.0
     * @return Lista de Estrategias de una Actividad de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos las Estrategias de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PRESUPUESTO_ENDPOINT, produces = "application/json; charset=UTF-8" )
    public HashMap<String, Object> getAllEstrategia() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Sectores Ejecutores para una Actividad registrados en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _presupuestoRepository.findAll() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Presupuesto de la BD
     * @autor Nahum Martinez | NAM
     * @version  22/08/2018/v1.0
     * @return Estado de la BD
     * @param idPresupuesto Identificador del Presupuesto a Buscar
     */
    @ApiOperation(value = "Retorna el Presupuesto enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PRESUPUESTO_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getEstrategia( @ApiParam(value="Identificador del Presupuesto a Buscar", required=true)
                                               @PathVariable("idPresupuesto") long idPresupuesto ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if ( _presupuestoRepository.findByIdPresupuesto( idPresupuesto) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de la Estrategia Consultada";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de la Estrategia Consultada";
                msgExeptions.map.put("data", _presupuestoRepository.findByIdPresupuesto( idPresupuesto ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


}
