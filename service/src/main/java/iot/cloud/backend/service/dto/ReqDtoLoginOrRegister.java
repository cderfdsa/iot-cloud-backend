package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoLoginOrRegister {
    @NotNull
    private String email;
    @NotNull
    private String validateCode;

    private boolean day30 = false;
}
