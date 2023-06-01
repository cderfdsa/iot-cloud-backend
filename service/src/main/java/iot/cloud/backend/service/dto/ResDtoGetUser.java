package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ResDtoGetUser extends BaseResDto {
    private Long userId;
    private String account;
    private String email;
    private String secret;
}
