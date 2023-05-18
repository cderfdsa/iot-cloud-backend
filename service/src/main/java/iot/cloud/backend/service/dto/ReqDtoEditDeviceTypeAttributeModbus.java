package iot.cloud.backend.service.dto;

import lombok.Data;

/**
 * @author weichuang 2023/5/17 23:20
 */
@Data
public class ReqDtoEditDeviceTypeAttributeModbus {
    private String name;
    private int type;
    private int communicationType;
    private int protocolType;
    private int protocolFormat;
}
