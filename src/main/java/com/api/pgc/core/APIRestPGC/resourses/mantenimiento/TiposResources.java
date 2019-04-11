package com.api.pgc.core.APIRestPGC.resourses.mantenimiento;

//Imports de la Clase
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.GruposRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TiposRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "tiposapi" , description = "Operaciones sobre el Modulo de Tipos", tags = "Tipos")
public class TiposResources {

    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    TiposRepository tiposRepository;

    @Autowired
    GruposRepository gruposRepository;

    /**
     * Metodo que despliega la Lista de todos los Tipos de la BD
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Lista de Grupos de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Tipos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = TIPOS_ENDPOINT, produces = "application/json")
    public HashMap<String, Object>  getAllTipo() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Tipos registrados en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", tiposRepository.findAll() );
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
     * @param idTipo Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el Tipo enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado")})
    @GetMapping( value = TIPOS_ENDPOINT_FIND_BY_ID, produces = "application/json")
    public HashMap<String, Object> getTipo( @ApiParam(value="Identificador del Tipo a Buscar", required=true)
                                                @PathVariable ("idTipo") long idTipo  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( tiposRepository.findByIdTipo(idTipo) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Tipo consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Tipo Consultado";
                msgExeptions.map.put("data", tiposRepository.findByIdTipo(idTipo));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Tipo segun el Grupo de la BD
     * @autor Nahum Martinez | NAM
     * @version  21/01/2019/v1.0
     * @return Tipo de la BD
     * @param idGrupo Identificador del Grupo a Buscar
     */
    @ApiOperation(value = "Retorna el Tipo enviado a buscar de la BD, segun el Grupo que pertenece", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping( value = TIPOS_ENDPOINT_FIND_BY_IDGRUPO, produces = "application/json")
    public HashMap<String, Object> getTipoByGrupo( @ApiParam(value="Identificador del Tipo a Buscar, segun el Grupo Solicitado", required=true)
                                            @PathVariable ("idGrupo") long idGrupo  ) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            // Busca el Grupo, desde el Reporsitorio con el Parametro del Json enviado ( "idGrupo": valor )
            TblGrupo _tblGrupo = gruposRepository.findByIdGrupo( idGrupo );

            if( tiposRepository.findByIdGrupo(_tblGrupo) == null ){
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Tipo consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Tipo Consultado";
                msgExeptions.map.put("data", tiposRepository.findByIdGrupo(_tblGrupo));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Tipos
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Mensaje de Confirmacion de Registro de tipo
     * @param tipoJson Obtiene desde el request los datos del tipo a ingresar
     */
    //@ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Tipo",
            //notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Grupo")
    //@PostMapping(value = "/tipos", produces = "application/json")
    public HashMap<String, Object> addTipo(@ApiParam(value="Json del nuevo Tipo a Ingresar, con Grupo asociado", required=true)
                                       @RequestBody final TblTipo tipoJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca el Grupo, desde el Reporsitorio con el Parametro del Json enviado ( "idGrupo": { "idGrupo": valor })
            TblGrupo tG = gruposRepository.findByIdGrupo( tipoJson.getIdGrupo().getIdGrupo() );

            //Graba los Datos de Tipos
            try {
                //Setea el valor Buscado de la Entidad Grupos
                tipoJson.setIdGrupo(tG);

                //Realizamos la Persistencia de los Datos
                tiposRepository.save(tipoJson);

                //return tiposRepository.findAll();
                msgMethod = "Se ha Ingresado de forma satisfactoria!!";

                //Retorno del json
                return msgExeptions.msgJson( msgMethod, 200 );

            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Tipo";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "No existe el Tipo que buscas, por favor verfica que el Grupo ingresado es correcto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN

}
