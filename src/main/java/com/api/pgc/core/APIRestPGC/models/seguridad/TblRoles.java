package com.api.pgc.core.APIRestPGC.models.seguridad;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_roles",
        indexes = {@Index(name = "idx_cod_rol", columnList = "COD_ROL" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_ROL"})})
public class TblRoles {
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ROL", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idRol;

    @Column(name = "COD_ROL", nullable = false, length=10)
    @ApiModelProperty(notes = "Codigo Rol", required = true)
    private String codUsuario;

    @Column(name = "DESC_ROL", nullable = false, length=100)
    @ApiModelProperty(notes = "Descripcion del Rol", required = true)
    private String nombre1Usuario;

    @Column(name = "HABILITADA")
    @ApiModelProperty(notes = "Habilitado")
    private boolean habilitada;

    @Column(name = "PERMISO_LECTURA")
    @ApiModelProperty(notes = "Habilitado")
    private boolean permisoLectura;

    @Column(name = "PERMISO_ESCRITURA")
    @ApiModelProperty(notes = "Habilitado")
    private boolean permisoEscritura;

    @Column(name = "PERMISO_BORRAR")
    @ApiModelProperty(notes = "Habilitado")
    private boolean permisoBorrar;

    @Column(name = "PERMISO_APROBAR")
    @ApiModelProperty(notes = "Habilitado")
    private boolean permisoAprobar;


    // Mapeo de la Relacion de la Tabla de Roles con Grupos
    // Muchos Rol = 1 Grupo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ApiModelProperty(notes = "Entidad del Grupo de Rol, se envia desde un Json (\"idGrupo\": { \"idGrupo\": \"valor\" })", required = true)
    private TblGrupo idGrupo;


    /**
     * Constructor de la Clase
     */
    public TblRoles() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombre1Usuario() {
        return nombre1Usuario;
    }

    public void setNombre1Usuario(String nombre1Usuario) {
        this.nombre1Usuario = nombre1Usuario;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public boolean isPermisoLectura() {
        return permisoLectura;
    }

    public void setPermisoLectura(boolean permisoLectura) {
        this.permisoLectura = permisoLectura;
    }

    public boolean isPermisoEscritura() {
        return permisoEscritura;
    }

    public void setPermisoEscritura(boolean permisoEscritura) {
        this.permisoEscritura = permisoEscritura;
    }

    public boolean isPermisoBorrar() {
        return permisoBorrar;
    }

    public void setPermisoBorrar(boolean permisoBorrar) {
        this.permisoBorrar = permisoBorrar;
    }

    public boolean isPermisoAprobar() {
        return permisoAprobar;
    }

    public void setPermisoAprobar(boolean permisoAprobar) {
        this.permisoAprobar = permisoAprobar;
    }

    public TblGrupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(TblGrupo idGrupo) {
        this.idGrupo = idGrupo;
    }
}
