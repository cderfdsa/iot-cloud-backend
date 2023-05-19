package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.mapper.modules.device.MapperDeviceInfo;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {
    @Resource
    private MapperDeviceInfo mapperDeviceInfo;
}
