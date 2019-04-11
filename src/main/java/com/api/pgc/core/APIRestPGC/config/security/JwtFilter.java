package com.api.pgc.core.APIRestPGC.config.security;

import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Las peticiones que no sean /login pasarán por este filtro
 * el cuál se encarga de pasar el "request" a nuestra clase de utilidad JwtUtil
 * para que valide el token.
 */
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response1 = (HttpServletResponse) response;

        // TODO Auto-generated method stub
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();

        //response1.setHeader("Access-Control-Allow-Origin", "*");
        //response1.setHeader("Access-Control-Allow-Methods", "*");
        //response1.setHeader("Access-Control-Request-Headers", "*");

        /*if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                System.out.println("Header iN DOfILTER *********** : " + httpRequest.getHeader(headerNames.nextElement()));
            }
        }*/

        Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request);

        /*if ( authentication != null ) {
            System.out.println("Dato de la Funcion doFilter 1 ***************************  " + authentication );
        }*/

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);

        return;
    }

    public HashMap<String, Object> msgError(){
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        return msgExeptions.msgJson( "Error Controaldo de NAM ***** ", 200 );
    }

}
