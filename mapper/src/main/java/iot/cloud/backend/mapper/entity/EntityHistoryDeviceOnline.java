package iot.cloud.backend.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weichuang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityHistoryDeviceOnline implements Serializable {
    private Long id;
    private Long relUserInfoId;
    private Long relDeviceInfoId;
    private String deviceName;
    private String deviceCode;
    private Date createDt;
    private Integer status;
    private Integer statusReason;
}
