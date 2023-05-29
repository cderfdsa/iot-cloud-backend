package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoAddHistoryDeviceAttribute {
    @NotNull
    private Long relUserInfoId;
    @NotNull
    private Long relDeviceInfoId;
    @NotNull
    private String deviceName;
    @NotNull
    private String deviceTypeName;
    @NotNull
    private String deviceTypeAttributeName;
    @NotNull
    private String deviceTypeAttributeCode;
    @NotNull
    private String deviceCode;
    @NotNull
    private Long value;
}
