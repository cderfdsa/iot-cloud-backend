package iot.cloud.backend.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author weichuang 2023/5/17 23:04
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

}
