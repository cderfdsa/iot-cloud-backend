package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang 2023/5/13 19:41
 */
@Data
public class ResDtoGetDeviceType extends BaseResDto {
    private Long id;
    private String name;
    private int type;
    private int communicationType;
    private int protocolType;
    private int protocolFormat;
}
