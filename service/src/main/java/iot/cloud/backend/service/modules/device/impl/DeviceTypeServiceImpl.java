package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.mapper.entity.EntityDeviceType;
import iot.cloud.backend.mapper.modules.device.MapperDeviceType;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceTypeService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang 2023/5/18 16:53
 */
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {
    @Resource
    private MapperDeviceType mapperDeviceType;

    @Override
    public ResResult<ResDtoAdd> add(ReqDtoAddDeviceType reqDtoAddDeviceType) {
        //
        ResResult<ResDtoAdd> resResult = new ResResult<>();
        //
        EntityDeviceType entityDeviceType = new EntityDeviceType();
        entityDeviceType.setName(reqDtoAddDeviceType.getName());
        entityDeviceType.setType(reqDtoAddDeviceType.getType());
        entityDeviceType.setCommunicationType(reqDtoAddDeviceType.getCommunicationType());
        entityDeviceType.setProtocolType(reqDtoAddDeviceType.getProtocolType());
        entityDeviceType.setProtocolFormat(reqDtoAddDeviceType.getProtocolFormat());
        //
        int id = mapperDeviceType.insert(entityDeviceType);
        //
        return resResult;
    }

    @Override
    public ResResult<ResDtoEdit> edit(ReqDtoEditDeviceType reqDtoEditDeviceType) {
        return null;
    }

    @Override
    public ResResult<ResDtoRemove> remove(ReqDtoRemove reqDtoRemove) {
        return null;
    }

    @Override
    public ResResult<ResDtoGetDeviceType> get(ReqDtoGetDeviceType reqDtoGetDeviceType) {
        return null;
    }
}
