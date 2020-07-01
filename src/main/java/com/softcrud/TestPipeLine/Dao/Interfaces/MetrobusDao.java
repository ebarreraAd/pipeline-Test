package com.softcrud.TestPipeLine.Dao.Interfaces;

import com.softcrud.TestPipeLine.Dao.Entity.MetrobusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interfaz para el acceso a datos
 */
public interface MetrobusDao extends CrudRepository<MetrobusEntity, Long> {

    /**
     * Metodo para obtener las unidades activas mediante la fecha
     * @param searchtext
     * @return
     */
    @Query(
            value = "SELECT * FROM t_metrobus WHERE date_add LIKE %:searchtext%",
            nativeQuery = true)
    List<MetrobusEntity> getMetrobusesActives(@Param("searchtext") String searchtext);

    /**
     * Metodo para buscar las unidades mediante su id
     * @param idVehicle
     * @return
     */
    List<MetrobusEntity> findByVehicleId(String idVehicle);

    /**
     * Metodo para buscar las unidades por alcaldia
     * @param alcaldia
     * @return
     */
    @Query(
            value = "SELECT * FROM t_metrobus WHERE alcaldia=?1",
            nativeQuery = true)
    List<MetrobusEntity> getByAlcaldia(String alcaldia);
}
