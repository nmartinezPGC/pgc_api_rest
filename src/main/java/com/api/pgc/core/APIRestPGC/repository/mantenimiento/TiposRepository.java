package com.api.pgc.core.APIRestPGC.repository.mantenimiento;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TiposRepository  extends JpaRepository<TblTipo, Integer> {

    /**
     * Metodo que despliega el Tipo de la BD
     * @autor Nahum Martinez | NAM
     * @version  12/04/2018/v1.0
     * @return Tipo de la BD, por paramtro de ID
     * @param idTipo
     */
    TblTipo findByIdTipo(long idTipo);

    /**
     * Metodo que despliega los Tipos de Perfil de la BD
     * @autor Nahum Martinez | NAM
     * @version  08/01/2019/v1.0
     * @return Listado de los Tipo de Perfil de la BD, por parametro de ID Grupo
     * @param tblGrupo
     */
    List<TblTipo> findByIdGrupo(TblGrupo tblGrupo);

    /**
     * Metodo que despliega los Tipos de Perfiles de la BD con el Grupo como parametro
     *
     * @param tblGrupo
     * @return Tipos de Perfiles de la BD, por paramtro de ID Grupo
     * @autor Nahum Martinez | NAM
     * @version 08/01/2019/v1.0
     */
    @Query("SELECT COUNT(e) FROM TblTipo e WHERE e.idGrupo = :idGrupo ")
    List<TblTipo> getCountTiposPerfiles(@Param("idGrupo") TblGrupo tblGrupo);

}
