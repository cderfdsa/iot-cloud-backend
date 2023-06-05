package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ResDtoPageDeviceTypeAttributeModbus extends BaseResDto {
    private Long id;
    private Long relDeviceTypeId;
    private Long relDeviceTypeAttributeId;
    private String relDeviceTypeAttributeName;
    private int slaveAddress;
    private int registerAddress;
    private int readWriteType;
    private int dataType;
}
