package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ResDtoPageDeviceType extends BaseResDto {
    private Long id;
    private Long relUserInfoId;
    private String name;
    private int type;
    private int communicationType;
    private int protocolType;
    private int protocolFormat;
    private int busTimeValue;
    private String busTimeUnit;
}
