package iot.cloud.backend.common.utils;

import cn.hutool.core.io.checksum.CRC16;
import cn.hutool.core.io.checksum.crc16.CRC16Modbus;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.*;
import com.serotonin.modbus4j.sero.util.queue.ByteQueue;

import java.nio.ByteOrder;

/**
 * @author weichuang
 */
public class Modbus4jUtils {

    private static byte[] retBytesFromRequest(ModbusRequest request) {
        ByteQueue byteQueue = new ByteQueue();
        request.write(byteQueue);
        byte[] dataNoCRC = byteQueue.peekAll();
        CRC16 crc16 = new CRC16(new CRC16Modbus());
        crc16.update(dataNoCRC);
        long value = crc16.getValue();
        byte[] crcValue = ByteUtils.longToBytes(value, ByteOrder.LITTLE_ENDIAN);
        return (byte[]) ArrayUtils.append(dataNoCRC, crcValue[0], crcValue[1]);
    }

    /**
     * @Title readCoilStatus
     * 读（线圈）开关量数据，相当于功能码：01H-读线圈状态
     */
    public static byte[] readCoilStatus(int slaveId, int offset, int numberOfRegister) throws ModbusTransportException {
        ReadCoilsRequest request = new ReadCoilsRequest(slaveId, offset, numberOfRegister);
        return retBytesFromRequest(request);
    }

    /**
     * @Title readInputStatus
     * 读取外围设备输入的开关量，相当于功能码：02H-读离散输入状态
     */
    public static byte[] readInputStatus(int slaveId, int offset, int numberOfRegister) throws ModbusTransportException {
        ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, offset, numberOfRegister);
        return retBytesFromRequest(request);
    }

    /**
     * 读取保持寄存器数据，相当于功能码：03H-读保持寄存器
     */
    public static byte[] readHoldingRegister(int slaveId, int offset, int numberOfRegister) throws ModbusTransportException {
        ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, offset, numberOfRegister);
        return retBytesFromRequest(request);
    }


    /**
     * @Title readInputRegisters
     * 读取外围设备输入的数据，相当于功能码：04H-读输入寄存器
     */
    public static byte[] readInputRegisters(int slaveId, int offset, int numberOfRegister) throws ModbusTransportException {
        ReadInputRegistersRequest request = new ReadInputRegistersRequest(slaveId, offset, numberOfRegister);
        return retBytesFromRequest(request);
    }

    /**
     * @Title writeCoil
     * 写单个（线圈）开关量数据，相当于功能码：05H-写单个线圈
     */
    public static byte[] writeCoil(int slaveId, int writeOffset, boolean writeValue) throws ModbusTransportException {
        WriteCoilRequest request = new WriteCoilRequest(slaveId, writeOffset, writeValue);
        return retBytesFromRequest(request);
    }

    /**
     * @Title writeCoils
     * 写多个开关量数据（线圈），相当于功能码：0FH-写多个线圈
     */
    public static byte[] writeCoils(int slaveId, int startOffset, boolean[] data) throws ModbusTransportException {
        WriteCoilsRequest request = new WriteCoilsRequest(slaveId, startOffset, data);
        return retBytesFromRequest(request);
    }

    /**
     * @Title writeHoldingRegister
     * 写单个保持寄存器，相当于功能码：06H-写单个保持寄存器
     */
    public static byte[] writeHoldingRegister(int slaveId, int writeOffset, short writeValue) throws ModbusTransportException {
        WriteRegisterRequest request = new WriteRegisterRequest(slaveId, writeOffset, writeValue);
        return retBytesFromRequest(request);
    }

    /**
     * @Title writeHoldingRegisters
     * 写多个保持寄存器，相当于功能码：10H-写多个保持寄存器
     */
    public static byte[] writeHoldingRegisters(int slaveId, int startOffset, short[] data) throws ModbusTransportException {
        WriteRegistersRequest request = new WriteRegistersRequest(slaveId, startOffset, data);
        return retBytesFromRequest(request);
    }
}

