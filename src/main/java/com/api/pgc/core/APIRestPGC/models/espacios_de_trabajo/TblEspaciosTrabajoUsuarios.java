package com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo;

import com.api.pgc.core.APIRestPGC.models.seguridad.TblRoles;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_espacios_trabajo_usuarios",
        indexes = {@Index(name = "idx_cod_espacio_trabajo_user", columnList = "COD_ESPACIO_TRABAJO_USUARIO")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_ESPACIO_TRABAJO_USUARIO"})})
public class TblEspaciosTrabajoUsuarios {
    // Nueva Clase para los Espacios de Trabajo Usuarios
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESPACIOS_TRABAJO_USUARIOS", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idEspacioTrabajoUsuario;

    @Column(name = "COD_ESPACIO_TRABAJO_USUARIO", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo Espacio de Trabajo de Usuario", required = true)
    private String codEspacioTrabajoUsuario;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date fechaCreacion= new Date();

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();

    @Column(name = "ACTIVO")
    private boolean activo;

    // Mapeo de la Relacion de la Tabla de Espacio de Trabajo con Espacios de Trabajo
    // Muchos Espacio de Trabajo Usuario = 1 Espacio de Trabajo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ESPACIO_TRABAJO", referencedColumnName = "ID_ESPACIO_TRABAJO")
    @ApiModelProperty(notes = "Entidad de espacio de trabajo, se envia desde un Json (\"idEspacioTrabajo\": { \"idEspacioTrabajo\": \"valor\" })", required = true)
    private TblEspaciosTrabajo idEspacioTrabajo;


    // Mapeo de la Relacion de la Tabla de Espacio de Trabajo con Espacios de Trabajo
    // Muchos Espacio de Trabajo Usuario = 1 Espacio de Trabajo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO_ESPACIO_TRABAJO", referencedColumnName = "ID_USUARIO")
    @ApiModelProperty(notes = "Entidad de usuario quye tendra el espacio de trabajo asignado, se envia desde un Json (\"idUsuarioEspacioTrabajo\": { \"idUsuario\": \"valor\" })", required = true)
    private TblUsuarios idUsuarioEspacioTrabajo;

    // Mapeo de la Relacion de la Tabla de usuarios con Espacios de Trabajo
    // Muchos Espacio de Rol = 1 Espacio de Trabajo Usuario
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ROL_ESPACIO_TRABAJO", referencedColumnName = "ID_ROL")
    @ApiModelProperty(notes = "Entidad de Roles que tendra el espacio de trabajo asignado, se envia desde un Json (\"idRolEspacioTrabajo\": { \"idRol\": \"valor\" })", required = true)
    private TblRoles idRolEspacioTrabajo;


    /**
     * Constructor de la Clase
     */
    public TblEspaciosTrabajoUsuarios() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdEspacioTrabajoUsuario() {
        return idEspacioTrabajoUsuario;
    }

    public void setIdEspacioTrabajoUsuario(long idEspacioTrabajoUsuario) {
        this.idEspacioTrabajoUsuario = idEspacioTrabajoUsuario;
    }

    public String getCodEspacioTrabajoUsuario() {
        return codEspacioTrabajoUsuario;
    }

    public void setCodEspacioTrabajoUsuario(String codEspacioTrabajoUsuario) {
        this.codEspacioTrabajoUsuario = codEspacioTrabajoUsuario;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TblEspaciosTrabajo getIdEspacioTrabajo() {
        return idEspacioTrabajo;
    }

    public void setIdEspacioTrabajo(TblEspaciosTrabajo idEspacioTrabajo) {
        this.idEspacioTrabajo = idEspacioTrabajo;
    }

    public TblUsuarios getIdUsuarioEspacioTrabajo() {
        return idUsuarioEspacioTrabajo;
    }

    public void setIdUsuarioEspacioTrabajo(TblUsuarios idUsuarioEspacioTrabajo) {
        this.idUsuarioEspacioTrabajo = idUsuarioEspacioTrabajo;
    }

    public TblRoles getIdRolEspacioTrabajo() {
        return idRolEspacioTrabajo;
    }

    public void setIdRolEspacioTrabajo(TblRoles idRolEspacioTrabajo) {
        this.idRolEspacioTrabajo = idRolEspacioTrabajo;
    }
}
