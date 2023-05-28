package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ResDtoGetDeviceInfo extends BaseResDto {
    private Long id;
    private Long relDeviceTypeId;
    private Long relUserInfoId;
    private String name;
    private String code;
}
