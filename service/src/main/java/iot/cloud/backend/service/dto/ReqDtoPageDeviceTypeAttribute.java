package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoPageDeviceTypeAttribute extends ReqDtoPage {
    @NotBlank
    private Long relDeviceTypeId;
    private String searchKey;
}
