/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades;


import com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo.TblEspaciosTrabajo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.*;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades",
        indexes = {@Index(name = "idx_codigo_actividad", columnList = "CODIGO_ACTIVIDAD")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_ACTIVIDAD"})})
public class TblActividad {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTIVIDAD", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividad;

    // Datos Generales de la Actividad
    @Column(name = "CODIGO_ACTIVIDAD", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Actividad", required = true)
    private String codigoActividad;

    // Mapeo de la Relacion de la Tabla de Estados con Activiades
    // Muchos Actividad = 1 Estado
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ApiModelProperty(notes = "Entidad del Estado de la Actividad, se envia desde un Json (\"idEstadoActivity\": { \"idEstado\": \"valor\" })", required = true)
    private TblEstado idEstadoActivity;

    @Column(name = "EXPLICACION_ESTADO")
    @ApiModelProperty(notes = "Explicacion del Estado")
    private String explicacionEstado;

    @Column(name = "ANTECEDENTES_ATIVIDAD")
    @ApiModelProperty(notes = "Actecedentes de Actividad")
    private String antecedentesActividad;

    @Column(name = "OBJETIVO_ACTIVIDAD")
    @ApiModelProperty(notes = "Objetivos de Actividad")
    private String objetivoActividad;

    @Column(name = "DESCRIPCION_ACTIVIDAD")
    @ApiModelProperty(notes = "Descripcion de Actividad")
    private String descripcionActividad;

    @Column(name = "CONDICIONES_ACTIVIDAD")
    @ApiModelProperty(notes = "Condiciones Previas de Actividad")
    private String condicionesActividad;

    @Column(name = "CODIGO_SIAFI_BIP")
    @ApiModelProperty(notes = "Código BIP de SIAFI")
    private String codigoSIAFIBIP;


    // Mapeo de la Relacion Planificacion
    // Muchos Actividad = 1 Estrategia
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ESTRATEGIA", referencedColumnName = "ID_ESTRATEGIA")
    @ApiModelProperty(notes = "Entidad de la Estrategia de la Actividad, se envia desde un Json (\"idEstrategiaActivity\": { \"idEstrategia\": \"valor\" })")
    private TblEstrategiasActividad idEstrategiaActivity;

    // Muchos Actividad = 1 Estrategia
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PRESUPUESTO", referencedColumnName = "ID_PRESUPUESTO")
    @ApiModelProperty(notes = "Entidad del Presupuesto de la Actividad, se envia desde un Json (\"idPresupuestoActivity\": { \"idPresupuesto\": \"valor\" })")
    private TblPresupuestoActividad idPresupuestoActivity;

    // Muchos Actividad = 1 Sector Ejecutor
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SECTOR_EJECUTOR", referencedColumnName = "ID_SECTOR_EJECUTOR")
    @ApiModelProperty(notes = "Entidad del Sector Ejecutor de la Actividad, se envia desde un Json (\"idSectorEjecutorActivity\": { \"idSectorEjecutor\": \"valor\" })")
    private TblSectorEjecutor idSectorEjecutorActivity;

    // Resultados
    @Column(name = "RESULTADOS_ESPERADOS")
    @ApiModelProperty(notes = "Resultados Esperados")
    private String resultadosEsperados;

    @Column(name = "RESULTADOS_ALA_FECHA")
    @ApiModelProperty(notes = "Resultados ala Fecha")
    private String resultadosAlaFecha;

    @Column(name = "JUSTIFICACION_ACTIVIDAD")
    @ApiModelProperty(notes = "Justificacion Atividad")
    private String justificacionActividad;

    @Column(name = "COSTO_ACTIVIDAD")
    @ApiModelProperty(notes = "Costo Actividad")
    private double costoActividad;

    // Muchos Actividad = 1 Moneda
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MONEDA_ACTIVIDAD", referencedColumnName = "ID_MONEDA_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad de la Moneda de la Actividad, se envia desde un Json (\"idMonedaActividadActivity\": { \"idMonedaActividad\": \"valor\" })")
    private TblMonedaActividad idMonedaActividadActivity;

    @Column(name = "NOMBRE_ACTIVIDAD")
    @ApiModelProperty(notes = "Nombre Actividad")
    private String nombreActividad;

    @Column(name = "PRODUCTOS_ESPERADOS")
    @ApiModelProperty(notes = "Productos Esperados")
    private String productosEsperados;

    // Auditoria de Datos
    @Column(name = "FECHA_FINANCIAMIENTO", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Financiamiento")
    private Date fechaFinanciamiento;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Creacion")
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    //@ApiModelProperty(notes = "Hora de Creacion de la Actividad, formato hh:mm:ss")
    private Date horaCreacion = new Date();

    // Muchos Actividad = 1 Usuario
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO_CREADOR", referencedColumnName = "ID_USUARIO")
    @ApiModelProperty(notes = "Entidad de Usuario de la Actividad, se envia desde un Json (\"idUsuarioCreador\": { \"idUsuario\": \"valor\" })")
    private TblUsuarios idUsuarioCreador;

    // Muchos Actividad = 1 Espacio de Trabajo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ESPACIO_TRABAJO", referencedColumnName = "ID_ESPACIO_TRABAJO")
    @ApiModelProperty(notes = "Entidad de Espacio de Trabajo de la Actividad, se envia desde un Json (\"idEspacioTrabajoActivity\": { \"idEspacioTrabajo\": \"valor\" })")
    private TblEspaciosTrabajo idEspacioTrabajoActivity;

    @Column(name = "ACTIVO")
    // @ApiModelProperty(notes = "Nombre Actividad")
    private boolean activo = true;

    // Mapeo de la Relacion de la Tabla de Estados con Activiades
    // Muchos Actividad = 1 Estado
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ESTADO_VALID", referencedColumnName = "ID_ESTADO")
    // @ApiModelProperty(notes = "Entidad del Estado de la validacion del Proyecto, se envia desde un Json (\"idEstadoValid\": { \"idEstadov\": \"valor\" })", required = true)
    private TblEstado idEstadoValid;

    // Mapeo de la Relacion de la Tabla de Tipo de Iniciativa con Activiades
    // Muchos Actividad = 1 Tipo de Iniciativa
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_INICIATIVA_CSS_ACT", referencedColumnName = "ID_TIPO_INICIATIVA")
    // @ApiModelProperty(notes = "Entidad del Tipo de Iniciativa de la los Proyectos CSS, se envia desde un Json (\"idTipoIniciativaCssAct\": { \"idTipoIniciativaCss\": \"valor\" })", required = true)
    private TblTipoIniciativaCss idTipoIniciativaCssAct;

    @Column(name = "ACTIVIDADES_CSS")
    @ApiModelProperty(notes = "Actividades CSS")
    private String actividadesCss;

    @Column(name = "FECHA_MODIFICACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Modificacion de Proyecto")
    private Date fechaModificacion;

    @Column(name = "HORA_MODIFICACION")
    @Temporal(TemporalType.TIME)
    private Date horaModificacion;

    // Muchos Actividad = 1 Usuario
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO_MOD", referencedColumnName = "ID_USUARIO")
    @ApiModelProperty(notes = "Entidad de Usuario que Modifica la Actividad, se envia desde un Json (\"idUsuarioMod\": { \"idUsuario\": \"valor\" })")
    private TblUsuarios idUsuarioMod;

    /*
     * Constructor vacio de la Clase, solo para Jpa
     */
    public TblActividad() {
        //Este lo usa Jpa para realizar los Mapping
    }


    /*
     * Metodos Getters y Setters de la Clase
     * 2019-01-11
    */

    public long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(long idActividad) {
        this.idActividad = idActividad;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public TblEstado getIdEstadoActivity() {
        return idEstadoActivity;
    }

    public void setIdEstadoActivity(TblEstado idEstadoActivity) {
        this.idEstadoActivity = idEstadoActivity;
    }

    public String getExplicacionEstado() {
        return explicacionEstado;
    }

    public void setExplicacionEstado(String explicacionEstado) {
        this.explicacionEstado = explicacionEstado;
    }

    public String getAntecedentesActividad() {
        return antecedentesActividad;
    }

    public void setAntecedentesActividad(String antecedentesActividad) {
        this.antecedentesActividad = antecedentesActividad;
    }

    public String getObjetivoActividad() {
        return objetivoActividad;
    }

    public void setObjetivoActividad(String objetivoActividad) {
        this.objetivoActividad = objetivoActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public String getCondicionesActividad() {
        return condicionesActividad;
    }

    public void setCondicionesActividad(String condicionesActividad) {
        this.condicionesActividad = condicionesActividad;
    }

    public String getCodigoSIAFIBIP() {
        return codigoSIAFIBIP;
    }

    public void setCodigoSIAFIBIP(String codigoSIAFIBIP) {
        this.codigoSIAFIBIP = codigoSIAFIBIP;
    }

    public TblEstrategiasActividad getIdEstrategiaActivity() {
        return idEstrategiaActivity;
    }

    public void setIdEstrategiaActivity(TblEstrategiasActividad idEstrategiaActivity) {
        this.idEstrategiaActivity = idEstrategiaActivity;
    }

    public TblPresupuestoActividad getIdPresupuestoActivity() {
        return idPresupuestoActivity;
    }

    public void setIdPresupuestoActivity(TblPresupuestoActividad idPresupuestoActivity) {
        this.idPresupuestoActivity = idPresupuestoActivity;
    }

    public TblSectorEjecutor getIdSectorEjecutorActivity() {
        return idSectorEjecutorActivity;
    }

    public void setIdSectorEjecutorActivity(TblSectorEjecutor idSectorEjecutorActivity) {
        this.idSectorEjecutorActivity = idSectorEjecutorActivity;
    }

    public String getResultadosEsperados() {
        return resultadosEsperados;
    }

    public void setResultadosEsperados(String resultadosEsperados) {
        this.resultadosEsperados = resultadosEsperados;
    }

    public String getResultadosAlaFecha() {
        return resultadosAlaFecha;
    }

    public void setResultadosAlaFecha(String resultadosAlaFecha) {
        this.resultadosAlaFecha = resultadosAlaFecha;
    }

    public String getJustificacionActividad() {
        return justificacionActividad;
    }

    public void setJustificacionActividad(String justificacionActividad) {
        this.justificacionActividad = justificacionActividad;
    }

    public double getCostoActividad() {
        return costoActividad;
    }

    public void setCostoActividad(double costoActividad) {
        this.costoActividad = costoActividad;
    }

    public TblMonedaActividad getIdMonedaActividadActivity() {
        return idMonedaActividadActivity;
    }

    public void setIdMonedaActividadActivity(TblMonedaActividad idMonedaActividadActivity) {
        this.idMonedaActividadActivity = idMonedaActividadActivity;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getProductosEsperados() {
        return productosEsperados;
    }

    public void setProductosEsperados(String productosEsperados) {
        this.productosEsperados = productosEsperados;
    }

    public Date getFechaFinanciamiento() {
        return fechaFinanciamiento;
    }

    public void setFechaFinanciamiento(Date fechaFinanciamiento) {
        this.fechaFinanciamiento = fechaFinanciamiento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public TblUsuarios getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    public void setIdUsuarioCreador(TblUsuarios idUsuarioCreador) {
        this.idUsuarioCreador = idUsuarioCreador;
    }

    public TblEspaciosTrabajo getIdEspacioTrabajoActivity() {
        return idEspacioTrabajoActivity;
    }

    public void setIdEspacioTrabajoActivity(TblEspaciosTrabajo idEspacioTrabajoActivity) {
        this.idEspacioTrabajoActivity = idEspacioTrabajoActivity;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TblEstado getIdEstadoValid() {
        return idEstadoValid;
    }

    public void setIdEstadoValid(TblEstado idEstadoValid) {
        this.idEstadoValid = idEstadoValid;
    }

    public TblTipoIniciativaCss getIdTipoIniciativaCssAct() {
        return idTipoIniciativaCssAct;
    }

    public void setIdTipoIniciativaCssAct(TblTipoIniciativaCss idTipoIniciativaCssAct) {
        this.idTipoIniciativaCssAct = idTipoIniciativaCssAct;
    }

    public String getActividadesCss() {
        return actividadesCss;
    }

    public void setActividadesCss(String actividadesCss) {
        this.actividadesCss = actividadesCss;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(Date horaModificacion) {
        this.horaModificacion = horaModificacion;
    }

    public TblUsuarios getIdUsuarioMod() {
        return idUsuarioMod;
    }

    public void setIdUsuarioMod(TblUsuarios idUsuarioMod) {
        this.idUsuarioMod = idUsuarioMod;
    }
}
