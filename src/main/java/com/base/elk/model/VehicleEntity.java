package com.base.elk.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * @author Mohamed JARRAY
 * Vehicle JPA entity.
 */
@Entity
@Table(name = "vehicle")
@Data
@ToString(of = { "vehicleId", "vin", "vehicleModel" })
public class VehicleEntity {

    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Unique identifier of the vehicle. No two vehicle can have the same id.", example = "1", required = true, position = 0)
    private Long vehicleId;

    @ApiModelProperty(notes = "Model of the vehicle.", example = "TESLA", required = true, position = 1)
    @Column(nullable = false, name = "vehicle_model")
    private String vehicleModel;

    @ApiModelProperty(notes = "Vin of the vehicle. No two vehicle can have the same Vin.", example = "TESLA", required = true, position = 2)
    @Column(nullable = false, name = "vin")
    private String vin;

    @ApiModelProperty(notes = "Price of the vehicle.", example = "10 000", required = true, position = 3)
    @Column(nullable = false, name = "vehicle_price")
    private Integer vehiclePrice;
}
