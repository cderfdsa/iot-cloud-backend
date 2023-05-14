package iot.cloud.backend.service.dto;

import lombok.Data;

/**
 * @author weichuang 2023/5/13 19:40
 */
@Data
public class ReqDtoLogin {
    private String email;
    private String validateCode;
}
