package com.api.pgc.core.APIRestPGC.resourses.seguirdad;

//Imports de la Clase
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblCategoriaOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblPerfiles;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.EstadosRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.CategoriaOrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.OrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.TipoOrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.PerfilesRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.PaisRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TiposRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "userApi" , description = "Operaciones sobre el Modulo de Usuarios", tags = "Usuarios")
public class UsuariosResources {
    // Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    TiposRepository tiposRepository;

    @Autowired
    EstadosRepository estadosRepository;

    @Autowired
    PaisRepository paisRepository;

    @Autowired
    PerfilesRepository _perfilesRepository;

    @Autowired
    TipoOrganizacionRepository _tipoOrganizacionRepository;

    @Autowired
    CategoriaOrganizacionRepository _categoriaOrganizacionRepository;

    @Autowired
    OrganizacionRepository _organizacionRepository;


    //Metodos Principales de la Clase
    /**
     * Metodo que despliega la Lista de todos los Usuarios de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Lista de Usuarios de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Usuarios de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = USUARIOS_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllUsers() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            msgMethod = "Listado de todos los Usuarios registrados en la BD";

            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", usuariosRepository.findAll() );
            msgExeptions.map.put("totalRecords", usuariosRepository.count());

            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Usuario de la BD
     * @param codUsuario Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el Usuario enviado a buscar de la BD",
            notes = "Busqueda del Usuarui por su numero de Indentidad",
            authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error Interno del Servidor")})
    @GetMapping( value = USUARIOS_ENDPOINT_FIND_BY_COD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUserByCod( @ApiParam(value="Identificador del Usuario a Buscar, por Código", example = "0801198520207", required=true)
                                            @PathVariable ("codUsuario") String codUsuario  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( usuariosRepository.findByCodUsuario(codUsuario) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Usuario consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Usuario Consultado";
                msgExeptions.map.put("data", usuariosRepository.findByCodUsuario(codUsuario));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Usuario de la BD
     * @param idUsuario Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el Usuario enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error Interno del Servidor")})
    @GetMapping( value = USUARIOS_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUserById( @ApiParam(value="Identificador del Usuario a Buscar, por ID", required=true)
                                            @PathVariable ("idUsuario") long idUsuario  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( usuariosRepository.findByIdUsuario(idUsuario) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Usuario consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 404);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Usuario Consultado";
                msgExeptions.map.put("data", usuariosRepository.findByIdUsuario(idUsuario));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Usuarios
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Mensaje de Confirmacion de Registro de Usuario
     * @param userJson Obtiene desde el request los datos del usuario a ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Usuario",
           notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Datos de Relacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = USUARIOS_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addUsuario(@ApiParam(value="Json del nuevo Usuario a Ingresar, con Relacion asociado", required=true)
                                            @RequestBody final TblUsuarios userJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        Date dateActual = new Date();

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        try {
            // Busca el Tipo, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoUsuario": { "idTipo": valor })
            TblTipo tTipos = tiposRepository.findByIdTipo( userJson.getIdTipoUsuario().getIdTipo() );

            // Busca el Estado, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoUsuario": { "idEstado": valor })
            TblEstado tEstados = estadosRepository.findByIdEstado( userJson.getIdEstadoUsuario().getIdEstado() );

            // Busca el Estado, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoUsuario": { "idEstado": valor })
            TblPais tPais = paisRepository.findByIdPais( userJson.getIdPaisUsuario().getIdPais() );

            // Busca el Tipo de Organizacion, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoOrganizacionUsuario": { "idTipoOrganizacion": valor })
            TblTipoOrganizacion _tipoOrganizacion = _tipoOrganizacionRepository.findByIdTipoOrganizacion( userJson.getIdTipoOrganizacionUsuario().getIdTipoOrganizacion() );

            // Busca el Categoria de Organizacion, desde el Reporsitorio con el Parametro del Json enviado ( "idCatOrganizacionUsuario": { "idCatOrganizacion": valor })
            TblCategoriaOrganizacion _tblCategoriaOrganizacion = _categoriaOrganizacionRepository.findByIdCatOrganizacion( userJson.getIdCatOrganizacionUsuario().getIdCatOrganizacion() );

            // Busca el Organizacion, desde el Reporsitorio con el Parametro del Json enviado ( "idOrganizacionUsuarioUsuario": { "idOrganizacion": valor })
            TblOrganizacion _tblOrganizacion = _organizacionRepository.findByIdOrganizacion( userJson.getIdOrganizacionUsuario().getIdOrganizacion());


            // Graba los Datos de Tipos
            try {
                //Setea el valor Buscando de la Entidad Tipos de Usuario
                userJson.setIdTipoUsuario( tTipos );
                userJson.setIdEstadoUsuario( tEstados );
                userJson.setIdPaisUsuario( tPais );
                userJson.setIdTipoOrganizacionUsuario( _tipoOrganizacion );
                userJson.setIdCatOrganizacionUsuario( _tblCategoriaOrganizacion );
                userJson.setIdOrganizacionUsuario( _tblOrganizacion );

                //Seteo de las Fecha y Hora de Creacion
                userJson.setFechaCreacion( dateActual );
                userJson.setHoraCreacion( dateActual );

                userJson.setPasswordUsuario( encoder.encode( userJson.getPasswordUsuario() ) );

                //Realizamos la Persistencia de los Datos
                usuariosRepository.save(userJson);
                usuariosRepository.flush();

                //return tiposRepository.findAll();
                msgMethod = "El Usuario se ha Ingresado de forma satisfactoria!!";
                msgExeptions.map.put("data", usuariosRepository.findByCodUsuario( userJson.getCodUsuario() ));

                //Retorno del json
                return msgExeptions.msgJson( msgMethod, 200 );
            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Usuario";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "Ha Ocurrido un error al Intentar Grabar el Usuario";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Usuario de la BD
     * @param emailUsuario Emial del Usuario a buscar
     */
    @ApiOperation(value = "Retorna el Usuario enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error Interno del Servidor")})
    @GetMapping( value = USUARIOS_ENDPOINT_FIND_BY_MAIL, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUserByEmail( @ApiParam(value="Identificador del Usuario a Buscar, por Código Interno", required=true)
                                                 @PathVariable ("emailUsuario") String emailUsuario ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        System.out.println("Pasa por Consulta de Usuarios con el Parametro del Email, ********************** Nahum Martinez *******************************");

        try{
            if( usuariosRepository.findByEmailUsuario(emailUsuario) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Usuario consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Usuario Consultado";
                msgExeptions.map.put("data", usuariosRepository.findByEmailUsuario(emailUsuario));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Actualiza el Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version  28/01/2019/v1.0
     * @return Actualizacion del Usuario segun el Parametro del ID enviado
     * @param idUsuario
     * @param _usuarioJsonBean
     */
    @ApiOperation(value = "Actualiza el valor del Usuarios segun el parametro del Id Enviado", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = USUARIOS_ENDPOINT_UPDATE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> updateUsuario(@RequestBody final TblUsuarios _usuarioJsonBean,
                                                   @PathVariable ("idUsuario") long idUsuario)  throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha y Hora de Ingreso
        Date dateActual = new Date();

        try {
            // Buscamos el Usuario a Actualizar
            TblUsuarios _tblUsuarios = usuariosRepository.findByIdUsuario( idUsuario );

            try {
                // Buscamos el Perfil a Actualizar
                TblTipo _tblTipo = tiposRepository.findByIdTipo( _usuarioJsonBean.getIdTipoUsuario().getIdTipo() );

                // Buscamos el Estados a Actualizar
                TblEstado _tblEstado = estadosRepository.findByIdEstado( _usuarioJsonBean.getIdEstadoUsuario().getIdEstado() );

                // Buscamos el Usuario a Actualizar
                TblPais _tblPais = paisRepository.findByIdPais( _usuarioJsonBean.getIdPaisUsuario().getIdPais() );

                // Busca el Tipo de Organizacion, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoOrganizacionUsuario": { "idTipoOrganizacion": valor })
                TblTipoOrganizacion _tipoOrganizacion = _tipoOrganizacionRepository.findByIdTipoOrganizacion( _usuarioJsonBean.getIdTipoOrganizacionUsuario().getIdTipoOrganizacion() );

                // Busca el Categoria de Organizacion, desde el Reporsitorio con el Parametro del Json enviado ( "idCatOrganizacionUsuario": { "idCatOrganizacion": valor })
                TblCategoriaOrganizacion _tblCategoriaOrganizacion = _categoriaOrganizacionRepository.findByIdCatOrganizacion( _usuarioJsonBean.getIdCatOrganizacionUsuario().getIdCatOrganizacion() );

                // Busca el Organizacion, desde el Reporsitorio con el Parametro del Json enviado ( "idOrganizacionUsuarioUsuario": { "idOrganizacion": valor })
                TblOrganizacion _tblOrganizacion = _organizacionRepository.findByIdOrganizacion( _usuarioJsonBean.getIdOrganizacionUsuario().getIdOrganizacion());

                // Seteo de los Campos a Modificar
                _tblUsuarios.setInicialesUsuario( _usuarioJsonBean.getInicialesUsuario());
                _tblUsuarios.setNombre1Usuario( _usuarioJsonBean.getNombre1Usuario());
                _tblUsuarios.setNombre2Usuario( _usuarioJsonBean.getNombre2Usuario());
                _tblUsuarios.setApellido1Usuario( _usuarioJsonBean.getApellido1Usuario());
                _tblUsuarios.setApellido2Usuario( _usuarioJsonBean.getApellido2Usuario());
                _tblUsuarios.setDireccion( _usuarioJsonBean.getDireccion());
                _tblUsuarios.setCodUsuario( _usuarioJsonBean.getCodUsuario());

                _tblUsuarios.setIdTipoUsuario( _tblTipo );
                _tblUsuarios.setIdEstadoUsuario( _tblEstado );
                _tblUsuarios.setIdPaisUsuario( _tblPais );
                _tblUsuarios.setIdTipoOrganizacionUsuario( _tipoOrganizacion );
                _tblUsuarios.setIdCatOrganizacionUsuario( _tblCategoriaOrganizacion );
                _tblUsuarios.setIdOrganizacionUsuario( _tblOrganizacion );

                _tblUsuarios.setFechaModificacion( dateActual );
                _tblUsuarios.setHoraModificacion( dateActual);

                // Realizamos la Persistencia de los Datos
                usuariosRepository.save( _tblUsuarios );
                usuariosRepository.flush();

                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Usuario Actualizada Exitosamente !!";
                msgExeptions.map.put("data", usuariosRepository.findByIdUsuario(idUsuario));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } catch (Exception ex) {
                msgMethod = ex.toString();
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No se ha Podido encontrar el Usuario solicitado: " + idUsuario + " !!";
            throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | updateUsuario

    /**
     * Metodo que Inhabilita el Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version  01/02/2019/v1.0
     * @return Ibhabilitacion del Usuario segun el Parametro del ID enviado
     * @param idUsuario
     */
    @ApiOperation(value = "Inhabilita el Usuario segun el parametro del Id Enviado", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = USUARIOS_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deleteUsuario(@PathVariable ("idUsuario") long idUsuario)  throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha y Hora de Ingreso
        Date dateActual = new Date();

        try {
            // Buscamos el Usuario a Actualizar
            TblUsuarios _tblUsuarios = usuariosRepository.findByIdUsuario( idUsuario );

            try {
                // Seteo de los Campos a Modificar
                _tblUsuarios.setActivo(false);

                _tblUsuarios.setFechaModificacion( dateActual );
                _tblUsuarios.setHoraModificacion( dateActual);

                // Realizamos la Persistencia de los Datos
                usuariosRepository.save( _tblUsuarios );
                usuariosRepository.flush();

                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Usuario Inhabilitado Exitosamente !!";
                msgExeptions.map.put("data", usuariosRepository.findByIdUsuario(idUsuario));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } catch (Exception ex) {
                msgMethod = ex.toString();
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No se ha Podido encontrar el Usuario solicitado: " + idUsuario + " !!";
            throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | deleteUsuario
}
