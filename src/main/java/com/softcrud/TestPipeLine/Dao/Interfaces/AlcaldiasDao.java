package com.softcrud.TestPipeLine.Dao.Interfaces;

import com.softcrud.TestPipeLine.Dao.Entity.AlcaldiasEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz para el acceso a datos
 */
public interface AlcaldiasDao extends CrudRepository<AlcaldiasEntity, Long> {
}
