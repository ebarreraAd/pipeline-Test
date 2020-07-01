package com.softcrud.TestPipeLine.Dao.Interfaces;

import com.softcrud.TestPipeLine.Dao.Entity.GeoShapeEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz para el acceso a datos
 */
public interface GeoShapeDao extends CrudRepository<GeoShapeEntity, Long> {

}
