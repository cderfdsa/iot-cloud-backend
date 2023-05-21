package iot.cloud.backend.service.dto;

import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoAddDeviceTypeAttribute {
    private Long relDeviceTypeId;
    private String name;
    private String code;
    private int type;
    private int dataType;
}
