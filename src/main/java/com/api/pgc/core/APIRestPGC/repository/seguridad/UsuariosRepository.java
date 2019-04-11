package com.api.pgc.core.APIRestPGC.repository.seguridad;

import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<TblUsuarios, Integer> {
    /**
     * Metodo que despliega el Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return usuario de la BD, por paramtro de ID
     * @param idUsuario
     */
    TblUsuarios findByIdUsuario( long idUsuario );

    TblUsuarios findByCodUsuario( String codUsuario );

    TblUsuarios findByEmailUsuario( String emailUsuario );

}
