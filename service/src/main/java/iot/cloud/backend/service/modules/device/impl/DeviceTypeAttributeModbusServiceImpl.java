package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.mapper.modules.device.MapperDeviceTypeAttribute;
import iot.cloud.backend.mapper.modules.device.MapperDeviceTypeAttributeModbus;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeModbusService;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang 2023/5/18 16:53
 */
@Service
public class DeviceTypeAttributeModbusServiceImpl implements DeviceTypeAttributeModbusService {
    @Resource
    private MapperDeviceTypeAttributeModbus mapperDeviceTypeAttributeModbus;
}
