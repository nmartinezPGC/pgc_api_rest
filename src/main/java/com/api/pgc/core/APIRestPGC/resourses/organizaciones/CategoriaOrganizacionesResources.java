/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.organizaciones;


import com.api.pgc.core.APIRestPGC.models.organizaciones.TblCategoriaOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.CategoriaOrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.TipoOrganizacionRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "catOrganizacionapi", description = "Operaciones sobre el Modulo de Categoria de Organizacion", tags = "Categorias de Organizacion")
public class CategoriaOrganizacionesResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    CategoriaOrganizacionRepository _categoriaOrganizacionRepository;

    @Autowired
    TipoOrganizacionRepository _tipoOrganizacionRepository;


    /**
     * Metodo que despliega la Lista de todos los Categorias de Organizaciones de la BD
     *
     * @return Lista de Categorias de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/01/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas las Categorias de Organizaciones de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = CATEGORIA_ORGANIZACIONES_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllCategoriasOrganizaciones() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas las Categorias de Organizaciones registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("totalRecords", _categoriaOrganizacionRepository.count());
            msgExeptions.map.put("data", _categoriaOrganizacionRepository.findAll());

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Categorias de Organizacion de la BD
     *
     * @param idCatOrganizacion Identificador de la Categoria de Organizacion a Buscar
     * @return Categoria de Organizacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/01/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Categoria de Organizacion enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = CATEGORIA_ORGANIZACIONES_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getCategoriaOrganizacion(@ApiParam(value = "Identificador de la Categoria de Organizacion a Buscar", required = true)
                                                       @PathVariable("idCatOrganizacion") long idCatOrganizacion) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_categoriaOrganizacionRepository.findByIdCatOrganizacion(idCatOrganizacion) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado datos de la Categoria de Organizacion consultada";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de la Categoria de Organizacion Consultada";
                msgExeptions.map.put("totalRecords", _categoriaOrganizacionRepository.countByIdCatOrganizacion(idCatOrganizacion));
                msgExeptions.map.put("data", _categoriaOrganizacionRepository.findByIdCatOrganizacion(idCatOrganizacion));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN


    /**
     * Metodo que despliega la Categorias de Organizacion de la BD
     *
     * @param idTipoOrganizacion Identificador de la Categoria de Organizacion a Buscar
     * @return Categoria de Organizacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 22/01/2019/v1.0
     */
    @ApiOperation(value = "Retorna las Categorias de Organizacion enviado a buscar de la BD, segun el Tipo que pertencen", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = CATEGORIA_ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_ORGANIZACION, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getCategoriaTipoOrganizacion(@ApiParam(value = "Identificador de la Categoria de Organizacion a Buscar segun el Tipo de Organizacion que pertence", required = true)
                                                            @PathVariable("idTipoOrganizacion") long idTipoOrganizacion) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Tipo de Organizacion que pertenece la Categoria
        try {
            TblTipoOrganizacion _tblTipoOrganizacion = _tipoOrganizacionRepository.findByIdTipoOrganizacion(idTipoOrganizacion);

            try {
                if (_categoriaOrganizacionRepository.findByIdCatOrganizacion(idTipoOrganizacion) == null) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de la Categoria de Organizacion consultada, con el Tipo enviado";
                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 400);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de las Categorias de Organizacion Consultada, segun el Tipo";
                    msgExeptions.map.put("data", _categoriaOrganizacionRepository.getCategoriasByTipo(_tblTipoOrganizacion));
                    msgExeptions.map.put("totalRecords", _categoriaOrganizacionRepository.countByTipoOrganizacion(_tblTipoOrganizacion));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "No exiten datos de Tipos de Organizaicon asociadas a la Categorias";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No se ha encontrado el Tipo de Organizacion Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN


    /**
     * Metodo que Solicita un json con los datos de la Entidad Categoria de Organizacion
     *
     * @param _categoriaOrganizacionJson Obtiene desde el request los datos del Tipo de Organizacion a ingresar
     * @return Mensaje de Confirmacion de Registro de Tipo de Organizacion
     * @autor Nahum Martinez | NAM
     * @version 13/10/2018/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de la Nueva Categoria", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = CATEGORIA_ORGANIZACIONES_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addCategoriaOrganizacion(@ApiParam(value = "Json de la nueva Categoria de Organizacion a Ingresar", required = true)
                                                       @RequestBody final TblCategoriaOrganizacion _categoriaOrganizacionJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca el Tipo de Organizacion, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoOrganizacion": { "idTipoOrganizacion": valor })
            TblTipoOrganizacion _tblTipoOrganizacion= _tipoOrganizacionRepository.findByIdTipoOrganizacion( _categoriaOrganizacionJson.getIdTipoOrganizacionCat().getIdTipoOrganizacion());

            // Seteo de las Fecha y Hora de Creacion
            _categoriaOrganizacionJson.setFechaCreacion(dateActual);
            _categoriaOrganizacionJson.setHoraCreacion(dateActual);

            // Seteamos el Tipo de Organizacion
            _categoriaOrganizacionJson.setIdTipoOrganizacionCat(_tblTipoOrganizacion);


            // Realizamos la Persistencia de los Datos
            _categoriaOrganizacionRepository.save(_categoriaOrganizacionJson);

            // Retorno de la Funcion
            msgMethod = "Se ha Ingresado de forma satisfactoria!!";

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            msgMethod = "No existe el Tipo de organización que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Categoria de Organizacion
     *
     * @param _categoriaOrganizacionJson Obtiene desde el request los datos de la Categoria de Organizacion a ingresar
     * @param idCatOrganizacion Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro de Categoria de Organizacion
     * @autor Nahum Martinez | NAM
     * @version 28/01/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de la Nueva Categoria", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = CATEGORIA_ORGANIZACIONES_ENDPOINT_EDIT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> putTipoOrganizacion(@ApiParam(value = "Json de la nueva Categoria de Organizacion a Ingresar", required = true)
                                                       @PathVariable("idCatOrganizacion") long idCatOrganizacion,
                                                       @RequestBody final TblCategoriaOrganizacion _categoriaOrganizacionJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        // Buscamos la Categoria de Organizacion Solicitada
        TblCategoriaOrganizacion _tblCategoriaOrganizacion = _categoriaOrganizacionRepository.findByIdCatOrganizacion( idCatOrganizacion );

        try {
            // Buacamos el Tipo de Organizacion segun el Parametro enviado
            TblTipoOrganizacion _tblTipoOrganizacion = _tipoOrganizacionRepository.findByIdTipoOrganizacion( _categoriaOrganizacionJson.getIdTipoOrganizacionCat().getIdTipoOrganizacion() );

            // Realizamos la Persistencia de los Datos
            _tblCategoriaOrganizacion.setAcronimoCatOrganizacion( _categoriaOrganizacionJson.getAcronimoCatOrganizacion() );
            _tblCategoriaOrganizacion.setDescCatOrganizacion( _categoriaOrganizacionJson.getDescCatOrganizacion() );
            _tblCategoriaOrganizacion.setActivo( _categoriaOrganizacionJson.isActivo() );

            _tblCategoriaOrganizacion.setIdTipoOrganizacionCat(_tblTipoOrganizacion);

            _categoriaOrganizacionRepository.save(_tblCategoriaOrganizacion);

            // return tiposRepository.findAll();
            msgMethod = "Se ha Actualizado la Categoria de forma satisfactoria!!";

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            msgMethod = "Hay problemas al momento de Actualizar la Categoria de Organizacion.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | putTipoOrganizacion



    /**
     * Metodo que Solcita un json con los datos de la Entidad Categotia de Organizacion
     * para su Inhabilitacion
     * @param idCatOrganizacion Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro de Categoria de Organizacion
     * @autor Nahum Martinez | NAM
     * @version 28/01/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Id de la Categoria a Inhabilitar", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = CATEGORIA_ORGANIZACIONES_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deleteTipoOrganizacion(@ApiParam(value = "ID de la Categoria de Organizacion a Ingresar", required = true)
                                                          @PathVariable("idCatOrganizacion") long idCatOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        // Buscamos la Categoria de Organizacion Solicitada
        TblCategoriaOrganizacion _tblCategoriaOrganizacion = _categoriaOrganizacionRepository.findByIdCatOrganizacion( idCatOrganizacion );

        try {
            // Realizamos la Persistencia de los Datos
            _tblCategoriaOrganizacion.setActivo( false );

            _categoriaOrganizacionRepository.save(_tblCategoriaOrganizacion);

            // return tiposRepository.findAll();
            msgMethod = "Se ha Inhabiltado la Categoria de Organizacion de forma satisfactoria!!";

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            msgMethod = "Hay problemas al momento de Inhabilitar Categoria de Organizacion.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deleteTipoOrganizacion

}
