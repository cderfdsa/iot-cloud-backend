package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttribute;
import iot.cloud.backend.mapper.modules.device.MapperDeviceTypeAttribute;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Service
public class DeviceTypeAttributeServiceImpl implements DeviceTypeAttributeService {
    @Resource
    private MapperDeviceTypeAttribute mapperDeviceTypeAttribute;

    @Override
    public ResResult<ResDtoAdd> add(ReqDtoAddDeviceTypeAttribute reqDtoAddDeviceTypeAttribute) {
        //
        ResResult<ResDtoAdd> resResult = new ResResult<>();
        ResDtoAdd resDtoAdd = new ResDtoAdd();
        resResult.setData(resDtoAdd);
        //
        EntityDeviceTypeAttribute entityDeviceTypeAttribute = new EntityDeviceTypeAttribute();
        entityDeviceTypeAttribute.setRelDeviceTypeId(reqDtoAddDeviceTypeAttribute.getRelDeviceTypeId());
        entityDeviceTypeAttribute.setName(reqDtoAddDeviceTypeAttribute.getName());
        entityDeviceTypeAttribute.setCode(reqDtoAddDeviceTypeAttribute.getCode());
        entityDeviceTypeAttribute.setType(reqDtoAddDeviceTypeAttribute.getType());
        entityDeviceTypeAttribute.setDataType(reqDtoAddDeviceTypeAttribute.getDataType());
        //
        mapperDeviceTypeAttribute.insert(entityDeviceTypeAttribute);
        resDtoAdd.setId(entityDeviceTypeAttribute.getId());
        //
        return resResult;
    }

    @Override
    public ResResult<ResDtoEdit> edit(ReqDtoEditDeviceTypeAttribute reqDtoEditDeviceTypeAttribute) {
        return null;
    }

    @Override
    public ResResult<ResDtoRemove> remove(ReqDtoRemove reqDtoRemove) {
        return null;
    }

    @Override
    public ResResult<ResDtoGetDeviceTypeAttribute> get(ReqDtoGetDeviceTypeAttribute reqDtoGetDeviceTypeAttribute) {
        return null;
    }
}
