package com.api.pgc.core.APIRestPGC.repository.espacios_de_trabajo;

import com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo.TblEspaciosTrabajo;
import com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo.TblEspaciosTrabajoUsuarios;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EspaciosTrabajoUsuarioRepository extends JpaRepository<TblEspaciosTrabajoUsuarios, Integer> {

    /**
     * Metodo que despliega el Espacio de Trabajo asignado al Usuario de la BD
     *
     * @param tblUsuarios
     * @return Espacios de Trabajo de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 11/10/2018/v1.0
     */
    @Query("SELECT e FROM TblEspaciosTrabajoUsuarios e WHERE e.idUsuarioEspacioTrabajo = :idUsuarioEspacioTrabajo AND e.activo = true ")
    List<TblEspaciosTrabajoUsuarios> findByIdUsuarioEspacioTrabajo(@Param("idUsuarioEspacioTrabajo")  TblUsuarios tblUsuarios);



    /**
     * Metodo que despliega que Busca los
     *
     * @param tblUsuarios
     * @return Organizacion de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    // @Query("SELECT e FROM TblOrganizacion e WHERE e.idTipoOrganizacionT = :idTipoOrganizacion ")
    long countByIdUsuarioEspacioTrabajo(TblUsuarios tblUsuarios);


    /**
     * Metodo que despliega el Espacio de Trabajo asignado al Usuario de la BD
     *
     * @param tblUsuarios
     * @param tblEspaciosTrabajo
     * @return Espacios de Trabajo de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 01/03/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblEspaciosTrabajoUsuarios e WHERE e.idUsuarioEspacioTrabajo = :idUsuarioEspacioTrabajo AND e.idEspacioTrabajo = :idEspacioTrabajo ")
    long countIdUsuarioEspacioTrabajoAndIdEspacioTrabajo(@Param("idUsuarioEspacioTrabajo")  TblUsuarios tblUsuarios,
                                                         @Param("idEspacioTrabajo")TblEspaciosTrabajo tblEspaciosTrabajo);

}
