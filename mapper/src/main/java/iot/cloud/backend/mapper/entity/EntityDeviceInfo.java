package iot.cloud.backend.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author weichuang 2023/5/17 23:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device_info")
public class EntityDeviceInfo implements Serializable {
    private Long id;
    @Column(name = "rel_device_type_id")
    private Long relDeviceTypeId;
    @Column(name = "rel_user_info_id")
    private Long relUserInfoId;
    private String name;
    private String code;
    private String pwd;

}
