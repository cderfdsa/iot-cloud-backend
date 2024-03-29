package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ResDtoGetDeviceTypeAttributeModbus extends BaseResDto {
    private int slaveAddress;
    private int registerAddress;
    private String code;
}
