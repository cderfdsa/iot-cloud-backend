package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ResDtoLoginOrRegister extends BaseResDto {
    private Long user_id;
    private String email;
    private String account;
    private String token;
}
