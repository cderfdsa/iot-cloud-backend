package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.mapper.modules.device.MapperDeviceTypeAttributeModbus;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeModbusService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang 2023/5/18 16:53
 */
@Service
public class DeviceTypeAttributeModbusServiceImpl implements DeviceTypeAttributeModbusService {
    @Resource
    private MapperDeviceTypeAttributeModbus mapperDeviceTypeAttributeModbus;

    @Override
    public ResResult<ResDtoAdd> add(ReqDtoAddDeviceTypeAttributeModbus reqDtoAddDeviceTypeAttributeModbus) {
        return null;
    }

    @Override
    public ResResult<ResDtoEdit> edit(ReqDtoEditDeviceTypeAttributeModbus reqDtoEditDeviceTypeAttributeModbus) {
        return null;
    }

    @Override
    public ResResult<ResDtoRemove> remove(ReqDtoRemove reqDtoRemove) {
        return null;
    }

    @Override
    public ResResult<ResDtoGetDeviceTypeAttributeModbus> get(ReqDtoGetDeviceTypeAttributeModbus reqDtoGetDeviceTypeAttributeModbus) {
        return null;
    }
}
