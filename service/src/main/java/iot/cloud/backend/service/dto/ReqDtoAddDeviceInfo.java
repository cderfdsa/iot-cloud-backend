package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoAddDeviceInfo {
    @NotNull
    private Long relDeviceTypeId;
    @NotNull
    private Long relUserInfoId;
    @NotEmpty
    private String name;
}
