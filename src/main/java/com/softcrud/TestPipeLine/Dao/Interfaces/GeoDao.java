package com.softcrud.TestPipeLine.Dao.Interfaces;

import com.softcrud.TestPipeLine.Dao.Entity.GeoShapeEntidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interfaz para el acceso a datos
 */
public interface GeoDao extends CrudRepository<GeoShapeEntidad, Long> {

    /**
     * Metodo para obtencion de coordenadas mediante el recordId
     * @param recordId
     * @return
     */
    @Query(
            value = "SELECT * FROM `t_geoshape` where recordid=?1",
            nativeQuery = true)
    List<GeoShapeEntidad> getGeoShapeByRecordId(String recordId);
}
