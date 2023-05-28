package iot.cloud.backend.mapper.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weichuang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoModbusAttribute {
    private int slaveAddress;
    private int registerAddress;
    private int readWriteType;
    private int dataType;
    private String code;
}
