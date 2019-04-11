package com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_espacios_trabajo",
        indexes = {@Index(name = "idx_cod_espacio_trabajo", columnList = "COD_ESPACIO_TRABAJO" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_ESPACIO_TRABAJO"})})
public class TblEspaciosTrabajo {
    // Nueva Clase para los Espacios de Trabajo NAM 2
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ESPACIO_TRABAJO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idEspacioTrabajo;

    @Column(name = "COD_ESPACIO_TRABAJO", nullable = false, length=50)
    @ApiModelProperty(notes = "Codigo Espacio de Trabajo", required = true)
    private String codEspacioTrabajo;

    @Column(name = "NOMBRE_ESPACIO_TRABAJO", nullable = false, length=200)
    @ApiModelProperty(notes = "Nombre del Espacio de Trabajo", required = true)
    private String nombreEspacioTrabajo;

    @Column(name = "DESCRIPCION_ESPACIO_TRABAJO", nullable = false, length=500)
    @ApiModelProperty(notes = "Descripcion del Espacio de Trabajo")
    private String descripcionEspacioTrabajo;

    @Column(name = "ESPACIO_PADRE")
    private boolean espacioPadre;

    @Column(name = "VISTA_PUBLICA")
    private boolean vistaPublica;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    //@ApiModelProperty(notes = "Fecha de Creacion del Usuario, formato hh:mm:ss")
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    //@ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss")
    private Date horaCreacion = new Date();


    //Mapeo de la Relacion de la Tabla de Tipos con Espacios de Trabajo
    // Muchos Espacio de Trabajo = 1 Tipo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_ESPACIO_TRABAJO", referencedColumnName = "ID_TIPO")
    @ApiModelProperty(notes = "Entidad del Tipo de espacio de trabajo, se envia desde un Json (\"idTipoEspacioTrabajo\": { \"idTipo\": \"valor\" })", required = true)
    private TblTipo idTipoEspacioTrabajo;


    //Mapeo de la Relacion de la Tabla de Estados con Espacios de Trabajo
    // Muchos Espacio de Trabajo = 1 Estado
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ESTADO_ESPACIO_TRABAJO", referencedColumnName = "ID_ESTADO")
    @ApiModelProperty(notes = "Entidad del Estado de espacio de trabajo, se envia desde un Json (\"idEstadoEspacioTrabajo\": { \"idEstado\": \"valor\" })", required = true)
    private TblEstado idEstadoEspacioTrabajo;


    //Mapeo de la Relacion de la Tabla de Estados con Espacios de Trabajo
    // Muchos Espacio de Trabajo = 1 Pais
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS")
    @ApiModelProperty(notes = "Entidad del Pais de espacio de trabajo, se envia desde un Json (\"idPais\": { \"idPais\": \"valor\" })", required = true)
    private TblPais idPais;


    /**
     * Constructor de la Clase
     */
    public TblEspaciosTrabajo() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdEspacioTrabajo() {
        return idEspacioTrabajo;
    }

    public void setIdEspacioTrabajo(long idEspacioTrabajo) {
        this.idEspacioTrabajo = idEspacioTrabajo;
    }

    public String getCodEspacioTrabajo() {
        return codEspacioTrabajo;
    }

    public void setCodEspacioTrabajo(String codEspacioTrabajo) {
        this.codEspacioTrabajo = codEspacioTrabajo;
    }

    public String getNombreEspacioTrabajo() {
        return nombreEspacioTrabajo;
    }

    public void setNombreEspacioTrabajo(String nombreEspacioTrabajo) {
        this.nombreEspacioTrabajo = nombreEspacioTrabajo;
    }

    public String getDescripcionEspacioTrabajo() {
        return descripcionEspacioTrabajo;
    }

    public void setDescripcionEspacioTrabajo(String descripcionEspacioTrabajo) {
        this.descripcionEspacioTrabajo = descripcionEspacioTrabajo;
    }

    public boolean isEspacioPadre() {
        return espacioPadre;
    }

    public void setEspacioPadre(boolean espacioPadre) {
        this.espacioPadre = espacioPadre;
    }

    public boolean isVistaPublica() {
        return vistaPublica;
    }

    public void setVistaPublica(boolean vistaPublica) {
        this.vistaPublica = vistaPublica;
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

    public TblTipo getIdTipoEspacioTrabajo() {
        return idTipoEspacioTrabajo;
    }

    public void setIdTipoEspacioTrabajo(TblTipo idTipoEspacioTrabajo) {
        this.idTipoEspacioTrabajo = idTipoEspacioTrabajo;
    }

    public TblEstado getIdEstadoEspacioTrabajo() {
        return idEstadoEspacioTrabajo;
    }

    public void setIdEstadoEspacioTrabajo(TblEstado idEstadoEspacioTrabajo) {
        this.idEstadoEspacioTrabajo = idEstadoEspacioTrabajo;
    }

    public TblPais getIdPais() {
        return idPais;
    }

    public void setIdPais(TblPais idPais) {
        this.idPais = idPais;
    }
}
