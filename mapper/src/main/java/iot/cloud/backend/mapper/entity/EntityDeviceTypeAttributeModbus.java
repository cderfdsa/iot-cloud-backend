package iot.cloud.backend.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author weichuang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityDeviceTypeAttributeModbus implements Serializable {
    private Long id;
    private Long relDeviceTypeId;
    private Long relDeviceTypeAttributesId;
    private int slaveAddress;
    private int registerAddress;
    private int readWriteType;
    private int dataType;

}
