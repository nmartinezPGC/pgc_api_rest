package com.api.pgc.core.APIRestPGC.service;

import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.ACTIVITY_ENDPOINT;
import static com.api.pgc.core.APIRestPGC.utilities.configAPI.API_BASE_PATH;

@CrossOrigin(origins = "*", maxAge = 3600)
@Service("usuarioService")
@EnableGlobalMethodSecurity(prePostEnabled = true)
// @RequestMapping(value = API_BASE_PATH)
// @Api(value = "usersApi" , description = "Operaciones sobre el Modulo de Usuarios", tags = "Login")
public class UsuarioService extends TblUsuarios implements UserDetailsService {

    @Autowired
    @Qualifier("usuariosRepository")
    private UsuariosRepository usuariosRepository;

    @Override
    /*@ApiOperation(value = "Login a la BD", notes = "Retorna el Hash JWT, que servira para Autenticar todas las peticiones a los EndPoint de la API",
            authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = "auth/login", produces = "application/json; charset=UTF-8" )*/
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        System.out.println("Entra en Paso 1 loadUserByUsername **************************************");

        //Try - Catch
        try{
            //TblUsuarios tblUsuarios = usuariosRepository.findByCodUsuario( username );
            TblUsuarios tblUsuarios = usuariosRepository.findByEmailUsuario( username );

            //Validaion de la Busqueda del Usuario por l Codigo
            if( tblUsuarios == null ){
                System.out.println("Usuario con email: " + username + " no encontrado.");
                throw new UsernameNotFoundException("Usuario con email: " + username + " no encontrado.");
            }else {
                System.out.println("Datos del Login User:  " + tblUsuarios.getNombre1Usuario() + "  ********   "
                        + tblUsuarios.getApellido1Usuario() + "  *******  " + tblUsuarios.getIdEstadoUsuario().getDescEstado()
                        + "  *******  " + tblUsuarios.getIdTipoUsuario().getDescTipo() + "  *******  " + tblUsuarios.getRol() + "  *******  " + tblUsuarios.getPasswordUsuario());

                return new User(tblUsuarios.getEmailUsuario(), tblUsuarios.getPasswordUsuario(),
                        tblUsuarios.isActivo(), tblUsuarios.isActivo(), tblUsuarios.isActivo(), tblUsuarios.isActivo(),
                        buildgrante(tblUsuarios.getRol()));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuario con email: " + username + " no encontrado.");
        }
    }//FIN | loadUserByUsername()



    public List<GrantedAuthority> buildgrante(byte rol){
        String[] roles = {"LECTOR","USUARIO","ADMINISTRADOR"};
        List<GrantedAuthority> auths = new ArrayList<>();

        for( int i = 0; i < rol; i++ ){
            auths.add( new SimpleGrantedAuthority( roles[i] ));
        }
        return auths;
    }

}
