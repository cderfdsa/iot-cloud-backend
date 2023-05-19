package iot.cloud.backend.service.dto;

import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoEditDeviceTypeAttribute {
    private String name;
    private int type;
    private int communicationType;
    private int protocolType;
    private int protocolFormat;
}
