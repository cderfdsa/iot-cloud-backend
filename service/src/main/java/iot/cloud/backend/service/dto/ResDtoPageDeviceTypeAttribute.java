package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ResDtoPageDeviceTypeAttribute extends BaseResDto {
    private Long id;
    private Long relDeviceTypeId;
    private String name;
    private String code;
    private int type;
    private int dataType;
}
