package com.api.pgc.core.APIRestPGC.config.security;

import java.util.Date;

public class SecurityConstants {
    // Constantes del Modulo de Seguridad
    public static final String TOKEN_SECRET = "pgc-Admin/NAMS#1985";
    public static final long BASE_TIME = 86400;
    // public static final long BASE_TIME = 100;
    // public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final Date EXPIRATION_TIME = new Date( System.currentTimeMillis() + (1000*100)); // 300 seconds
    // public static final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + (1000 * BASE_TIME)); // 24 horas
    public static final Date NOW_TIME = new Date(); //Fecha de Hoy
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    //Mapeo de las Rutas del Modulo de Seguridad
    public static final String SIGN_UP_URL = "/api/v1/users/sign-up";

    public static final String LOGIN_URL = "/api/v1/auth/login";

}
