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
@Table(name = "device_type_attributes")
public class EntityDeviceTypeAttribute implements Serializable {
    private Long id;
    @Column(name = "rel_device_type_id")
    private Long relDeviceTypeId;
    private String name;
    private String code;
    private int type;
    @Column(name = "data_type")
    private int dataType;
    @Column(name = "protocol_format")
    private int protocolFormat;

}
