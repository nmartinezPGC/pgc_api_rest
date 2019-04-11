package com.api.pgc.core.APIRestPGC.config.security;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomLoginFailureHandler implements  AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        System.out.println("Data de funcion onAuthenticationFailure ***************** ");

        //Parametros de Salida del Response
        response.setStatus( HttpStatus.BAD_REQUEST.value() );
        response.setContentType("application/x-json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");

        System.out.println("Paso 1 onAuthenticationFailure +++++++++++++ ");
        JSONObject jsonResponse;
        jsonResponse = new JSONObject();
        try {
            // jsonResponse.put("message", exception.getMessage().toString() );
            jsonResponse.put("message", "Usuario o Contraseña, no se encuentran, verifica que se han ingresado correctamente" );
            jsonResponse.put("status", HttpStatus.BAD_REQUEST.value() );
            jsonResponse.put("error", exception.getMessage().toString() );
            jsonResponse.put("path", request.getServletPath() );

            response.getWriter().write(jsonResponse.toString());
            //response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Usuario No Valido");
            System.out.println("Paso 2 onAuthenticationFailure +++++++++++++ " + jsonResponse);
        } catch (JSONException e) {
            System.out.println("Paso 3 onAuthenticationFailure +++++++++++++ ");

            response.getWriter().write("Error Critico al realizar la peticion del Login");
            //e.printStackTrace();
        }
    }
}
