package com.api.pgc.core.APIRestPGC.repository.mantenimiento;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GruposRepository extends JpaRepository<TblGrupo, Integer> {

    /**
     * Metodo que despliega el Grupos de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/04/2018/v1.0
     * @return Grupos de la BD, por paramtro de ID
     * @param idGrupo
     */
    TblGrupo findByIdGrupo(long idGrupo);
}
