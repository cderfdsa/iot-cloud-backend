package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoValidateCode {
    @Email
    private String email;
}
