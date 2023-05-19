package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.common.base.PageInfo;
import iot.cloud.backend.mapper.entity.EntityDeviceType;
import iot.cloud.backend.mapper.modules.device.MapperDeviceType;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceTypeService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        ResDtoAdd resDtoAdd = new ResDtoAdd();
        resResult.setData(resDtoAdd);
        //
        EntityDeviceType entityDeviceType = new EntityDeviceType();
        entityDeviceType.setName(reqDtoAddDeviceType.getName());
        entityDeviceType.setType(reqDtoAddDeviceType.getType());
        entityDeviceType.setCommunicationType(reqDtoAddDeviceType.getCommunicationType());
        entityDeviceType.setProtocolType(reqDtoAddDeviceType.getProtocolType());
        entityDeviceType.setProtocolFormat(reqDtoAddDeviceType.getProtocolFormat());
        //
        mapperDeviceType.insert(entityDeviceType);
        resDtoAdd.setId(entityDeviceType.getId());
        //
        return resResult;
    }

    @Override
    public ResResult<ResDtoEdit> edit(ReqDtoEditDeviceType reqDtoEditDeviceType) {
        //
        ResResult<ResDtoEdit> resResult = new ResResult<>();
        ResDtoEdit resDtoEdit = new ResDtoEdit();
        resResult.setData(resDtoEdit);
        //
        EntityDeviceType entityDeviceType = new EntityDeviceType();
        entityDeviceType.setId(reqDtoEditDeviceType.getId());
        entityDeviceType.setName(reqDtoEditDeviceType.getName());
        entityDeviceType.setType(reqDtoEditDeviceType.getType());
        entityDeviceType.setCommunicationType(reqDtoEditDeviceType.getCommunicationType());
        entityDeviceType.setProtocolType(reqDtoEditDeviceType.getProtocolType());
        entityDeviceType.setProtocolFormat(reqDtoEditDeviceType.getProtocolFormat());
        //
        mapperDeviceType.insert(entityDeviceType);
        resDtoEdit.setId(entityDeviceType.getId());
        //
        return resResult;
    }

    @Override
    public ResResult<ResDtoRemove> remove(ReqDtoRemove reqDtoRemove) {
        //
        ResResult<ResDtoRemove> resResult = new ResResult<>();
        ResDtoRemove resDtoRemove = new ResDtoRemove();
        resResult.setData(resDtoRemove);
        //
        mapperDeviceType.delete(reqDtoRemove.getId());
        resDtoRemove.setId(reqDtoRemove.getId());
        //
        return resResult;
    }

    @Override
    public ResResult<ResDtoGetDeviceType> get(ReqDtoGetDeviceType reqDtoGetDeviceType) {
        //
        ResResult<ResDtoGetDeviceType> resResult = new ResResult<>();
        ResDtoGetDeviceType resDtoGetDeviceType = new ResDtoGetDeviceType();
        resResult.setData(resDtoGetDeviceType);
        //
        EntityDeviceType entityDeviceType = mapperDeviceType.selectById(reqDtoGetDeviceType.getId());
        resDtoGetDeviceType.setId(entityDeviceType.getId());
        resDtoGetDeviceType.setName(entityDeviceType.getName());
        resDtoGetDeviceType.setType(entityDeviceType.getType());
        resDtoGetDeviceType.setCommunicationType(entityDeviceType.getCommunicationType());
        resDtoGetDeviceType.setProtocolType(entityDeviceType.getProtocolType());
        resDtoGetDeviceType.setProtocolFormat(entityDeviceType.getProtocolFormat());
        //
        return resResult;
    }

    @Override
    public ResResult<PageInfo<ResDtoPageDeviceType>> page(ReqDtoPageDeviceType reqDtoPageDeviceType) {
        //
        ResResult<PageInfo<ResDtoPageDeviceType>> resResult = new ResResult<>();
        PageInfo<ResDtoPageDeviceType> pageInfo = new PageInfo<>();
        pageInfo.setOffset(reqDtoPageDeviceType.getOffset());
        pageInfo.setRows(reqDtoPageDeviceType.getRows());
        resResult.setData(pageInfo);
        //
        List<ResDtoPageDeviceType> list = new ArrayList<>();
        List<EntityDeviceType> listForEntity = mapperDeviceType.limit(reqDtoPageDeviceType.getNameKey(), reqDtoPageDeviceType.getOffset(), reqDtoPageDeviceType.getRows());
        pageInfo.setTotal(mapperDeviceType.limitTotal(reqDtoPageDeviceType.getNameKey()));
        listForEntity.stream().forEach((entityDeviceType) -> {
            ResDtoPageDeviceType resDtoPageDeviceType = new ResDtoPageDeviceType();
            resDtoPageDeviceType.setId(entityDeviceType.getId());
            resDtoPageDeviceType.setName(entityDeviceType.getName());
            resDtoPageDeviceType.setType(entityDeviceType.getType());
            resDtoPageDeviceType.setCommunicationType(entityDeviceType.getCommunicationType());
            resDtoPageDeviceType.setProtocolType(entityDeviceType.getProtocolType());
            resDtoPageDeviceType.setProtocolFormat(entityDeviceType.getProtocolFormat());
            list.add(resDtoPageDeviceType);
        });
        pageInfo.setList(list);
        //
        return resResult;
    }
}
