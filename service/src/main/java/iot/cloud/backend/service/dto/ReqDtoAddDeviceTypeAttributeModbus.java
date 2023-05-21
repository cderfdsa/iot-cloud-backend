package iot.cloud.backend.service.dto;

import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoAddDeviceTypeAttributeModbus {
    private Long relDeviceTypeId;
    private Long relDeviceTypeAttributeId;
    private int slaveAddress;
    private int registerAddress;
    private int readWriteType;
    private int dataType;
}
