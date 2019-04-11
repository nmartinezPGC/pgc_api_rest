/*
 * Copyright (c) 2019.  Nahum Martinez
 */

/**
 * @author nahum.martinez
 * @date 07/01/2019
 * @name PerfilesResources
 */

package com.api.pgc.core.APIRestPGC.resourses.seguirdad;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblPerfiles;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.GruposRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TiposRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.PerfilesRepository;
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
@Api(value = "perfilApi" , description = "Operaciones sobre el Modulo de Perfiles", tags = "Seguridad")
public class PerfilesResources {
    // Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    PerfilesRepository _perfilesRepository;

    @Autowired
    TiposRepository _tiposRepository;

    @Autowired
    GruposRepository _gruposRepository;

    // Metodos Principales de la Clase
    /**
     * Metodo que despliega la Lista de todos los Perfiles de la BD
     * @autor Nahum Martinez | NAM
     * @version  07/01/2019/v1.0
     * @return Lista de perfiles de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Perfiles de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PERFILES_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllPerfiles() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            msgMethod = "Listado de todos los Perfiles registrados en la BD";

            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _perfilesRepository.findAll() );
            msgExeptions.map.put("totalRecords", _perfilesRepository.count());

            System.out.println("sdsdsdsdsdsd");

            // Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Lista de todos los Tipo de Perfiles de la BD
     * @autor Nahum Martinez | NAM
     * @version  08/01/2019/v1.0
     * @return Lista de Tipos de Perfiles de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Tipos de Perfiles de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PERFILES_ENDPOINT_TIPOS, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllTiposPerfiles() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();
        // Busca el Grupo, desde el Reporsitorio con el Parametro del Json enviado ( idGrupo = 1)
        TblGrupo TGrupo = _gruposRepository.findByIdGrupo(1);

        try{
            msgMethod = "Listado de todos los Tipos de Perfiles registrados en la BD";

            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _tiposRepository.findByIdGrupo(TGrupo));
            msgExeptions.map.put("totalRecords", _tiposRepository.getCountTiposPerfiles(TGrupo));

            // Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Ingresa un json con los datos de la Entidad Perfiles
     * @autor Nahum Martinez | NAM
     * @version  07/01/2019/v1.0
     * @return Mensaje de Confirmacion de Registro de Perfil
     * @param perfilJson Obtiene desde el request los datos del perfil a Ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Perfil",
            notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Datos de Relacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = PERFILES_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addPerfil(@ApiParam(value="Json del nuevo Perfil a Ingresar, con Relacion asociado", required=true)
                                              @RequestBody final TblPerfiles perfilJson ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca el Tipo, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoPerfil": { "idTipo": valor })
            TblTipo tTipo = _tiposRepository.findByIdTipo( perfilJson.getIdTipoPerfil().getIdTipo() );

            //Graba los Datos de Perfil
            try {
                //Setea el valor Buscando de la Entidad Perfil
                perfilJson.setCodPerfil(perfilJson.getCodPerfil());
                perfilJson.setActivado(true);

                //Seteo de las Fecha y Hora de Creacion
                perfilJson.setDescPerfil(perfilJson.getDescPerfil());
                perfilJson.setIdTipoPerfil(tTipo);

                // Realizamos la Persistencia de los Datos
                _perfilesRepository.save(perfilJson);

                //return tiposRepository.findAll();
                msgMethod = "Se ha Ingresado de forma satisfactoria!!";

                //Retorno del json
                return msgExeptions.msgJson( msgMethod, 200 );
            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Perfil";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "Ha Ocurrido un error al Intentar Grabar el Perfil";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } /** FIN */


    /**
     * Metodo que Actualiza un json con los datos de la Entidad Perfiles
     * @autor Nahum Martinez | NAM
     * @version  11/01/2019/v1.0
     * @return Mensaje de Confirmacion de Registro de Perfil
     * @param _perfilJsonBean Obtiene desde el request los datos del perfil a Actualizar
     * @param idPerfil Identificador del Perfil
     */
    @ApiOperation(value = "Actualiza a la BD, la Información enviada por el Bean del Perfil",
            notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Datos de Relacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = PERFILES_ENDPOINT_EDIT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editPerfil(@ApiParam(value="Json del Perfil a Actualizar, con Relacion el Tipo que pertenece", required=true)
                                             @RequestBody final TblPerfiles _perfilJsonBean, @PathVariable ("idPerfil") long idPerfil) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Busca el perfil que ha solicitado Actualizacion
        TblPerfiles _tblPerfiles = _perfilesRepository.findByIdPerfil( idPerfil );

        try {
            // Busca el Tipo, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoPerfil": { "idTipo": valor })
            TblTipo tTipo = _tiposRepository.findByIdTipo( _perfilJsonBean.getIdTipoPerfil().getIdTipo() );

            //Graba los Datos de Perfil
            try {
                //Setea el valor Buscando de la Entidad Perfil
                _tblPerfiles.setCodPerfil( _perfilJsonBean.getCodPerfil() );
                // _tblPerfiles.setActivado(true);

                //Seteo de las Fecha y Hora de Creacion
                _tblPerfiles.setDescPerfil( _perfilJsonBean.getDescPerfil() );
                _tblPerfiles.setIdTipoPerfil( tTipo );

                // Realizamos la Persistencia de los Datos
                _perfilesRepository.save( _tblPerfiles );

                //return tiposRepository.findAll();
                msgMethod = "Se ha Actualizado de forma Satisfactoria el Perfil !!";

                //Retorno del json
                return msgExeptions.msgJson( msgMethod, 200 );
            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Actualizar el Perfil";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "Ha Ocurrido un error al Intentar Actualizar el Perfil";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } /** FIN */


    /**
     * Metodo que Inhabilita un json con los datos de la Entidad Perfiles
     * @autor Nahum Martinez | NAM
     * @version  15/01/2019/v1.0
     * @return Mensaje de Confirmacion de Registro de Perfil
     * @param idPerfil Identificador del Perfil
     */
    @ApiOperation(value = "Inhabilita a la BD, la Información enviada por el Bean del Perfil",
            notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Datos de Relacion", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = PERFILES_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletePerfil(@ApiParam(value="Json del Perfil a Inhabilitar, con Relacion asociado", required=true)
                                              @PathVariable ("idPerfil") long idPerfil) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Busca el perfil que ha solicitado Actualizacion
        TblPerfiles _tblPerfiles = _perfilesRepository.findByIdPerfil( idPerfil );

        try {

            //Graba los Datos de Perfil
            try {
                _tblPerfiles.setActivado(false);

                // Realizamos la Persistencia de los Datos
                _perfilesRepository.save( _tblPerfiles );

                //return tiposRepository.findAll();
                msgMethod = "Se ha Inhabilitado de forma Satisfactoria el Perfil !!";

                //Retorno del json
                return msgExeptions.msgJson( msgMethod, 200 );
            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Inhabilitar el Perfil";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "Ha Ocurrido un error al Intentar Inhabilitar el Perfil";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } /** FIN */

}