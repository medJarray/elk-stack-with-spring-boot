package com.base.elk.mapper;

import java.util.function.Function;

import com.base.elk.dto.VehicleDto;
import com.base.elk.model.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Function<VehicleDto, VehicleEntity> VEHICLE_DTO_TO_VEHICLE_ENTITY = INSTANCE::vehicleDtoToVehicleEntity;
    Function<VehicleEntity, VehicleDto> VEHICLE_ENTITY_TO_VEHICLE_DTO = INSTANCE::vehicleEntityToVehicleDto;

    @Mapping(target = "vehicleId", ignore = true)
    VehicleEntity vehicleDtoToVehicleEntity(VehicleDto vehicleDto);

    VehicleDto vehicleEntityToVehicleDto(VehicleEntity vehicleEntity);
}
