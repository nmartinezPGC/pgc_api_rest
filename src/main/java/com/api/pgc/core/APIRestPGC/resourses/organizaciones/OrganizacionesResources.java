package com.api.pgc.core.APIRestPGC.resourses.organizaciones;


import com.api.pgc.core.APIRestPGC.models.organizaciones.TblCategoriaOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadIdInternaRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.CategoriaOrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.GrupoOrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.OrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.TipoOrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.PaisRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "organizacionapi", description = "Operaciones sobre el Modulo de Organizacion", tags = "Organizaciones")
public class OrganizacionesResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    OrganizacionRepository organizacionRepository;

    @Autowired
    TipoOrganizacionRepository tipoOrganizacionRepository;

    @Autowired
    GrupoOrganizacionRepository grupoOrganizacionRepository;

    @Autowired
    PaisRepository paisRepository;

    @Autowired
    CategoriaOrganizacionRepository _categoriaOrganizacionRepository;

    @Autowired
    ActividadIdInternaRepository _actividadIdInternaRepository;



    /**
     * Metodo que despliega la Lista de todos las Organizaciones de la BD
     *
     * @return Lista de las Organizaciones de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas las Organizaciones de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllOrganizaciones() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todass las Organizaciones registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            if (organizacionRepository.findAll().isEmpty() || organizacionRepository.findAll() == null) {
                msgMethod = "No Existen, Organizaciones resgitradas en la Base de Daros, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", organizacionRepository.findAll( new Sort(Sort.Direction.DESC, "<idOrganizacion>" )));
                msgExeptions.map.put("totalRecors", organizacionRepository.count());
            } else {
                msgExeptions.map.put("data", organizacionRepository.findAll());
                msgExeptions.map.put("totalRecors", organizacionRepository.count());
            }

            //Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param idOrganizacion Identificador de Organizacion a Buscar
     * @return Organizacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna la Organizacion enviada a buscar de la BD, con el ID como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getOrganizacionById(@ApiParam(value = "Identificador de Organizacion a Buscar", required = true)
                                                   @PathVariable("idOrganizacion") long idOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (organizacionRepository.findByIdOrganizacion(idOrganizacion) == null) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Organizacion consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Organizacion Consultado";
                msgExeptions.map.put("data", organizacionRepository.findByIdOrganizacion(idOrganizacion));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN



    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param codOrganizacion Identificador de Organizacion a Buscar
     * @return Organizacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 09/01/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Organizacion enviado a buscar el Codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_CODIGO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getOrganizacionByCodigo(@ApiParam(value = "Código de Organizacion a Buscar", required = true)
                                                           @PathVariable("codOrganizacion") String codOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (organizacionRepository.findByCodOrganizacion(codOrganizacion) == null) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Organizacion consultada";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Organizacion Consultada";
                msgExeptions.map.put("data", organizacionRepository.findByCodOrganizacion(codOrganizacion));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad de Organizacion
     *
     * @param organizacionJson Obtiene desde el request los datos de Organizacion a ingresar
     * @return Mensaje de Confirmacion de Registro de Organizacion
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de la nueva Organizacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ORGANIZACIONES_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addOrganizacion(@ApiParam(value = "Json de la nueva Organizacion a Ingresar", required = true)
                                                   @RequestBody final TblOrganizacion organizacionJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscar el Tipo de Organizacion
            TblTipoOrganizacion tblTipoOrganizacion = tipoOrganizacionRepository.findByIdTipoOrganizacion(organizacionJson.getIdTipoOrganizacion().getIdTipoOrganizacion());

            // Buscar el Grupo de Organizacion
            // TblGrupoOrganizacion tblGrupoOrganizacion = grupoOrganizacionRepository.findByIdGrupoOrganizacion(organizacionJson.getIdGrupoOrganizazion().getIdGrupoOrganizacion());

            // Buscar el Categoria de Organizacion
            TblCategoriaOrganizacion _tblCategoriaOrganizacion= _categoriaOrganizacionRepository.findByIdCatOrganizacion( organizacionJson.getIdCatOrganizacion().getIdCatOrganizacion() );

            if (tblTipoOrganizacion.isActivo() == true) {
                // Buscar el el Pais de Organizacion
                TblPais tblPais = paisRepository.findByIdPais(organizacionJson.getIdPaisOrganizacion().getIdPais());

                if (tblPais.getInicialesPais() != null) {
                    //Setea el valor Buscando de la Entidad Tipos de Usuario
                    organizacionJson.setIdTipoOrganizacion(tblTipoOrganizacion);
                    organizacionJson.setIdPaisOrganizacion(tblPais);
                    // organizacionJson.setIdGrupoOrganizazion(tblGrupoOrganizacion);
                    organizacionJson.setIdCatOrganizacion(_tblCategoriaOrganizacion);

                    //Realizamos la Persistencia de los Datos
                    organizacionRepository.save(organizacionJson);

                    //return organizacionRepository.findAll();
                    msgMethod = "Se ha Ingresado de forma satisfactoria!!";
                } else {
                    msgMethod = "No existe el Pais de Organizacion que buscas, por favor verfica que el Identificador correcto ingresado es correcto.";
                }
            } else {
                msgMethod = "El Tipo de Organización que buscas, no Existe o no esta Habilitado.";
            }

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            msgMethod = "Los datos Ingresados no son los correctos, pofavor verifica que correspondan a la informacion utilizada en la BD.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }


    /**
     * Metodo que Solcita un json con los datos de la Entidad de Organizacion
     *
     * @param _organizacionJson Obtiene desde el request los datos de la Organizacion a ingresar
     * @param idOrganizacion Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro de la Organizacion
     * @autor Nahum Martinez | NAM
     * @version 14/02/2019/v1.0
     */
    @ApiOperation(value = "Actualiza a la BD, la Información enviada por el Bean de la Organizacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = ORGANIZACIONES_ENDPOINT_EDIT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editOrganizacion(@ApiParam(value = "Json de la Organizacion a Ingresar", required = true)
                                                       @PathVariable("idOrganizacion") long idOrganizacion,
                                                       @RequestBody final TblOrganizacion _organizacionJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingreso
        Date dateActual = new Date();

        // Buscamos la Organizacion solicitada para la Modificacion
        try {
            // Buacamos la Organizacion segun el Parametro enviado
            TblOrganizacion _tblOrganizacion = organizacionRepository.findByIdOrganizacion(idOrganizacion);

            if ( organizacionRepository.countByIdOrganizacion ( idOrganizacion ) > 0 ) {
                // Buacamos el Tipo Organizacion segun el Parametro enviado
                TblTipoOrganizacion _tblTipoOrganizacion = tipoOrganizacionRepository.findByIdTipoOrganizacion( _organizacionJson.getIdTipoOrganizacion().getIdTipoOrganizacion() );

                // Buacamos el Cat Organizacion segun el Parametro enviado
                TblCategoriaOrganizacion _tblCategoriaOrganizacion = _categoriaOrganizacionRepository.findByIdCatOrganizacion( _organizacionJson.getIdCatOrganizacion().getIdCatOrganizacion() );

                // Buacamos el Pais segun el Parametro enviado
                TblPais _tblPais = paisRepository.findByIdPais( _organizacionJson.getIdPaisOrganizacion().getIdPais() );

                try {
                    // Realizamos la Persistencia de los Datos
                    _tblOrganizacion.setActivo( _organizacionJson.isActivo() );
                    _tblOrganizacion.setAdministradorFinanciero( _organizacionJson.isAdministradorFinanciero() );
                    _tblOrganizacion.setSocioDesarrollo(_organizacionJson.isSocioDesarrollo());
                    _tblOrganizacion.setUnidadEjecutora(_organizacionJson.isUnidadEjecutora());
                    _tblOrganizacion.setAgenciaBeneficiaria(_organizacionJson.isAgenciaBeneficiaria());

                    _tblOrganizacion.setInicalesOrganizacion(_organizacionJson.getInicalesOrganizacion());
                    _tblOrganizacion.setNombreOrganizacion(_organizacionJson.getNombreOrganizacion());
                    _tblOrganizacion.setDescOrganizacion(_organizacionJson.getDescOrganizacion());
                    _tblOrganizacion.setDireccionFisicaOrganizacion(_organizacionJson.getDireccionFisicaOrganizacion());
                    _tblOrganizacion.setTelefonoOrganizacion(_organizacionJson.getTelefonoOrganizacion());
                    _tblOrganizacion.setEmailOrganizacion(_organizacionJson.getEmailOrganizacion());
                    _tblOrganizacion.setContactoReferencia(_organizacionJson.getContactoReferencia());
                    _tblOrganizacion.setWebOrganizacion(_organizacionJson.getWebOrganizacion());

                    _tblOrganizacion.setIdCatOrganizacion(_tblCategoriaOrganizacion);
                    _tblOrganizacion.setIdPaisOrganizacion(_tblPais);
                    _tblOrganizacion.setIdTipoOrganizacion(_tblTipoOrganizacion);

                    organizacionRepository.save( _tblOrganizacion );
                    organizacionRepository.flush();

                    // return Repository
                    msgMethod = "Se ha Actualizado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "Hay problemas al momento de Actualizar la Organizacion.";
                    throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } else {
                //Retorno del json
                msgMethod = "No se encuentra una Organizacion con el parametro enviado !!";
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            msgMethod = "Hay problemas al momento de Actualizar la Organizacion.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | editOrganizacion



    /**
     * Metodo que despliega la Organizacion de la BD, enviando el Tipo de Organizacion
     *
     * @param idTipoOrganizacion Identificador del Estado a Buscar
     * @return Organizaciones de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna las Organizaciones enviado a buscar de la BD, por el Tipo Indicado", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getTipoOrganizacion(@ApiParam(value = "Identificador del Tipo de Organizacion a Buscar", required = true)
                                                       @PathVariable("idTipoOrganizacion") long idTipoOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscamos el Tipo de Organizacion
            TblTipoOrganizacion tblTipoOrganizacion = tipoOrganizacionRepository.findByIdTipoOrganizacion(idTipoOrganizacion);

            if (organizacionRepository.getTipoOrganizacion(tblTipoOrganizacion) == null ||
                    organizacionRepository.getTipoOrganizacion(tblTipoOrganizacion).isEmpty()) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se han encontrado dato de las Organizaciones consultadas, con el Tipo solicitado, por favor ingresa un Tipo de Organizacion valido";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", organizacionRepository.getTipoOrganizacion(tblTipoOrganizacion));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de las Organizaciones Consultadas";
                msgExeptions.map.put("data", organizacionRepository.getTipoOrganizacion(tblTipoOrganizacion));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Organizacion de la BD, enviando el Tipo de Organizacion y Pais
     *
     * @param idTipoOrganizacion Identificador del Tipo y Pais de Organizacion a Buscar
     * @param idPaisOrganizacion Identificador del Pais de Organizacion a Buscar
     * @return Organizaciones de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna las Organizaciones enviado a buscar de la BD, por el Tipo y Pais Indicado", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_PAIS, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getTipoPaisOrganizacion(@ApiParam(value = "Identificador del Tipo y Pais de Organizacion a Buscar", required = true)
                                                           @PathVariable("idTipoOrganizacion") long idTipoOrganizacion,
                                                           @PathVariable("idPaisOrganizacion") long idPaisOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscamos el Tipo de Organizacion
            TblTipoOrganizacion tblTipoOrganizacion = tipoOrganizacionRepository.findByIdTipoOrganizacion(idTipoOrganizacion);
            TblPais tblPais = paisRepository.findByIdPais(idPaisOrganizacion);

            if (organizacionRepository.getTipoPaisOrganizacion(tblTipoOrganizacion, tblPais).isEmpty()) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se han encontrado dato de las Organizaciones consultadas, con el Tipo y Pais solicitado, porfavor ingresa un Tipo y Pais de Organizacion valido";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", organizacionRepository.getTipoPaisOrganizacion(tblTipoOrganizacion, tblPais));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de las Organizaciones Consultadas";
                msgExeptions.map.put("data", organizacionRepository.getTipoPaisOrganizacion(tblTipoOrganizacion, tblPais));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN



    /**
     * Metodo que despliega la Organizacion de la BD, enviando el Tipo de Organizacion, Pais
     * y Categoria
     * @param idTipoOrganizacion Identificador del Tipo y Pais de Organizacion a Buscar
     * @param idPaisOrganizacion Identificador del Pais de Organizacion a Buscar
     * @param idCategoriaOrganizacion Identificador de la Categoria de Organizacion a Buscar
     * @return Organizaciones de la BD
     * @autor Nahum Martinez | NAM
     * @version 23/01/2019/v1.0
     */
    @ApiOperation(value = "Retorna las Organizaciones enviado a buscar de la BD, por el Tipo, Pais y Categoria Indicado", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_PAIS_CATEGORIA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getTipoPaisCategoriaOrganizacion(@ApiParam(value = "Identificador del Tipo, Pais y Categoria de Organizacion a Buscar", required = true)
                                                           @PathVariable("idTipoOrganizacion") long idTipoOrganizacion,
                                                           @PathVariable("idPaisOrganizacion") long idPaisOrganizacion,
                                                           @PathVariable("idCategoriaOrganizacion") long idCategoriaOrganizacion) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscamos el Tipo, Pais y Categoria de Organizacion
            TblTipoOrganizacion tblTipoOrganizacion = tipoOrganizacionRepository.findByIdTipoOrganizacion(idTipoOrganizacion);
            TblPais tblPais = paisRepository.findByIdPais(idPaisOrganizacion);
            TblCategoriaOrganizacion tblCategoriaOrganizacion = _categoriaOrganizacionRepository.findByIdCatOrganizacion(idCategoriaOrganizacion);

            if (organizacionRepository.getTipoPaisCatOrganizacion(tblTipoOrganizacion, tblPais, tblCategoriaOrganizacion).isEmpty()) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se han encontrado dato de las Organizaciones consultadas, con el Tipo, Pais y Categoria solicitado, por favor verifica un Tipo, Pais o Categoria de Organizacion valido";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", organizacionRepository.getTipoPaisCatOrganizacion(tblTipoOrganizacion, tblPais, tblCategoriaOrganizacion));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de las Organizaciones Consultadas, segun los criterio enviados";
                msgExeptions.map.put("data", organizacionRepository.getTipoPaisCatOrganizacion(tblTipoOrganizacion, tblPais, tblCategoriaOrganizacion));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Organizacion de la BD, enviando el Pais de Organizacion
     *
     * @param idPaisOrganizacion Identificador del Estado a Buscar
     * @return Organizaciones de la BD
     * @autor Nahum Martinez | NAM
     * @version 20/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna las Organizaciones enviado a buscar de la BD, por el Pais Indicado", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_ID_PAIS, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPaisOrganizacion(@ApiParam(value = "Identificador del Pais de Organizacion a Buscar", required = true)
                                                       @PathVariable("idPaisOrganizacion") long idPaisOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscamos el Pais de Organizacion
            TblPais tblPaisOrganizacion = paisRepository.findByIdPais(idPaisOrganizacion);

            if (organizacionRepository.getPaisOrganizacion(tblPaisOrganizacion) == null ||
                    organizacionRepository.getPaisOrganizacion(tblPaisOrganizacion).isEmpty()) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se has encontrado dato de las Organizaciones consultadas, con el Pais solicitado, porfavor ingresa un pais de Organizacion valido";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", organizacionRepository.getPaisOrganizacion(tblPaisOrganizacion));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de las Organizaciones Consultadas, con el Pais como parametro";
                msgExeptions.map.put("data", organizacionRepository.getPaisOrganizacion(tblPaisOrganizacion));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN



    /**
     * Metodo que Solcita un json con los datos de la Entidad de Organizacion
     *
     * @param idOrganizacion Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro de la Organizacion
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @ApiOperation(value = "Inhabilita a la BD, la Información enviada por el Bean de la Organizacion", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = ORGANIZACIONES_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deleteOrganizacion(@ApiParam(value = "Id de la Organizacion a Inhabilitar", required = true)
                                                    @PathVariable("idOrganizacion") long idOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Inhabilitacion
        Date dateActual = new Date();

        // Buscamos la Organizacion solicitada para la Modificacion
        try {
            // Buacamos la Organizacion segun el Parametro enviado
            TblOrganizacion _tblOrganizacion = organizacionRepository.findByIdOrganizacion(idOrganizacion);

            if ( organizacionRepository.countByIdOrganizacion ( idOrganizacion ) > 0 ) {
                try {
                    // Realizamos la Persistencia de los Datos
                    _tblOrganizacion.setActivo(false);

                    organizacionRepository.save( _tblOrganizacion );
                    organizacionRepository.flush();

                    // return Repository
                    msgMethod = "Se ha Inhabilitado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "Hay problemas al momento de Inhabilitar la Organizacion.";
                    throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } else {
                //Retorno del json
                msgMethod = "No se encuentra una Organizacion con el parametro enviado !!";
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            msgMethod = "Hay problemas al momento de Inhabilitar la Organizacion.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | editOrganizacion
}
