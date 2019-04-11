/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades;

//Imports de la Clase
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo.TblEspaciosTrabajo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.*;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.espacios_de_trabajo.EspaciosTrabajoRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.EstadosRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades.*;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.PaisRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
// @ApiIgnore | Ignora los EndPoints
@Api(value = "activityApi" , description = "Operaciones sobre el Modulo de Proyectos", tags = "Proyectos")
public class ActividadResources {
    /**
     * Propiedades de la Clase
     */
    String msgMethod = null;


    /**
     * Definicion de los Repossitorios de la Clase
     */
    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    EstadosRepository _estadosRepository;

    @Autowired
    PaisRepository paisRepository;

    @Autowired
    EstrategiasRepository _estrategiasRepository;

    @Autowired
    PresupuestoRepository _presupuestoRepository;

    @Autowired
    SectorEjecutorRepository _sectorEjecutorRepository;

    @Autowired
    MonedaActividadRepository _monedaActividadRepository;

    @Autowired
    UsuariosRepository _usuariosRepository;

    @Autowired
    EspaciosTrabajoRepository _espaciosTrabajoRepository;

    @Autowired
    TipoIniciativaCssRepository _tipoIniciativaCssRepository;


    /**
     * Metodo que despliega la Lista de todos los Proyectos de la BD
     * @autor Nahum Martinez | NAM
     * @version  31/01/2019/v1.0
     * @return Lista de todos los Proyectos registrados de la BD
     */
    @ApiOperation(value = "Buscar todos los Proyectos registrados de la BD", notes = "Retorna el Listado de todos los Proyectos registrados de la BD, y su información de relaciones",
            authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ACTIVITY_ENDPOINT, produces = "application/json; charset=UTF-8" )
    public HashMap<String, Object> getAllActvidades() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            // Busca las Planificaciones
            if (_actividadRepository.count() == 0 ){
                msgMethod = "No se ha Encontrado Información de Proyectos registrados en la BD";
            } else {
                msgMethod = "Listado de todas los Proyectos registrados en la BD";
            }

            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _actividadRepository.findAll() );
            msgExeptions.map.put( "countRecords", _actividadRepository.count() );
            // Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }/** FIN | getAllActvidades */


    /**
     * Metodo que despliega el Proyecto de la BD
     *
     * @param idActivity Identificador del Proyecto a Buscar
     * @return Proyecto de la BD
     * @autor Nahum Martinez | NAM
     * @version 01/02/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Información del Proyecto enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ACTIVITY_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getProyectoByIdActividad(@ApiParam(value = "Identificador del Proyecto a Buscar", required = true)
                                                            @PathVariable("idActivity") long idActivity) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadRepository.findByIdActividad(idActivity) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado datos del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Información del Proyecto consultado";
                msgExeptions.map.put("totalRecords", _actividadRepository.countByIdActividad(idActivity));
                msgExeptions.map.put("data", _actividadRepository.findByIdActividad(idActivity));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } /** FIN | getProyectoByIdActividad */


    /**
     * Metodo que Solcita un json con los datos de la Entidad Actividades
     * @autor Nahum Martinez | NAM
     * @version  11/01/2019/v1.0
     * @return Mensaje de Confirmacion de Registro de la Actividad
     * @param _actividadJsonBean Obtiene desde el request los datos de la Actividad a Ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de la nueva Actividad",
           notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Datos de Relacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ACTIVITY_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    //public ResponseEntity<Object> addActividad(@ApiParam(value="Json de la nueva Actividad a Ingresar, con Relacion asociado", required=true)
    public HashMap<String, Object> addActividad(@ApiParam(value="Json de la nueva Actividad a Ingresar, con Relacion asociado", required=true)
                                            @RequestBody final TblActividad _actividadJsonBean) throws Exception, SQLException {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha y Hora de Ingreso
        Date dateActual = new Date();

        try {
            // Busca la Acvidad para Saber si esta repetida, por Medio del Codigo
            System.out.println( "Nombre de Actividad " + _actividadJsonBean.getNombreActividad());
            if ( _actividadRepository.countByNombreActividad( _actividadJsonBean.getNombreActividad() ) != 0 ) {
                // Notifica al Usuario que la Actividad ya esta Ingresada, con el Mismo Nombre
                msgMethod = "Ya Existe una Actividad con este Nombre !! " + _actividadJsonBean.getNombreActividad() + " porfavor verifica que sea el correcto";
                //Sobreescirbe el Metodo de Mensajes
                msgExeptions.map.put("data", _actividadJsonBean.getNombreActividad() );
                msgExeptions.map.put("find", true );

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else if  ( _actividadRepository.countByCodigoActividad( _actividadJsonBean.getCodigoActividad() ) != 0 ) {
                // Notifica al Usuario que la Actividad ya esta Ingresada, con el Codigo Asignado
                msgMethod = "Ya Existe una Actividad con este Codigo !! " + _actividadJsonBean.getCodigoActividad() + " porfavor verifica que sea el correcto";
                //Sobreescirbe el Metodo de Mensajes
                msgExeptions.map.put("data", _actividadJsonBean.getCodigoActividad() );
                msgExeptions.map.put("find", true );

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Busca el Estado, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoActivity": { "idEstado": valor })
                TblEstado _tblEstados = _estadosRepository.findByIdEstado( _actividadJsonBean.getIdEstadoActivity().getIdEstado());

                // Busca la Estragia, desde el Reporsitorio con el Parametro del Json enviado ( "idEstrategiaActivity": { "idEstrategia": valor })
                TblEstrategiasActividad _tblEstrategiasActividad = _estrategiasRepository.findByIdEstrategia(_actividadJsonBean.getIdEstrategiaActivity().getIdEstrategia());

                // Busca el Presupuesto, desde el Reporsitorio con el Parametro del Json enviado ( "idPresupuestoActivity": { "idPresupuesto": valor })
                TblPresupuestoActividad _tblPresupuestoActividad = _presupuestoRepository.findByIdPresupuesto(_actividadJsonBean.getIdPresupuestoActivity().getIdPresupuesto());

                // Busca el Sector Ejecutor, desde el Reporsitorio con el Parametro del Json enviado ( "idSectorEjecutorActivity": { "idSectorEjecutor": valor })
                TblSectorEjecutor _tblSectorEjecutor = _sectorEjecutorRepository.findByIdSectorEjecutor(_actividadJsonBean.getIdSectorEjecutorActivity().getIdSectorEjecutor());

                // Busca la Moneda de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idMonedaActividadActivity": { "idMonedaActividad": valor })
                TblMonedaActividad _tblMonedaActividad = _monedaActividadRepository.findByIdMonedaActividad(_actividadJsonBean.getIdMonedaActividadActivity().getIdMonedaActividad());

                // Busca el Usuario Creador de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idUsuarioCreador": { "idUsuario": valor })
                TblUsuarios _tblUsuarios = _usuariosRepository.findByIdUsuario(  _actividadJsonBean.getIdUsuarioCreador().getIdUsuario() );

                // Busca el Espacio de Trabajo de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idEspacioTrabajoActivity": { "idEspacioTrabajo": valor })
                TblEspaciosTrabajo _tblEspaciosTrabajo = _espaciosTrabajoRepository.findByIdEspacioTrabajo(  _actividadJsonBean.getIdEspacioTrabajoActivity().getIdEspacioTrabajo() );

                // Busca el Estado de Validacion del Proyecto, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoValid": { "idEstadoV": valor })
                TblEstado _tblEstadosValid = _estadosRepository.findByIdEstado( _actividadJsonBean.getIdEstadoValid().getIdEstado());

                // Busca el Tipo de Iniciativa de Validacion del Proyecto CSS, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoIniciativaCssAct": { "idTipoIniciativa": valor })
                TblTipoIniciativaCss _tblTipoIniciativaCss = _tipoIniciativaCssRepository.findByIdTipoIniciativa( _actividadJsonBean.getIdTipoIniciativaCssAct().getIdTipoIniciativa());


                // Graba los Datos de la Nueva Actividad
                try {
                    // Setea los valores de las Relaciones enviadas
                    _actividadJsonBean.setIdEstadoActivity( _tblEstados );
                    _actividadJsonBean.setIdEstrategiaActivity( _tblEstrategiasActividad );
                    _actividadJsonBean.setIdPresupuestoActivity( _tblPresupuestoActividad );
                    _actividadJsonBean.setIdSectorEjecutorActivity( _tblSectorEjecutor );
                    _actividadJsonBean.setIdMonedaActividadActivity( _tblMonedaActividad );
                    _actividadJsonBean.setIdUsuarioCreador( _tblUsuarios );
                    _actividadJsonBean.setIdEspacioTrabajoActivity( _tblEspaciosTrabajo);
                    _actividadJsonBean.setIdEstadoValid( _tblEstadosValid );
                    _actividadJsonBean.setIdTipoIniciativaCssAct( _tblTipoIniciativaCss );

                    // Seteo de las Fecha y Hora de Creacion
                    _actividadJsonBean.setFechaCreacion(dateActual);
                    _actividadJsonBean.setHoraCreacion(dateActual);

                    // Realizamos la Persistencia de los Datos
                    System.out.println("Datos del Proyecto");
                    _actividadRepository.save(_actividadJsonBean);
                    _actividadRepository.flush();

                    // Datos del Proyecto Recien Ingresado
                    msgExeptions.map.put("data", _actividadRepository.findByCodigoActividad( _actividadJsonBean.getCodigoActividad() ) );
                    msgExeptions.map.put("find", false );

                    msgMethod = "Se ha Ingresado de forma Satisfactoria el Proyecto !!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Intentar Grabar el Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            }
        }catch ( Exception ex ){
            msgMethod = "Ha Ocurrido un error al Intentar Grabar el Proyecto, problema con la Información enviada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }/** FIN | addActividad */



    /**
     * Metodo que Solcita un json con los datos de la Entidad Proyecto
     * @autor Nahum Martinez | NAM
     * @version  11/01/2019/v1.0
     * @return Mensaje de Confirmacion de Actualizacion del Proyecto
     * @param _actividadJsonBean Obtiene desde el request los datos del Proyecto a Actualizar
     * @param idActivity Identificador del Proyecto
     */
    @ApiOperation(value = "Actualiza a la BD, la Información enviada por el Bean del Proyecto a Actualizar",
            notes = "Se debe incluir en la Estructura del JsonBean el Identificador del Proyecto y los Datos de Relacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = ACTIVITY_ENDPOINT_EDIT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> updateActividad(@ApiParam(value="Json del Proyecto a Actualizar, con las Relaciones asociadas", required=true)
                                                @RequestBody final TblActividad _actividadJsonBean,
                                                   @PathVariable("idActivity") long idActivity) throws Exception, SQLException {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha y Hora de Ingreso
        Date dateActual = new Date();

        try {
            // Buscamos la Categoria de Organizacion Solicitada
            TblActividad _tblActividad = _actividadRepository.findByIdActividad( idActivity );

            // Busca la Acvidad para Saber si esta repetida
            if ( _actividadRepository.countByCodigoActividad( _actividadJsonBean.getCodigoActividad() ) != 0 ) {

                // Busca el Estado, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoActivity": { "idEstado": valor })
                TblEstado _tblEstados = _estadosRepository.findByIdEstado( _actividadJsonBean.getIdEstadoActivity().getIdEstado());

                // Busca la Estragia, desde el Reporsitorio con el Parametro del Json enviado ( "idEstrategiaActivity": { "idEstrategia": valor })
                TblEstrategiasActividad _tblEstrategiasActividad = _estrategiasRepository.findByIdEstrategia(_actividadJsonBean.getIdEstrategiaActivity().getIdEstrategia());

                // Busca el Presupuesto, desde el Reporsitorio con el Parametro del Json enviado ( "idPresupuestoActivity": { "idPresupuesto": valor })
                TblPresupuestoActividad _tblPresupuestoActividad = _presupuestoRepository.findByIdPresupuesto(_actividadJsonBean.getIdPresupuestoActivity().getIdPresupuesto());

                // Busca el Sector Ejecutor, desde el Reporsitorio con el Parametro del Json enviado ( "idSectorEjecutorActivity": { "idSectorEjecutor": valor })
                TblSectorEjecutor _tblSectorEjecutor = _sectorEjecutorRepository.findByIdSectorEjecutor(_actividadJsonBean.getIdSectorEjecutorActivity().getIdSectorEjecutor());

                // Busca la Moneda de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idMonedaActividadActivity": { "idMonedaActividad": valor })
                TblMonedaActividad _tblMonedaActividad = _monedaActividadRepository.findByIdMonedaActividad(_actividadJsonBean.getIdMonedaActividadActivity().getIdMonedaActividad());

                // Busca el Usuario Modificador de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idUsuarioMod": { "idUsuario": valor })
                TblUsuarios _tblUsuarios = _usuariosRepository.findByIdUsuario(  _actividadJsonBean.getIdUsuarioMod().getIdUsuario() );

                // Busca el Espacio de Trabajo de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idEspacioTrabajoActivity": { "idEspacioTrabajo": valor })
                TblEspaciosTrabajo _tblEspaciosTrabajo = _espaciosTrabajoRepository.findByIdEspacioTrabajo(  _actividadJsonBean.getIdEspacioTrabajoActivity().getIdEspacioTrabajo() );

                // Busca el Estado de Validacion del Proyecto, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoValid": { "idEstadoV": valor })
                TblEstado _tblEstadosValid = _estadosRepository.findByIdEstado( _actividadJsonBean.getIdEstadoValid().getIdEstado());

                // Busca el Tipo de Iniciativa de Validacion del Proyecto CSS, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoIniciativaCssAct": { "idTipoIniciativa": valor })
                TblTipoIniciativaCss _tblTipoIniciativaCss = _tipoIniciativaCssRepository.findByIdTipoIniciativa( _actividadJsonBean.getIdTipoIniciativaCssAct().getIdTipoIniciativa());

                // Actualiza los Datos del Proyecto seleccionado
                try {
                    // Setea los valores de las Relaciones enviadas
                    _tblActividad.setIdEstadoActivity( _tblEstados );
                    _tblActividad.setIdEstrategiaActivity( _tblEstrategiasActividad );
                    _tblActividad.setIdPresupuestoActivity( _tblPresupuestoActividad );
                    _tblActividad.setIdSectorEjecutorActivity( _tblSectorEjecutor );
                    _tblActividad.setIdMonedaActividadActivity( _tblMonedaActividad );
                    _tblActividad.setIdUsuarioMod( _tblUsuarios );
                    _tblActividad.setIdEspacioTrabajoActivity( _tblEspaciosTrabajo);
                    _tblActividad.setIdEstadoValid( _tblEstadosValid );
                    _tblActividad.setIdTipoIniciativaCssAct( _tblTipoIniciativaCss );

                    // Setea los Campos de Modificacion Genereales
                    _tblActividad.setCodigoActividad( _actividadJsonBean.getCodigoActividad() );
                    _tblActividad.setExplicacionEstado( _actividadJsonBean.getExplicacionEstado() );
                    _tblActividad.setAntecedentesActividad( _actividadJsonBean.getAntecedentesActividad() );
                    _tblActividad.setObjetivoActividad( _actividadJsonBean.getObjetivoActividad() );
                    _tblActividad.setDescripcionActividad( _actividadJsonBean.getDescripcionActividad() );
                    _tblActividad.setCondicionesActividad( _actividadJsonBean.getCondicionesActividad() );
                    _tblActividad.setCodigoSIAFIBIP( _actividadJsonBean.getCodigoSIAFIBIP() );
                    _tblActividad.setResultadosEsperados( _actividadJsonBean.getResultadosEsperados() );
                    _tblActividad.setResultadosAlaFecha( _actividadJsonBean.getResultadosAlaFecha() );
                    _tblActividad.setJustificacionActividad( _actividadJsonBean.getJustificacionActividad() );
                    _tblActividad.setCostoActividad(_actividadJsonBean.getCostoActividad() );
                    _tblActividad.setCostoActividad(_actividadJsonBean.getCostoActividad() );
                    _tblActividad.setCostoActividad(_actividadJsonBean.getCostoActividad() );
                    _tblActividad.setFechaFinanciamiento(_actividadJsonBean.getFechaFinanciamiento() );
                    _tblActividad.setNombreActividad(_actividadJsonBean.getNombreActividad() );
                    _tblActividad.setProductosEsperados(_actividadJsonBean.getProductosEsperados() );
                    _tblActividad.setNombreActividad(_actividadJsonBean.getNombreActividad() );
                    _tblActividad.setActividadesCss(_actividadJsonBean.getActividadesCss() );

                    // Seteo de las Fecha y Hora de Modificacion
                    _tblActividad.setFechaModificacion(dateActual);
                    _tblActividad.setHoraModificacion(dateActual);

                    // Realizamos la Persistencia de los Datos
                    _actividadRepository.save(_tblActividad);
                    _actividadRepository.flush();

                    // Datos del Proyecto Recien Ingresado
                    msgExeptions.map.put("data", _actividadRepository.findByCodigoActividad( _actividadJsonBean.getCodigoActividad() ) );
                    msgExeptions.map.put("find", false );

                    msgMethod = "Se ha Actulizado la Informacion del Proyecto de forma satisfactoria !!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Actualizar el Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } else {
                // Notifica al Usuario que el Proyecto no Existe
                msgMethod = "No existe el Proyecto con el código que estas buscando !! " + _actividadJsonBean.getCodigoActividad();
                // SobreEscirbe el Metodo de Mensajes
                msgExeptions.map.put("data", _actividadJsonBean.getCodigoActividad() );
                msgExeptions.map.put("find", true );

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            msgMethod = "Ha Ocurrido un error al Intentar Actualizar el Proyecto, problema con la Información enviada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }/** FIN | updateActividad */

}
