package iot.cloud.backend.service.dto;

import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoLoginOrRegister {
    private String email;
    private String validateCode;
}
