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
public class EntityDeviceType implements Serializable {
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
