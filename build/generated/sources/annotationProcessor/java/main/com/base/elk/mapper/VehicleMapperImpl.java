package com.base.elk.mapper;

import com.base.elk.dto.VehicleDto;
import com.base.elk.model.VehicleEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-14T11:40:43+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
)
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public VehicleEntity vehicleDtoToVehicleEntity(VehicleDto vehicleDto) {
        if ( vehicleDto == null ) {
            return null;
        }

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setVehicleModel( vehicleDto.getVehicleModel() );
        vehicleEntity.setVin( vehicleDto.getVin() );
        vehicleEntity.setVehiclePrice( vehicleDto.getVehiclePrice() );

        return vehicleEntity;
    }

    @Override
    public VehicleDto vehicleEntityToVehicleDto(VehicleEntity vehicleEntity) {
        if ( vehicleEntity == null ) {
            return null;
        }

        VehicleDto vehicleDto = new VehicleDto();

        vehicleDto.setVehicleModel( vehicleEntity.getVehicleModel() );
        vehicleDto.setVin( vehicleEntity.getVin() );
        vehicleDto.setVehiclePrice( vehicleEntity.getVehiclePrice() );

        return vehicleDto;
    }
}
