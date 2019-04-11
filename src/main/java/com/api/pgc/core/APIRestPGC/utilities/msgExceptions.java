package com.api.pgc.core.APIRestPGC.utilities;

import java.util.HashMap;

public class msgExceptions {
    //Propiedades de la Clase de Msg
    public String msgMethod = null;
    public HashMap<String, Object> map = new HashMap<>();



    public HashMap<String, Object> msgJson(String msgMethodIn, int statusIn ){
        //msgMethod = "Lista de todos los Grupos Disponibles";
        //Parametros de la Clase
        map.put("message", msgMethodIn);
        map.put("status", statusIn);
        //map.put("data", modelIn);

        //Retorno del Mensaje
        return map;
    }

}
