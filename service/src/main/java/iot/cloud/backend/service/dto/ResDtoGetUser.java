package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang 2023/5/13 19:41
 */
@Data
public class ResDtoGetUser extends BaseResDto {
    private Long user_id;
    private String email;
}