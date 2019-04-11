package com.api.pgc.core.APIRestPGC.resourses.seguirdad;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.GruposRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.RolesRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "rolesApi" , description = "Operaciones sobre el Modulo de Roles", tags = "Seguridad")
public class RolesResources {
    // Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    RolesRepository _rolesRepository;

    @Autowired
    GruposRepository _gruposRepository;


    // Metodos Principales de la Clase
    /**
     * Metodo que despliega la Lista de todos los Roles de la BD
     * @autor Nahum Martinez | NAM
     * @version  07/03/2019/v1.0
     * @return Lista de Roles de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Roles de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ROLES_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllRoles() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            msgMethod = "Listado de todos los Roles registrados en la BD";

            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _rolesRepository.findAll() );
            msgExeptions.map.put("totalRecords", _rolesRepository.count());

            // Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega los Roles de la BD
     * @autor Nahum Martinez | NAM
     * @version  07/03/2019/v1.0
     * @return Roles de la BD, por ID Grupo consultado
     * @param idGrupo Identificador del Grupo a Buscar
     */
    @ApiOperation(value = "Retorna los Roles enviado a buscar de la BD, por Id Grupo", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error Interno del Servidor")})
    @GetMapping( value = ROLES_FIND_BY_ID_GRUPO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUserById( @ApiParam(value="Identificador Grupo de Rol a Buscar, por ID", required=true)
                                                @PathVariable("idGrupo") long idGrupo  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            // Buscar el Grupo del Rol
            TblGrupo _tblGrupo = _gruposRepository.findByIdGrupo( idGrupo );

            try {
                if (_rolesRepository.countByIdGrupo(_tblGrupo) == 0) {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado dato de Roles, con el Grupo consultado";
                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Roles, con Grupo consultado";
                    msgExeptions.map.put("data", _rolesRepository.findByIdGrupo(_tblGrupo));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "No se ha encontrado Roles, con el Grupo solicitado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "No se ha encontrado Grupo del Rol consultado, verifica que el Grupo existe";
            throw new RuntimeException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
        }
    }//FIN
}
