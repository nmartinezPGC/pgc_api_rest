package com.api.pgc.core.APIRestPGC.repository.mantenimiento;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstadosRepository extends JpaRepository<TblEstado, Integer> {

    /**
     * Metodo que despliega el Esatdo de la BD
     *
     * @param idEstado
     * @return Esatdo de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 11/04/2018/v1.0
     */
    TblEstado findByIdEstado(long idEstado);


    /**
     * Metodo que despliega el Esatdo de la BD
     *
     * @param tblGrupo
     * @return Esatdo de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 11/04/2018/v1.0
     */
    List<TblEstado> findByIdGrupo(TblGrupo tblGrupo);


    @Query("SELECT e FROM TblEstado e WHERE idGrupo = :idGrupo ")
    List<TblEstado> getEstadosGrupos(@Param("idGrupo") TblGrupo idGrupo);

}
