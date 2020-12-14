package com.base.elk.repository;

import com.base.elk.model.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mohamed Jarray
 * Spring data repository for {@link VehicleEntity}.
 */
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
}
