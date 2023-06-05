package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.common.base.PageInfo;
import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttribute;
import iot.cloud.backend.mapper.modules.device.MapperDeviceTypeAttribute;
import iot.cloud.backend.mapper.vo.VoIdName;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ResResult<PageInfo<ResDtoPageDeviceTypeAttribute>> page(ReqDtoPageDeviceTypeAttribute reqDtoPageDeviceTypeAttribute) {
        //
        ResResult<PageInfo<ResDtoPageDeviceTypeAttribute>> resResult = new ResResult<>();
        PageInfo<ResDtoPageDeviceTypeAttribute> pageInfo = new PageInfo<>();
        pageInfo.setOffset(reqDtoPageDeviceTypeAttribute.getOffset());
        pageInfo.setRows(reqDtoPageDeviceTypeAttribute.getRows());
        resResult.setData(pageInfo);
        //
        List<ResDtoPageDeviceTypeAttribute> list = new ArrayList<>();
        List<EntityDeviceTypeAttribute> listForEntity = mapperDeviceTypeAttribute.limit(reqDtoPageDeviceTypeAttribute.getRelDeviceTypeId(), reqDtoPageDeviceTypeAttribute.getSearchKey(), reqDtoPageDeviceTypeAttribute.getOffset(), reqDtoPageDeviceTypeAttribute.getRows());
        pageInfo.setTotal(mapperDeviceTypeAttribute.limitTotal(reqDtoPageDeviceTypeAttribute.getRelDeviceTypeId(), reqDtoPageDeviceTypeAttribute.getSearchKey()));
        listForEntity.stream().forEach((entityDeviceTypeAttribute) -> {
            ResDtoPageDeviceTypeAttribute resDtoPageDeviceTypeAttribute = new ResDtoPageDeviceTypeAttribute();
            resDtoPageDeviceTypeAttribute.setId(entityDeviceTypeAttribute.getId());
            resDtoPageDeviceTypeAttribute.setRelDeviceTypeId(entityDeviceTypeAttribute.getRelDeviceTypeId());
            resDtoPageDeviceTypeAttribute.setName(entityDeviceTypeAttribute.getName());
            resDtoPageDeviceTypeAttribute.setCode(entityDeviceTypeAttribute.getCode());
            resDtoPageDeviceTypeAttribute.setType(entityDeviceTypeAttribute.getType());
            resDtoPageDeviceTypeAttribute.setDataType(entityDeviceTypeAttribute.getDataType());
            list.add(resDtoPageDeviceTypeAttribute);
        });
        pageInfo.setList(list);
        //
        return resResult;
    }

    @Override
    public Map<Long, String> getIdNamesByIds(List<Long> ids) {
        List<VoIdName> voIdNameList = mapperDeviceTypeAttribute.selectIdNamesByIds(ids);
        Map<Long, String> retMap = new HashMap<>();
        for (VoIdName voIdName : voIdNameList) {
            retMap.put(voIdName.getId(), voIdName.getName());
        }
        return retMap;
    }
}
