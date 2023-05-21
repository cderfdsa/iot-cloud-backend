package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttributeModbus;
import iot.cloud.backend.mapper.modules.device.MapperDeviceTypeAttributeModbus;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeModbusService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Service
public class DeviceTypeAttributeModbusServiceImpl implements DeviceTypeAttributeModbusService {
    @Resource
    private MapperDeviceTypeAttributeModbus mapperDeviceTypeAttributeModbus;

    @Override
    public ResResult<ResDtoAdd> add(ReqDtoAddDeviceTypeAttributeModbus reqDtoAddDeviceTypeAttributeModbus) {
        //
        ResResult<ResDtoAdd> resResult = new ResResult<>();
        ResDtoAdd resDtoAdd = new ResDtoAdd();
        resResult.setData(resDtoAdd);
        //
        EntityDeviceTypeAttributeModbus entityDeviceTypeAttributeModbus = new EntityDeviceTypeAttributeModbus();
        entityDeviceTypeAttributeModbus.setRelDeviceTypeId(reqDtoAddDeviceTypeAttributeModbus.getRelDeviceTypeId());
        entityDeviceTypeAttributeModbus.setRelDeviceTypeAttributeId(reqDtoAddDeviceTypeAttributeModbus.getRelDeviceTypeAttributeId());
        entityDeviceTypeAttributeModbus.setSlaveAddress(reqDtoAddDeviceTypeAttributeModbus.getSlaveAddress());
        entityDeviceTypeAttributeModbus.setRegisterAddress(reqDtoAddDeviceTypeAttributeModbus.getRegisterAddress());
        entityDeviceTypeAttributeModbus.setReadWriteType(reqDtoAddDeviceTypeAttributeModbus.getReadWriteType());
        entityDeviceTypeAttributeModbus.setDataType(reqDtoAddDeviceTypeAttributeModbus.getDataType());
        //
        mapperDeviceTypeAttributeModbus.insert(entityDeviceTypeAttributeModbus);
        resDtoAdd.setId(entityDeviceTypeAttributeModbus.getId());
        //
        return resResult;
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
