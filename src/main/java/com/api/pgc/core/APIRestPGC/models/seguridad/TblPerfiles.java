package com.api.pgc.core.APIRestPGC.models.seguridad;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_perfiles",
        indexes = {@Index(name = "idx_cod_rol", columnList = "COD_PERFIL" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_PERFIL"})})
public class TblPerfiles {
    // Propiedades de la tabla
    @Id
    // @GeneratedValue(strategy=GenerationType.AUTO)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_PERFIL", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idPerfil;

    @Column(name = "COD_PERFIL", nullable = false, length=80)
    @ApiModelProperty(notes = "Codigo Perfil", required = true)
    private String codPerfil;

    @Column(name = "DESC_PERFIL", nullable = false, length=120)
    @ApiModelProperty(notes = "Descripcion del Perfil", required = true)
    private String descPerfil;

    @Column(name = "ACTIVADO")
    @ApiModelProperty(notes = "Habilitado")
    private boolean activado;

    // Mapeo de la Relacion de la Tabla de Tipos con Perfiles
    // Muchos Tipos = 1 Perfil
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_PERFIL", referencedColumnName = "ID_TIPO")
    @ApiModelProperty(notes = "Entidad del Tipo de Perfil, se envia desde un Json (\"idTipoPerfil\": { \"idTipoPerfil\": \"valor\" })", required = true)
    private TblTipo idTipoPerfil;

    /**
     * Constructor de la Clase
     */
    public TblPerfiles() {
    }

    /**
     * Metodos Getters y Setters
     */
    public long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(String codPerfil) {
        this.codPerfil = codPerfil;
    }

    public String getDescPerfil() {
        return descPerfil;
    }

    public void setDescPerfil(String descPerfil) {
        this.descPerfil = descPerfil;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public TblTipo getIdTipoPerfil() {
        return idTipoPerfil;
    }

    public void setIdTipoPerfil(TblTipo idTipoPerfil) {
        this.idTipoPerfil = idTipoPerfil;
    }
}
