package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang
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
