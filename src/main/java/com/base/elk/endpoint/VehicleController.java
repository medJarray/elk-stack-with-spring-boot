package com.base.elk.endpoint;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.base.elk.dto.VehicleDto;
import com.base.elk.exception.VehicleAlreadyExistException;
import com.base.elk.exception.VehicleNotFoundException;
import com.base.elk.mapper.VehicleMapper;
import com.base.elk.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.util.UriComponentsBuilder.*;

@RestController
@RequestMapping(VehicleController.PATH)
@Api("Set of endpoints for Creating, Retrieving, Updating and Deleting of Vehicle.")
@RequiredArgsConstructor
@Validated
@Slf4j
public class VehicleController {

    static final String PATH = "/api/v1/vehicles";

    private final VehicleService vehicleService;

    @GetMapping
    @ApiOperation("Returns list of all vehicle in the database.")
    public List<VehicleDto> findAll() {
        return
                vehicleService.findAllVehicles()
                .stream()
                .map(VehicleMapper.VEHICLE_ENTITY_TO_VEHICLE_DTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{vehicleId}")
    @ApiOperation("Returns one vehicle by Id from database.")
    public VehicleDto findById(@PathVariable("vehicleId") Long vehicleId) throws VehicleNotFoundException {
        try {
            return VehicleMapper.VEHICLE_ENTITY_TO_VEHICLE_DTO.apply(vehicleService.findById(vehicleId));
        } catch (VehicleNotFoundException e) {
           log.warn("Vehicle [{}] not found ", vehicleId, e);
           throw new VehicleNotFoundException(e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation("Creates a new vehicle in the database.")
    public ResponseEntity<Void> createVehicle(@RequestBody @Valid VehicleDto vehicleDto) throws VehicleAlreadyExistException {
        Long vehicleId;
        try {
            vehicleId = vehicleService.create(VehicleMapper.VEHICLE_DTO_TO_VEHICLE_ENTITY.apply(vehicleDto)).getVehicleId();
        } catch (VehicleAlreadyExistException e) {
            log.error("Vehicle already exists.", e);
            throw new VehicleAlreadyExistException(e.getMessage());
        }
        return ResponseEntity.created(fromUriString(PATH).pathSegment(vehicleId.toString()).build().toUri()).build();
    }

    @DeleteMapping("/{vehicleId}")
    @ApiOperation("Delete a vehicle froù the database.")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleId") final Long vehicleId) throws VehicleNotFoundException {
        try {
            vehicleService.delete(vehicleId);
        } catch (VehicleNotFoundException e) {
            final String errorMessage = String.format("Cannot find the vehicle to deleted [%s]", vehicleId);
            log.debug(errorMessage, e);
            throw new VehicleNotFoundException(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

}
