package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.mapper.modules.device.MapperDeviceInfo;
import iot.cloud.backend.mapper.modules.device.MapperDeviceType;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.modules.device.DeviceTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang 2023/5/18 16:53
 */
@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {
    @Resource
    private MapperDeviceInfo mapperDeviceInfo;
}
