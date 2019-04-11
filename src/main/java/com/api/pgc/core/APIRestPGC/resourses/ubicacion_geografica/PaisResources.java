package com.api.pgc.core.APIRestPGC.resourses.ubicacion_geografica;


import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.PaisRepository;
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
@Api(value = "paisapi", description = "Operaciones sobre el Modulo de Ubicaciones | Datos Geográficos", tags = "Datos Geográficos")
public class PaisResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    PaisRepository paisRepository;


    /**
     * Metodo que despliega la Lista de todos los Paises de la BD
     *
     * @return Lista de Pais de la BD
     * @autor Nahum Martinez | NAM
     * @version 13/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Paises de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PAIS_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllPaises() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Paises registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", paisRepository.findAll());

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Pais de la BD
     *
     * @param idPais Identificador del Estado a Buscar
     * @return Estado de la BD
     * @autor Nahum Martinez | NAM
     * @version 13/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Pais enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PAIS_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getEstados(@ApiParam(value = "Identificador del Pais a Buscar", required = true)
                                              @PathVariable("idPais") long idPais) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (paisRepository.findByIdPais(idPais) == null) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Pais consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Pais Consultado";
                msgExeptions.map.put("data", paisRepository.findByIdPais(idPais));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }

        //return estadosRepository.findByIdEstado( idEstado );
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Pais
     *
     * @param paisJson Obtiene desde el request los datos del pais a ingresar
     * @return Mensaje de Confirmacion de Registro de estado
     * @autor Nahum Martinez | NAM
     * @version 10/04/2018/v1.0
     */
    //@ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Estado")
    //@PostMapping(value = "/estados", produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addPais(@ApiParam(value = "Json del nuevo Pais a Ingresar", required = true)
                                           @RequestBody final TblPais paisJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            //Realizamos la Persistencia de los Datos
            paisRepository.save(paisJson);

            //return tiposRepository.findAll();
            msgMethod = "Se ha Ingresado de forma satisfactoria!!";

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            msgMethod = "No existe el Grupo que buscas, por favor verfica que el Estado ingresado es correcto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }

}
