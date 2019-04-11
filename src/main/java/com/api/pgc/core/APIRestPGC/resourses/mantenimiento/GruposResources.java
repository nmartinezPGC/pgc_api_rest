package com.api.pgc.core.APIRestPGC.resourses.mantenimiento;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.GruposRepository;
import com.api.pgc.core.APIRestPGC.utilities.configAPI;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.API_BASE_PATH;
import static com.api.pgc.core.APIRestPGC.utilities.configAPI.GRUPOS_ENDPOINT;
import static com.api.pgc.core.APIRestPGC.utilities.configAPI.GRUPOS_ENDPOINT_FIND_BY_ID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH )
@Api(value = "gruposapi" , description = "Operaciones sobre el Modulo de Grupos", tags = "Grupos")
public class GruposResources {

    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    GruposRepository gruposRepository;

    private configAPI configApi;

    /**
     * Metodo que despliega la Lista de todos los Grupos de la BD
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Lista de Grupos de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Grupos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = GRUPOS_ENDPOINT, produces = "application/json")
    public HashMap<String, Object> getAllGroup() throws Exception  {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Grupos registrados en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", gruposRepository.findAll() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Grupo de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/04/2018/v1.0
     * @return Grupo de la BD
     * @param idGrupo Identificador del Grupo a Buscar
     */
    @ApiOperation(value = "Retorna el Grupo enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = GRUPOS_ENDPOINT_FIND_BY_ID, produces = "application/json")
    public HashMap<String, Object> getGroup(@ApiParam(value="Identificador del Grupo a Buscar", required=true)
                                  @PathVariable ("idGrupo") long idGrupo  ) throws Exception  {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( gruposRepository.findByIdGrupo( idGrupo ) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Grupo consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Grupo Consultado";
                msgExeptions.map.put("data", gruposRepository.findByIdGrupo(idGrupo));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Grupos
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Mensaje de Confirmacion de Registro de grupo
     * @param grupoJson Obtiene desde el request los datos del grupo a ingresar
     */
    //@ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Grupo")
    //@PostMapping(value = "/grupos", produces = "application/json")
    public HashMap<String, Object> addGroup( @ApiParam(value="Json del nuevo Grupo a Ingresar", required=true)
                                        @RequestBody final TblGrupo grupoJson) throws Exception  {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            //Realizamos la Persistencia de los Datos
            gruposRepository.save(grupoJson);

            //return tiposRepository.findAll();
            msgMethod = "Se ha Ingresado de forma satisfactoria!!";

            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            msgMethod = "No existe el Grupo que buscas, por favor verfica que el Grupo ingresado es correcto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN

}
