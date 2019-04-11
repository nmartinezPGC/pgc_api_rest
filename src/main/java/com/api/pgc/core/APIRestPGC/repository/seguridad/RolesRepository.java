package com.api.pgc.core.APIRestPGC.repository.seguridad;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository  extends JpaRepository<TblRoles, Integer> {
    /**
     * Metodo que despliega el Rol de la BD
     * @autor Nahum Martinez | NAM
     * @version  01/03/2019/v1.0
     * @return Rol de la BD, por paramtro de ID
     * @param idRol
     */
    TblRoles findByIdRol(long idRol);

    /**
     * Metodo que despliega los Rol de la BD
     * @autor Nahum Martinez | NAM
     * @version  07/03/2019/v1.0
     * @return Rol de la BD, por paramtro de ID Grupo
     * @param tblGrupo
     */
    List<TblRoles> findByIdGrupo(TblGrupo tblGrupo);


    /**
     * Metodo que despliega los Rol de la BD
     * @autor Nahum Martinez | NAM
     * @version  07/03/2019/v1.0
     * @return Rol de la BD, por paramtro de ID Grupo
     * @param tblGrupo
     */
    long countByIdGrupo(TblGrupo tblGrupo);
}
