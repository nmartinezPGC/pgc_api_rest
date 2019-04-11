package com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.TblSectorEjecutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorEjecutorRepository extends JpaRepository<TblSectorEjecutor, Integer> {

    /**
     * Metodo que despliega el Esatdo de la BD
     * @autor Nahum Martinez | NAM
     * @version  22/08/2018/v1.0
     * @return Esatdo de la BD, por paramtro de ID
     * @param idSectorEjecutor
     */
    TblSectorEjecutor findByIdSectorEjecutor(long idSectorEjecutor);

}
