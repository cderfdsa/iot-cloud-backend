package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoAddHistoryDeviceOnline {
    @NotNull
    private Long relUserInfoId;
    @NotNull
    private Long relDeviceInfoId;
    @NotNull
    private String deviceName;
    @NotNull
    private String deviceCode;
    @NotNull
    private Integer status;
    @NotNull
    private Integer statusReason;
}
