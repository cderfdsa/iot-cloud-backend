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
@Table(name = "device_type_attributes_modbus")
public class EntityDeviceTypeAttributeModbus implements Serializable {
    private Long id;
    @Column(name = "rel_device_type_id")
    private Long relDeviceTypeId;
    @Column(name = "rel_device_type_attributes_id")
    private Long relDeviceTypeAttributesId;
    @Column(name = "slave_address")
    private int slaveAddress;
    @Column(name = "register_address")
    private int registerAddress;
    @Column(name = "read_write_type")
    private int readWriteType;
    @Column(name = "data_type")
    private int dataType;

}
