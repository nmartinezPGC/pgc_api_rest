/*
 * Copyright (c) 2018.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.utilities;

/**
 * @author nahum.martinez
 * @version 1.0
 * @apiNote Clase que Mapea todos EndPoints de la API
 */

public class configAPI {
    // Constantes del Modulo de General de la API
    public static final String API_BASE_PATH = "/api/v1";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mantenimientos Genericos de la API ******************************************************************************
    // Mapeo de las Rutas del Modulo de Estados
    public static final String ESTADOS_ENDPOINT = "/estados";
    public static final String ESTADOS_ENDPOINT_FIND_BY_ID = "/estados/findById/{idEstado}";
    public static final String ESTADOS_ENDPOINT_FIND_BY_IDGRUPO = "/estados/findByIdGrupo/{idGrupo}";
    public static final String ESTADOS_ENDPOINT_LIST1 = API_BASE_PATH + "/estados/findById/{idEstado}";

    // Mapeo de las Rutas del Modulo de Grupos
    public static final String GRUPOS_ENDPOINT = "/grupos";
    public static final String GRUPOS_ENDPOINT_FIND_BY_ID = "/grupos/findById/{idGrupo}";

    // Mapeo de las Rutas del Modulo de Tipos
    public static final String TIPOS_ENDPOINT = "/tipos";
    public static final String TIPOS_ENDPOINT_FIND_BY_ID = "/tipos/findById/{idTipo}";
    public static final String TIPOS_ENDPOINT_FIND_BY_IDGRUPO = "/tipos/findByIdGrupo/{idGrupo}";

    // Mapeo de las Rutas del Modulo de Ubicaciones | Pais | Departamentos | Municipios
    public static final String PAIS_ENDPOINT = "/ubicacion-geografica/pais";
    public static final String PAIS_ENDPOINT_FIND_BY_ID = "/ubicacion-geografica/pais/findByIdPais/{idPais}";
    public static final String DEPTO_ENDPOINT = "/ubicacion-geografica/departamento";
    public static final String DEPTO_ENDPOINT_FIND_BY_ID = "/ubicacion-geografica/pais/findByIdDepartamento/{idDepartamento}";
    public static final String MUNIC_ENDPOINT = "/ubicacion-geografica/municipio";
    public static final String MUNIC_ENDPOINT_FIND_BY_ID = "/ubicacion-geografica/pais/findByIdMunicipio/{idMunicipio}";

    // Mapeo de las Rutas del Modulo de Ubicaciones | Nivel de Implementacion | Nivel Ubicacion Implentacion | Ubicacion Implementacion
    public static final String NIVEL_IMPLEMENTACION_ENDPOINT = "/ubicacion-geografica/nivel-implementacion";
    public static final String NIVEL_IMPLEMENTACION_FIND_BY_ID = "/ubicacion-geografica/nivel-implementacion/findByIdNivel/{idNivel}";
    public static final String NIVEL_UBICACION_IMPLEMENTACION_ENDPOINT = "/ubicacion-geografica/nivel-ubicacion-implementacion";
    public static final String NIVEL_UBICACION_IMPLEMENTACION_FIND_BY_ID = "/ubicacion-geografica/nivel-ubicacion-implementacion/findByIdNivelUbicacion/{idNivelUbicacion}";
    public static final String NIVEL_UBICACION_IMPLEMENTACION_FIND_BY_ID_NIVEL_IMPL = "/ubicacion-geografica/nivel-ubicacion-implementacion/findByIdNivelImplementacion/{idNivelImplementacion}";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT = "/ubicacion-geografica/ubicacion-implementacion";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID = "/ubicacion-geografica/ubicacion-implementacion/findByIdUbicacion/{idUbicacion}";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID_NIVEL_IMPL = "/ubicacion-geografica/ubicacion-implementacion/findByIdNivelImplementacion/{idNivelImplementacion}";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID_UBIC_NIVEL_IMPL = "/ubicacion-geografica/ubicacion-implementacion/findByIdNivelUbicacion/{idNivelUbicacion}";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID_UBIC_NIVEL_IMPL_2 = "/ubicacion-geografica/ubicacion-implementacion/findByIdNivelImplementacionAndIdNivelUbicacion/{idNivelUbicacion}/{idNivelImplementacion}";

    /*==================================================================================================================
     *================================================================================================================ */

    // Organizaciones de la API ******************************************************************************
    // Mapeo de las Rutas del Modulo de Organizaciones
    public static final String ORGANIZACIONES_ENDPOINT = "/organizaciones";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID = "/organizaciones/findByIdOrganizacion/{idOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_CODIGO = "/organizaciones/findByCodOrganizacion/{codOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_CODIGO_COUNT = "/organizaciones/countByCodOrganizacion/{codOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO = "/organizaciones/findByIdTipoOrganizacion/{idTipoOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_PAIS = "/organizaciones/findByIdPaisOrganizacion/{idPaisOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_PAIS = "/organizaciones/findByIdTipoPaisOrganizacion/{idTipoOrganizacion}/{idPaisOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_PAIS_CATEGORIA = "/organizaciones/findByIdTipoPaisCategoriaOrganizacion/{idTipoOrganizacion}/{idPaisOrganizacion}/{idCategoriaOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_NEW = "/organizaciones/new";
    public static final String ORGANIZACIONES_ENDPOINT_EDIT = "/organizaciones/edit/{idOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_DELETE = "/organizaciones/delete/{idOrganizacion}";

    public static final String TIPO_ORGANIZACIONES_ENDPOINT = "/tipos-organizaciones";
    public static final String TIPO_ORGANIZACIONES_ENDPOINT_FIND_BY_ID = "/tipos-organizaciones/findByIdTipoOrganizacion/{idTipoOrganizacion}";
    // public static final String TIPO_ORGANIZACIONES_ENDPOINT_FIND_BY_IDGRUPO = "/tipos-organizaciones/findByIdGrupo/{idGrupo}";
    public static final String TIPO_ORGANIZACIONES_ENDPOINT_NEW = "/tipos-organizaciones/new";
    public static final String TIPO_ORGANIZACIONES_ENDPOINT_EDIT = "/tipos-organizaciones/update/{idTipoOrganizacion}";
    public static final String TIPO_ORGANIZACIONES_ENDPOINT_DELETE = "/tipos-organizaciones/delete/{idTipoOrganizacion}";

    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT = "/categorias-organizaciones";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_FIND_BY_ID = "/categorias-organizaciones/findByIdCatOrganizacion/{idCatOrganizacion}";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_ORGANIZACION = "/categorias-organizaciones/findByIdTipoOrganizacion/{idTipoOrganizacion}";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_NEW = "/categorias-organizaciones/new";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_EDIT = "/categorias-organizaciones/update/{idCatOrganizacion}";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_DELETE = "/categorias-organizaciones/delete/{idCatOrganizacion}";


    /*==================================================================================================================
     *================================================================================================================ */

    // Espacios de Trabajos de la API ******************************************************************************
    // Mapeo de las Rutas del Modulo de Estados
    public static final String ESPACIOS_TRABAJO_ENDPOINT = "/espacios-trabajo";
    public static final String ESPACIOS_TRABAJO_ENDPOINT_FIND_BY_ID = "/espacios-trabajo/findByIdEspacio/{idEspacioTrabajo}";
    public static final String ESPACIOS_TRABAJO_ENDPOINT_NEW = "/espacios-trabajo/new";

    public static final String ESPACIOS_TRABAJO_USUARIO_ENDPOINT = "/espacios-trabajo-usuario";
    public static final String ESPACIOS_TRABAJO_USUARIO_ENDPOINT_FIND_BY_ID = "/espacios-trabajo-usuario/findByIdUsuario/{idUsuarioEspacioTrabajo}";
    public static final String ESPACIOS_TRABAJO_USUARIO_ENDPOINT_NEW = "/espacios-trabajo-usuario/new";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mantenimientos funcionales de la Informacion de las Actividades *************************************************
    // Mapeo de las Rutas del Modulo de Actividades
    public static final String ACTIVITY_ENDPOINT_NEW = "/activities/new-activity";
    public static final String ACTIVITY_ENDPOINT_EDIT = "/activities/edit-activity/{idActivity}";
    public static final String ACTIVITY_ENDPOINT_DELETE = "/activities/delete-activity/{idActivity}";
    public static final String ACTIVITY_ENDPOINT = "/activities";
    public static final String ACTIVITY_ENDPOINT_FIND_BY_ID = "/activities/findByIdActivity/{idActivity}";
    public static final String ACTIVITY_ENDPOINT_FIND_BY_COD = "/activities/findByIdCodActivity/{codActivity}";

    // Mapeo de las Rutas del Sub Modulo de Sector de Ejecucion
    public static final String SECTOR_EJECUTOR_ENDPOINT = "/mant-actividades/sector-ejecutor";
    public static final String SECTOR_EJECUTOR_ENDPOINT_FIND_BY_ID = "/mant-actividades/sector-ejecutor/findById/{idSectorEjecutor}";

    // Mapeo de las Rutas del Sub Modulo de Sector de Ejecucion
    public static final String TIPO_INICIATIVA_ENDPOINT = "/mant-actividades/tipo-iniciativa";
    public static final String TIPO_INICIATIVA_ENDPOINT_FIND_BY_ID = "/mant-actividades/tipo-iniciativa/findById/{idTipoIniciativa}";

    // Mapeo de las Rutas del Sub Modulo de Estrategias
    public static final String ESTRATEGIAS_ENDPOINT = "/mant-actividades/estrategia";
    public static final String ESTRATEGIAS_ENDPOINT_FIND_BY_ID = "/mant-actividades/estrategia/findById/{idEstrategia}";

    // Mapeo de las Rutas del Sub Modulo de Presupuesto
    public static final String PRESUPUESTO_ENDPOINT = "/mant-actividades/presupuesto";
    public static final String PRESUPUESTO_ENDPOINT_FIND_BY_ID = "/mant-actividades/presupuesto/findById/{idEstrategia}";

    // Mapeo de las Rutas del Sub Modulo de Id Internas
    public static final String ID_INTERNA_ENDPOINT = "/mant-actividades/id-interna";
    public static final String ID_INTERNA_ENDPOINT_FIND_BY_CODIGO = "/mant-actividades/id-interna/findByCodInterna/{codIdInterna}";
    public static final String ID_INTERNA_ENDPOINT_FIND_BY_CODIGO_COUNT = "/mant-actividades/id-interna/countByCodInterna/{codIdInterna}";
    public static final String ID_INTERNA_ENDPOINT_NEW = "/mant-actividades/id-interna/new";
    public static final String ID_INTERNA_ENDPOINT_EDIT = "/mant-actividades/id-interna/edit/{idInterna}";
    public static final String ID_INTERNA_ENDPOINT_DELETE = "/mant-actividades/id-interna/delete/{codIdInterna}";

    // Mapeo de las Rutas del Sub Modulo de Planificacion
    public static final String ID_PLANIFICACION_ENDPOINT = "/mant-actividades/planificacion";
    public static final String ID_PLANIFICACION_ENDPOINT_FIND_BY_ID_ACTIVIDAD_PLAN = "/mant-actividades/planificacion/findByIdActividadPlan/{idActividadPlan}";
    // public static final String ID_PLANIFICACION_ENDPOINT_FIND_BY_CODIGO_COUNT = "/mant-actividades/id-interna/countByCodInterna/{codIdInterna}";
    public static final String ID_PLANIFICACION_ENDPOINT_NEW = "/mant-actividades/planificacion/new";
    public static final String ID_PLANIFICACION_ENDPOINT_EDIT = "/mant-actividades/planificacion/edit/{idActividadPlan}";

    // Mapeo de las Rutas del Sub Modulo de Ubicaciones
    public static final String UBICACIONES_ACT_ENDPOINT = "/mant-actividades/ubicaciones";
    public static final String UBICACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/ubicaciones/findByIdActividad/{idActividad}";
    public static final String UBICACIONES_ACT_ENDPOINT_NEW = "/mant-actividades/ubicaciones/new";
    public static final String UBICACIONES_ACT_ENDPOINT_DELETE = "/mant-actividades/ubicaciones/delete/{idUbicacionImpl}/{idActividad}";

    // Mapeo de las Rutas del Sub Modulo Recursos del Proyecto
    public static final String RECURSOS_DOC_ENDPOINT = "/mant-actividades/recursos-proyecto";
    public static final String RECURSOS_DOC_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/recursos-proyecto/findByCodActividadRecurso/{codActividadRecurso}";
    public static final String RECURSOS_DOC_ENDPOINT_NEW = "/mant-actividades/recursos-proyecto/new";
    public static final String RECURSOS_DOC_ENDPOINT_DELETE = "/mant-actividades/recursos-proyecto/delete/{idActividadDocumento}";
    public static final String RECURSOS_DOC_UPLOAD_FILE = "/mant-actividades/recursos-proyecto/uploadFile";
    public static final String RECURSOS_DOC_UPLOAD_FILE_ARRAY = "/mant-actividades/recursos-proyecto/uploadMultipleFiles";
    public static final String RECURSOS_DOC_UPLOAD_FILE_DOWLOAD = "/mant-actividades/recursos-proyecto/downloadFile/{fileName:.+}";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas de Sectores y Programas
    public static final String SECTORES_OCDE_ENDPOINT = "/sectores/ocde-cad";
    public static final String SECTORES_OCDE_ENDPOINT_FIND_BY_ID_SECTOR = "/sectores/ocde-cad/findByIdSector/{idSector}";
    public static final String SECTORES_OCDE_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE = "/sectores/ocde-cad/findByIdNivelSector/{idNivelSector}/findBySectorPadreId/{sectorPadreId}";
    public static final String SECTORES_OCDE_ENDPOINT_FIND_BY_ID_NIVEL_SECTOR = "/sectores/ocde-cad/findByIdNivelSector/{idNivelSector}";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas del Modulo de Usuarios
    public static final String USUARIOS_ENDPOINT = "/usuarios";
    public static final String USUARIOS_ENDPOINT_FIND_BY_COD = "/usuarios/findByCod/{codUsuario}";
    public static final String USUARIOS_ENDPOINT_FIND_BY_ID = "/usuarios/findById/{idUsuario}";
    public static final String USUARIOS_ENDPOINT_FIND_BY_MAIL = "/usuarios/findByMail/{emailUsuario}";
    public static final String USUARIOS_ENDPOINT_NEW = "/usuarios/new";
    public static final String USUARIOS_ENDPOINT_UPDATE = "/usuarios/update/{idUsuario}";
    public static final String USUARIOS_ENDPOINT_DELETE = "/usuarios/delete/{idUsuario}";
    public static final String USUARIOS_ENDPOINT_TIPO = "/usuarios/kind";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas del Modulo de Perfiles de Usuario
    public static final String PERFILES_ENDPOINT = "/profiles";
    public static final String PERFILES_ENDPOINT_NEW = "/profiles/new";
    public static final String PERFILES_ENDPOINT_EDIT = "/profiles/edit/{idPerfil}";
    public static final String PERFILES_ENDPOINT_DELETE = "/profiles/delete/{idPerfil}";
    public static final String PERFILES_ENDPOINT_TIPOS = "/profiles/kind";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas del Modulo de Secuencias
    public static final String SECUENCIAS_ENDPOINT = "/secuencias";
    public static final String SECUENCIAS_ENDPOINT_FIND_BY_ID = "/secuencias/findByIdSecuencia/{idSecuencia}";
    public static final String SECUENCIAS_ENDPOINT_FIND_BY_COD = "/secuencias/findByCodSecuencia/{codSecuencia}";
    public static final String SECUENCIAS_ENDPOINT_UPDATE = "/secuencias/update/{idSecuencia}";

    // Mapeo de las Rutas del Modulo de Roles
    public static final String ROLES_ENDPOINT = "/roles";
    public static final String ROLES_FIND_BY_ID_GRUPO = "/roles/findByIdGrupo/{idGrupo}";

}
