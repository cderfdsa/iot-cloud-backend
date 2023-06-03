package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ResDtoPageDeviceInfo extends BaseResDto {
    private Long id;
    private Long relDeviceTypeId;
    private String relDeviceTypeName;
    private Long relUserInfoId;
    private String relUserInfoName;
    private String name;
    private String code;
    private int onlineStatus;
    private int activeStatus;
    private int alarmStatus;

}
