package com.api.pgc.core.APIRestPGC.repository.organizaciones;

import com.api.pgc.core.APIRestPGC.models.organizaciones.TblGrupoOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoOrganizacionRepository extends JpaRepository<TblGrupoOrganizacion, Integer> {

    /**
     * Metodo que despliega el Grupo de Organizacion de la BD
     *
     * @param idGrupoOrganizacion
     * @return Grupo de Organizacion de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    TblGrupoOrganizacion findByIdGrupoOrganizacion( long idGrupoOrganizacion );

}
