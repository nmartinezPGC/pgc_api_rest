package com.api.pgc.core.APIRestPGC.resourses.mantenimiento;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.EstadosRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.GruposRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "estadosapi", description = "Operaciones sobre el Modulo de Estados", tags = "Estados")
public class EstadosResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    EstadosRepository estadosRepository;

    @Autowired
    GruposRepository gruposRepository;

    /**
     * Metodo que despliega la Lista de todos los Estados de la BD
     *
     * @return Lista de Estados de la BD
     * @autor Nahum Martinez | NAM
     * @version 10/04/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Estados de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ESTADOS_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllEst() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Estados registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", estadosRepository.findAll());

            //msgExeptions.map.put( "data", estadosRepository.getEstadosAvtividades() );
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Estado de la BD
     *
     * @param idEstado Identificador del Estado a Buscar
     * @return Estado de la BD
     * @autor Nahum Martinez | NAM
     * @version 11/04/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Estado enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ESTADOS_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getEstados(@ApiParam(value = "Identificador del Estado a Buscar", required = true)
                                              @PathVariable("idEstado") long idEstado) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (estadosRepository.findByIdEstado(idEstado) == null) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Estado consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Estado Consultado";
                msgExeptions.map.put("data", estadosRepository.findByIdEstado(idEstado));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }

        //return estadosRepository.findByIdEstado( idEstado );
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Estados
     *
     * @param estadoJson Obtiene desde el request los datos del estado a ingresar
     * @return Mensaje de Confirmacion de Registro de estado
     * @autor Nahum Martinez | NAM
     * @version 10/04/2018/v1.0
     */
    //@ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Estado")
    //@PostMapping(value = "/estados", produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addEst(@ApiParam(value = "Json del nuevo Estado a Ingresar", required = true)
                                          @RequestBody final TblEstado estadoJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            //Realizamos la Persistencia de los Datos
            estadosRepository.save(estadoJson);

            //return tiposRepository.findAll();
            msgMethod = "Se ha Ingresado de forma satisfactoria!!";

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            msgMethod = "No existe el Grupo que buscas, por favor verfica que el Estado ingresado es correcto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }

        //return estadosRepository.findAll();
    }


    /**
     * Metodo que despliega el Estado de la BD
     *
     * @param idGrupo Identificador del Estado a Buscar
     * @return Estado de la BD
     * @autor Nahum Martinez | NAM
     * @version 11/04/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Estado enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ESTADOS_ENDPOINT_FIND_BY_IDGRUPO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getEstadosGrupo(@ApiParam(value = "Identificador del Grupo a Buscar", required = true)
                                                   @PathVariable("idGrupo") long idGrupo) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscamos el Grupo del Estado
            TblGrupo tblGrupo = gruposRepository.findByIdGrupo(idGrupo);

            if (estadosRepository.getEstadosGrupos(tblGrupo) == null ||
                    estadosRepository.getEstadosGrupos(tblGrupo).isEmpty()) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Estado consultado, con el Grupo solicitado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Estado Consultado";
                msgExeptions.map.put("data", estadosRepository.getEstadosGrupos(tblGrupo));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN

}
