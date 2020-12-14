package com.base.elk.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.base.elk.exception.VehicleAlreadyExistException;
import com.base.elk.exception.VehicleNotFoundException;
import com.base.elk.model.VehicleEntity;
import com.base.elk.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Mohamed JARRAY
 * Vehicle Srvice
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private static final String VEHICLE_ID_NOT_NULL = "vehicleId cannot be null.";
    private static final String VEHICLE_NOT_FOUND_BY_ID_ERROR_MSG = "Can't find an vehicle for id : %s";
    private static final String VEHICLE_ALREADY_EXIST_BY_VIN_ERROR_MSG = "the given vehicle with vin %s is already exists";

    final private VehicleRepository vehicleRepository;

    /**
     * Find all vehicle.
     * @return
     */
    public List<VehicleEntity> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    /**
     * Find vehicle by Id.
     * @param id
     * @return
     * @throws VehicleNotFoundException
     */
    public VehicleEntity findById(Long id) throws VehicleNotFoundException {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(String.format(VEHICLE_NOT_FOUND_BY_ID_ERROR_MSG, id)));
    }

    /**
     * Create new vehicle.
     * @param vehicle
     * @return
     * @throws VehicleAlreadyExistException
     */
    @Transactional
    public VehicleEntity create(VehicleEntity vehicle) throws VehicleAlreadyExistException {
         boolean vehicleExists = vehicleRepository.findAll()
                 .stream()
                 .map(VehicleEntity::getVin)
                 .anyMatch(vin -> vin.equals(vehicle.getVin()));

         if (vehicleExists) {
             throw new VehicleAlreadyExistException(String.format(VEHICLE_ALREADY_EXIST_BY_VIN_ERROR_MSG, vehicle.getVin()));
         }

         return vehicleRepository.save(vehicle);
    }

    /**
     * delete vehicle.
     * @param vehicleId
     * @return
     * @throws VehicleNotFoundException
     */
    @Transactional
    public void delete(Long vehicleId) throws VehicleNotFoundException {
        Objects.requireNonNull(vehicleId, VEHICLE_ID_NOT_NULL);
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(String.format(VEHICLE_NOT_FOUND_BY_ID_ERROR_MSG, vehicleId)));


        vehicleRepository.delete(vehicle);
    }
}
