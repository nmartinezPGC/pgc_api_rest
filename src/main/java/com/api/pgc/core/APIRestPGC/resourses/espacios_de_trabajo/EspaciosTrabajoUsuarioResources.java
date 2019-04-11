package com.api.pgc.core.APIRestPGC.resourses.espacios_de_trabajo;

//Imports de la Clase

import com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo.TblEspaciosTrabajo;
import com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo.TblEspaciosTrabajoUsuarios;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblRoles;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import com.api.pgc.core.APIRestPGC.repository.espacios_de_trabajo.EspaciosTrabajoRepository;
import com.api.pgc.core.APIRestPGC.repository.espacios_de_trabajo.EspaciosTrabajoUsuarioRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.EstadosRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.RolesRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.PaisRepository;
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
@Api(value = "espaciotrabajouserapi", description = "Operaciones sobre el Modulo de Espacios de Trabajo que tiene cada Usuario Asignado", tags = "Espacios de Trabajo - Usuarios")
public class EspaciosTrabajoUsuarioResources {

    //Propiedades de la Clase
    private String msgMethod = null;

    @Autowired
    EspaciosTrabajoUsuarioRepository espaciosTrabajoUsuarioRepository;

    @Autowired
    EspaciosTrabajoRepository _espaciosTrabajoRepository;

    @Autowired
    UsuariosRepository _usuariosRepository;

    @Autowired
    RolesRepository _rolesRepository;

    /**
     * Metodo que despliega la Lista de todos los Espacios de Trabajo Asignados al Usuario de la BD
     *
     * @return Lista de Espacios de Trabajo que tiene un Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version 12/1/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Espacios de Trabajo de la BD")
    @GetMapping(value = ESPACIOS_TRABAJO_USUARIO_ENDPOINT, produces = "application/json")
    public HashMap<String, Object> getAllEspaciosTrabajoUsuario() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Espacios de Trabajo registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", espaciosTrabajoUsuarioRepository.findAll());
            msgExeptions.map.put("totalRecords", espaciosTrabajoUsuarioRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Espacio de Trabajo que tiene un Usuario de la BD
     *
     * @param idUsuarioEspacioTrabajo Identificador del Tipo a Buscar
     * @return Espacio de Trabajo que tiene asignado un Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version 12/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Tipo enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado")})
    @GetMapping(value = ESPACIOS_TRABAJO_USUARIO_ENDPOINT_FIND_BY_ID, produces = "application/json")
    public HashMap<String, Object> getEspacioTrabajoUsuario(@ApiParam(value = "Identificador del Usuario que tiene Espacio de Trabajo a Buscar", required = true)
                                                            @PathVariable("idUsuarioEspacioTrabajo") long idUsuarioEspacioTrabajo) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscamos el dato del Usuario que desamos saber sus Asignacion de Espacios de Trabajo
            TblUsuarios _tblUsuarios = _usuariosRepository.findByIdUsuario(idUsuarioEspacioTrabajo);

            try {
                if (espaciosTrabajoUsuarioRepository.findByIdUsuarioEspacioTrabajo(_tblUsuarios) == null) {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado dato del Usuario con Espacios de Trabajo, asignados";
                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 400);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de los Espacios de Trabajo del Usuario, que tiene asignados";
                    msgExeptions.map.put("data", espaciosTrabajoUsuarioRepository.findByIdUsuarioEspacioTrabajo(_tblUsuarios));
                    msgExeptions.map.put("totalRecords", espaciosTrabajoUsuarioRepository.countByIdUsuarioEspacioTrabajo(_tblUsuarios));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No se ha encontrado un Usuario con el Parametro Enviado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solicita un json con los datos de la Entidad Espacios de Trabajo
     *
     * @param _espacioTrabajoUsuarioJson Obtiene desde el request los datos del Espacio de Trabajo a ingresar
     * @return Mensaje de Confirmacion de Registro de tipo
     * @autor Nahum Martinez | NAM
     * @version 11/10/2018/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean para Asignar Espacio de Trabajo al Usuario",
            notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Espacio de Trabajo y el Usuario al que se desea hacer la asignación", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ESPACIOS_TRABAJO_USUARIO_ENDPOINT_NEW, produces = "application/json")
    public HashMap<String, Object> addEspacioTrabajoUsuario(@ApiParam(value = "Json Bean del Espacio de trabajo asignado al Usuario a Ingresar", required = true)
                                                            @RequestBody final TblEspaciosTrabajoUsuarios _espacioTrabajoUsuarioJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            //Busca el Tipo de Espacio de Trabajo, desde el Reporsitorio con el Parametro del Json enviado ( "IdEspacioTrabajo": { "IdEspacioTrabajo": valor })
            TblEspaciosTrabajo _tblEspaciosTrabajo = _espaciosTrabajoRepository.findByIdEspacioTrabajo( _espacioTrabajoUsuarioJson.getIdEspacioTrabajo().getIdEspacioTrabajo() );

            //Graba los Datos de Asignacion de Espacios de Trabajo
            try {
                //Busca el Usuario de Espacio de Trabajo, desde el Reporsitorio con el Parametro del Json enviado ( "IdUsuarioEspacioTrabajo": { "IdUsuario": valor })
                TblUsuarios _tblUsuarios = _usuariosRepository.findByIdUsuario( _espacioTrabajoUsuarioJson.getIdUsuarioEspacioTrabajo().getIdUsuario() );

                //Busca el Rol de Espacio de Trabajo, desde el Reporsitorio con el Parametro del Json enviado ( "IdRolEspacioTrabajo": { "IdRol": valor })
                TblRoles _tblRoles = _rolesRepository.findByIdRol( _espacioTrabajoUsuarioJson.getIdRolEspacioTrabajo().getIdRol());

                if( espaciosTrabajoUsuarioRepository.countIdUsuarioEspacioTrabajoAndIdEspacioTrabajo( _tblUsuarios, _tblEspaciosTrabajo ) > 0 ){
                    //return tiposRepository.findAll();
                    msgMethod = "Se ha presentado un Error al momento de Asignar el Espacio de Trabajo al Usuario, por favor verifica que no estes repitiendo la información";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {

                    //Setea el valor Buscado de la Entidad Espacios de Trabajo | Espacio de Trabajo
                    _espacioTrabajoUsuarioJson.setIdEspacioTrabajo(_tblEspaciosTrabajo);

                    //Setea el valor Buscado de la Entidad Espacios de Trabajo | Usuarios
                    _espacioTrabajoUsuarioJson.setIdUsuarioEspacioTrabajo(_tblUsuarios);

                    //Setea el valor Buscado de la Entidad Espacios de Trabajo | Roles
                    _espacioTrabajoUsuarioJson.setIdRolEspacioTrabajo(_tblRoles);

                    //Realizamos la Persistencia de los Datos
                    espaciosTrabajoUsuarioRepository.save(_espacioTrabajoUsuarioJson);

                    //return tiposRepository.findAll();
                    msgMethod = "Se ha Asignado el Espacio de Trabajo al Usuario " + " de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el la Asignacion de Espacio de Trabajo del Usuario";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Espacio de Trabajo que buscas, por favor verfica que el Espacio de Trabajo o el Usuario ingresado es correcto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN

}
