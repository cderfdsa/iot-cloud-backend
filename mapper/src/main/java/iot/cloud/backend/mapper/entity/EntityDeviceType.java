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
@Table(name = "device_type")
public class EntityDeviceType implements Serializable {
    private Long id;
    private String name;
    private int type;
    @Column(name = "communication_type")
    private int communicationType;
    @Column(name = "protocol_type")
    private int protocolType;
    @Column(name = "protocol_format")
    private int protocolFormat;

}
