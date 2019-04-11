/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.sectores;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_sectores_ocde_cad",
        indexes = {@Index(name = "idx_codigo_sector", columnList = "CODIGO_SECTOR")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_SECTOR"})})
public class TblSectorOcdeCad {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SECTOR", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idSector;

    @Column(name = "CODIGO_SECTOR", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Sector OCDE/CAD", required = true)
    private String codigoSector;

    @Column(name = "NOMBRE_SECTOR", nullable = false, length = 250)
    @ApiModelProperty(notes = "Nombre del Sector OCDE/CAD")
    private String nombreSector;

    @Column(name = "DESCRIPCION_SECTOR", nullable = false, length = 250)
    @ApiModelProperty(notes = "Descripcion del Sector OCDE/CAD")
    private String descripcionSector;

    @Column(name = "IMAGEN_SECTOR", nullable = false, length = 300)
    @ApiModelProperty(notes = "Imagen del Sector OCDE/CAD")
    private String imagenSector;

    @Column(name = "ACTIVO")
    private boolean activo;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();

    // Mapeo de la Relacion de la Tabla de Sectores OCDE con Tipo Sector
    // Muchos Sector = 1 Tipo Sector
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_SECTOR", referencedColumnName = "ID_TIPO_SECTOR")
    @ApiModelProperty(notes = "Entidad del Tipo de Sector, se envia desde un Json (\"idTipoSector\": { \"idTipoSector\": \"valor\" })",
            required = true)
    private TblTipoSector idTipoSector;


    // Mapeo de la Relacion de la Tabla de Sectores OCDE con Sectores OCDE Padre
    // Muchos Sector = 1 Sector OCDE Padre
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SECTOR_PADRE_ID", referencedColumnName = "ID_SECTOR")
    @ApiModelProperty(notes = "Entidad del Sector OCDE, se envia desde un Json (\"sectorPadreId\": { \"idSector\": \"valor\" })",
            required = true)
    private TblSectorOcdeCad sectorPadreId;


    // Mapeo de la Relacion de la Tabla de Sectores OCDE con Nivel de Sectores
    // Muchos Sector = 1 Nivel de Sector
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NIVEL_SECTOR", referencedColumnName = "ID_NIVEL_SECTOR")
    @ApiModelProperty(notes = "Entidad del Nivel de Sector OCDE, se envia desde un Json (\"idNivelSector\": { \"idNivelSector\": \"valor\" })",
            required = true)
    private TblNivelSector idNivelSector;


    /**
     * Constructor de la Clase
     */
    public TblSectorOcdeCad() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdSector() {
        return idSector;
    }

    public void setIdSector(long idSector) {
        this.idSector = idSector;
    }

    public String getCodigoSector() {
        return codigoSector;
    }

    public void setCodigoSector(String codigoSector) {
        this.codigoSector = codigoSector;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    public String getDescripcionSector() {
        return descripcionSector;
    }

    public void setDescripcionSector(String descripcionSector) {
        this.descripcionSector = descripcionSector;
    }

    public String getImagenSector() {
        return imagenSector;
    }

    public void setImagenSector(String imagenSector) {
        this.imagenSector = imagenSector;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public TblTipoSector getIdTipoSector() {
        return idTipoSector;
    }

    public void setIdTipoSector(TblTipoSector idTipoSector) {
        this.idTipoSector = idTipoSector;
    }

    public TblSectorOcdeCad getSectorPadreId() {
        return sectorPadreId;
    }

    public void setSectorPadreId(TblSectorOcdeCad sectorPadreId) {
        this.sectorPadreId = sectorPadreId;
    }

    public TblNivelSector getIdNivelSector() {
        return idNivelSector;
    }

    public void setIdNivelSector(TblNivelSector idNivelSector) {
        this.idNivelSector = idNivelSector;
    }
}
