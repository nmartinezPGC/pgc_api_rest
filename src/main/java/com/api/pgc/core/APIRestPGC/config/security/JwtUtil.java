package com.api.pgc.core.APIRestPGC.config.security;

import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static java.util.Collections.emptyList;
// Constantes del Modulo de Seguridad
import static com.api.pgc.core.APIRestPGC.config.security.SecurityConstants.*;


@CrossOrigin(origins = "*", maxAge = 3600)
public class JwtUtil {


    /*******************************************************************************************************************
     * @autor Nahum Martinez
     * @date 2018-07-01
     * Método para crear el JWT y enviarlo al cliente en el header de la respuesta
     ******************************************************************************************************************/
    static void addAuthentication(HttpServletResponse res, String username) throws IOException {
        // System.out.println("Datos en: JwtUtil de la Funcion addAuthentication() - username **********  " +  username);

        //Parametros de Salida del Response
        res.setContentType("application/x-json;charset=UTF-8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();

        // Parametros para crear el Token
        String id = UUID.randomUUID().toString().replace("-", "");

        // Generacion del Token
        String token;
            token = Jwts.builder()
                    .setId(id)
                    .setSubject(username)
                    // .setIssuedAt(NOW_TIME)
                    // .setNotBefore(NOW_TIME)
                    .setExpiration(new Date( System.currentTimeMillis() + (1000*BASE_TIME)))
                    .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                    .compact();

            // System.out.println("Datos en: JwtUtil de la Funcion addAuthentication() - token **********  " +  token);

        if( token != null ){
            //agregamos al encabezado el token
            res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

            //Seteo del Json a ver
            try {
                // Jsonresponse send
                jsonResponse.put("token", token);
                jsonResponse.put("userName",username);
                jsonResponse.put("message", "Valor del Token de la Sesion, tienes 24 horas para usarlo, " +
                        "despues tu session finalizara");
                jsonResponse.put("status", HttpStatus.OK.value());
                res.getWriter().write(jsonResponse.toString());
            } catch (JSONException e) {
                res.getWriter().write("Error Critico al realizar la peticion del Token");
                e.printStackTrace();
            }
        }
    }//FIN | addAuthentication


    /*******************************************************************************************************************
     * @autor Nahum Martinez
     * @date 2018-07-01
     * Método para validar el token enviado por el cliente
     ******************************************************************************************************************/
    static Authentication getAuthentication(HttpServletRequest request) {
        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");
        // String token2 = request.getHeader("Access-Control-Request-Headers");
        String token3 = request.getParameter("tokenApi");

        if (token != null) {
            // System.out.println("Funcion getAuthentication Paso 1.1 ************** ***************  " + request.getHeader("Authorization") );

            request.setAttribute("expired", "Mensaje de NAM");

            if (token != null) {
               // System.out.println("Funcion getAuthentication Paso 1 - token con Datos ***************  " + token);
                String user = Jwts.parser()
                        .setSigningKey(TOKEN_SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) //este metodo es el que valida
                        .getBody()
                        .getSubject();

                // Recordamos que para las demás peticiones que no sean /auth/login
                // no requerimos una autenticacion por username/password con el Token que es del Headers
                // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
                if( user != null ){
                    return new UsernamePasswordAuthenticationToken(user, null, emptyList());
                } else {
                    System.out.println("User null ++++++++++++ ");
                }
                return null;
            }
        } else if( token3 != null ) {
            System.out.println("Funcion getAuthentication Paso 2 - token3 con Datos ***************  " + token3);
            String user = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token3.replace(HEADER_STRING, "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

            // Recordamos que para las demás peticiones que no sean /auth/login
            // no requerimos una autenticacion por username/password con el Token3 que es del Parametro
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            if( user != null ){
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            }else{
                System.out.println("User null ++++++++++++ ");
            }
            return null;
        } else {
            return null;
        }

        // Retorno de Metodo
        return null;
    }// FIN | getAuthentication

}
