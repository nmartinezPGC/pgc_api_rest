/*
 * Copyright (c) 2019.  Nahum Martinez
 */

/**
 * @author nahum.martinez
 * @date 07/01/2019
 * @name PerfilesResources
 */

package com.api.pgc.core.APIRestPGC.resourses.seguirdad;

import com.api.pgc.core.APIRestPGC.models.seguridad.TblSecuencia;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.seguridad.SecuenciasRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@ApiIgnore
// @Api(value = "perfilApi" , description = "Operaciones sobre el Modulo de Secuencias")
public class SecuenciaResources {
    // Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    SecuenciasRepository _secuenciasRepository;

    @Autowired
    UsuariosRepository _usuariosRepository;

    // Metodos Principales de la Clase
    /**
     * Metodo que despliega la Lista de todas las Secuencias de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/01/2019/v1.0
     * @return Lista de Secuencias de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todas las Secuencias de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECUENCIAS_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllSecuencias() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            msgMethod = "Listado de todas las Secuencias registrados en la BD";

            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _secuenciasRepository.findAll() );
            msgExeptions.map.put("totalRecords", _secuenciasRepository.count());

            // Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Secuencia Consultada de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/01/2019/v1.0
     * @return Secuencia de la BD, por ID
     * @param idSecuencia
     */
    @ApiOperation(value = "Retorna la Secuencia de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECUENCIAS_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSecuenciaById(@PathVariable ("idSecuencia") long idSecuencia)  throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            msgMethod = "Listado de la Secuencia consultada, por su ID";

            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _secuenciasRepository.findByIdSecuencia(idSecuencia));

            // Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Secuencia Consultada de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/01/2019/v1.0
     * @return Secuencia de la BD, por Codigo
     * @param codSecuencia
     */
    @ApiOperation(value = "Retorna la Secuencia de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECUENCIAS_ENDPOINT_FIND_BY_COD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSecuenciaByCod(@PathVariable ("codSecuencia") String codSecuencia)  throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            msgMethod = "Listado de la Secuencia consultada, por su Código";

            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _secuenciasRepository.findByCodSecuencia(codSecuencia));

            // Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Secuencia Consultada de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/01/2019/v1.0
     * @return Actualizacion del Valor de Secuencia Usada
     * @param idSecuencia
     * @param _secuenciaJsonBean
     */
    @ApiOperation(value = "Actualiza el valor de la Secuencia segun el parametro del Id Enviado", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = SECUENCIAS_ENDPOINT_UPDATE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> updateSecuencia(@RequestBody final TblSecuencia _secuenciaJsonBean,
                                                   @PathVariable ("idSecuencia") long idSecuencia)  throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha y Hora de Ingreso
        Date dateActual = new Date();

        try {
            // Buscamos la Secuencia a Actualizar
            TblSecuencia _tblSecuencia = _secuenciasRepository.findByIdSecuencia(idSecuencia);

            // Buscamos la Secuencia a Actualizar
            TblUsuarios _tblUsuarios= _usuariosRepository.findByIdUsuario( _secuenciaJsonBean.getIdUsuarioMod().getIdUsuario() );

            try {
                // Seteo de los Campos a Modificar
                _tblSecuencia.setValor1( _tblSecuencia.getValor2());

                _tblSecuencia.setValor1( _tblSecuencia.getValor2());
                _tblSecuencia.setValor2( _tblSecuencia.getValor2() + 1);

                _tblSecuencia.setFechaActualizacion( dateActual );
                _tblSecuencia.setHoraActualizacion( dateActual);

                // Realizamos la Persistencia de los Datos
                _secuenciasRepository.save(_tblSecuencia);
                _secuenciasRepository.flush();

                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Secuencia Actualizada Exitosamente !!";
                msgExeptions.map.put("data", _secuenciasRepository.findByIdSecuencia(idSecuencia));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } catch (Exception ex) {
                msgMethod = ex.toString();
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No se ha Podido encontrar la Secuencia solicitada !!";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | updateSecuencia
}