package com.api.pgc.core.APIRestPGC.config.security;

import com.api.pgc.core.APIRestPGC.service.UsuarioService;
import com.api.pgc.core.APIRestPGC.utilities.configAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.ESTADOS_ENDPOINT_FIND_BY_IDGRUPO;
import static com.api.pgc.core.APIRestPGC.utilities.configAPI.PAIS_ENDPOINT;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurity  extends WebSecurityConfigurerAdapter {

    // Constantes de la API
    private configAPI configApi;
    private SecurityConstants secutityConfig;

    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Autowired
    private CustomLoginFailureHandler loginFailureHandler;


    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //System.out.println("******* En WebSecurity 1 ******************  " + auth);
        auth.userDetailsService(usuarioDetailsService)
                .passwordEncoder(passwordEncoder()); //PassWord Encoder
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        System.out.println("Config CORS *****************************************");
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("X-API-KEY", "Origin", "X-Requested-With", "Content-Type",
                "Accept", "Access-Control-Request-Method"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET","POST", "OPTIONS", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(false);
        configuration.setExposedHeaders(Arrays.asList("Content-type","Authorization"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //Crea los Querys de Autenticacion
        /*
         * 1. Se desactiva el uso de cookies
         * 2. Se activa la configuración CORS con los valores por defecto
         * 3. Se desactiva el filtro CSRF
         * 4. Se indica que el login no requiere autenticación
         * 5. Se indica que el resto de URLs esten securizadas
         */
        httpSecurity
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //.cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers(secutityConfig.LOGIN_URL, secutityConfig.SIGN_UP_URL,
                        // configApi.API_BASE_PATH + configApi.ESTADOS_ENDPOINT,
                        configApi.API_BASE_PATH + ESTADOS_ENDPOINT_FIND_BY_IDGRUPO,
                         // configApi.API_BASE_PATH + PAIS_ENDPOINT,
                        // configApi.ESTADOS_ENDPOINT_LIST1,
                        configApi.API_BASE_PATH + configApi.USUARIOS_ENDPOINT_NEW,
                        // "/rest/registro", "/rest/usuarios/user/mail/{emailUsuario}",
                         // "/rest/registro", configApi.API_BASE_PATH + configApi.SECTOR_EJECUTOR_ENDPOINT,
                        "/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
                        "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui.html",
                        "/swagger-resources/configuration/security")
                    .permitAll() //permitimos el acceso a /login a cualquiera
                    .anyRequest().authenticated() //cualquier otra peticion requiere autenticacion *********************
                    .and()
                // path del login
                .formLogin()
                    //.failureHandler(loginFailureHandler)
                    .loginPage(secutityConfig.LOGIN_URL)
                    //.defaultSuccessUrl("/", true)
                    //.failureUrl("/login?error")
                    .permitAll()
                    .and()
                // path del logout
                .logout()
                    // .logoutUrl(SIGN_UP_URL)
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                    .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                // Las peticiones /login pasaran previamente por este filtro
                .addFilterBefore(new LoginFilter(secutityConfig.LOGIN_URL, authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                // Las demás peticiones pasarán por este filtro para validar el token
                .addFilterBefore(new JwtFilter(),
                        UsernamePasswordAuthenticationFilter.class);

                // Configuracion de los Headers
                /*.headers()
                // the headers you want here. This solved all my CORS problems!
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "GET","POST"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))

                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));
*/
    }

    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.getWriter().append("OK");
                httpServletResponse.setStatus(200);
            }
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.getWriter().append("Authentication failure");
                httpServletResponse.setStatus(401);
            }
        };
    }

    @Bean
    public CustomLoginFailureHandler authenticationHandlerBean() {
        return new CustomLoginFailureHandler();
    }

    //Creamos una mascara para El PassWordEncoder
    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }


}
