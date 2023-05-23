package iot.cloud.backend.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author weichuang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityDeviceInfo implements Serializable {
    private Long id;
    private Long relDeviceTypeId;
    private Long relUserInfoId;
    private String name;
    private String code;
    private String pwd;
    private int onlineStatus;
    private int alarmStatus;

}
