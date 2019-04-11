package com.api.pgc.core.APIRestPGC.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;


public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    //Encoder el Password
    @Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    private CustomLoginFailureHandler failureHandler = new CustomLoginFailureHandler();

    private RememberMeServices rememberMeServices = new NullRememberMeServices();


    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    @ResponseBody
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException {
        //jecutamos el llamado al Login
        // System.out.println("Paso 1 - attemptAuthentication ******** ");
        String header = req.getHeader("Authorization");

        Authentication auth;

        InputStream body = req.getInputStream();

        Usuario user = new ObjectMapper().readValue(body, Usuario.class);

            //Retornamos los Parametros enviados por el JsonBean
            auth = getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmailUsuario(),
                            //encoder.encode( user.getPasswordUsuario() ), //Encoder PassWord
                            user.getPasswordUsuario(), //Encoder PassWord
                            Collections.emptyList()
                    )
            );
            return auth;
    }

    @Override
    protected void successfulAuthentication( HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
           // System.out.println("**************************** successfulAuthentication ***************************** ");
        // Si la autenticacion fue exitosa, agregamos el token a la respuesta
        JwtUtil.addAuthentication(res, auth.getName());
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString(), failed);
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug("Delegating to authentication failure handler " + failureHandler);
        }

        // System.out.println("Data de Funcion unsuccessfulAuthentication ******** " + failed.toString());
        //Add more descriptive message
        /*response.sendError(HttpServletResponse.SC_BAD_REQUEST,
               failed.toString());*/
        rememberMeServices.loginFail(request, response);

        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
