package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.common.base.PageInfo;
import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttributeModbus;
import iot.cloud.backend.mapper.modules.device.MapperDeviceTypeAttributeModbus;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeModbusService;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weichuang
 */
@Service
public class DeviceTypeAttributeModbusServiceImpl implements DeviceTypeAttributeModbusService {
    @Resource
    private MapperDeviceTypeAttributeModbus mapperDeviceTypeAttributeModbus;
    @Resource
    private DeviceTypeAttributeService deviceTypeAttributeService;

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

    @Override
    public ResResult<PageInfo<ResDtoPageDeviceTypeAttributeModbus>> page(ReqDtoPageDeviceTypeAttributeModbus reqDtoPageDeviceTypeAttributeModbus) {
        //
        ResResult<PageInfo<ResDtoPageDeviceTypeAttributeModbus>> resResult = new ResResult<>();
        PageInfo<ResDtoPageDeviceTypeAttributeModbus> pageInfo = new PageInfo<>();
        pageInfo.setOffset(reqDtoPageDeviceTypeAttributeModbus.getOffset());
        pageInfo.setRows(reqDtoPageDeviceTypeAttributeModbus.getRows());
        resResult.setData(pageInfo);
        //
        List<ResDtoPageDeviceTypeAttributeModbus> list = new ArrayList<>();
        List<EntityDeviceTypeAttributeModbus> listForEntity = mapperDeviceTypeAttributeModbus.limit(reqDtoPageDeviceTypeAttributeModbus.getRelDeviceTypeId(), reqDtoPageDeviceTypeAttributeModbus.getSearchKey(), reqDtoPageDeviceTypeAttributeModbus.getOffset(), reqDtoPageDeviceTypeAttributeModbus.getRows());
        pageInfo.setTotal(mapperDeviceTypeAttributeModbus.limitTotal(reqDtoPageDeviceTypeAttributeModbus.getRelDeviceTypeId(), reqDtoPageDeviceTypeAttributeModbus.getSearchKey()));
        Map<Long, String> idNameMap = deviceTypeAttributeService.getIdNamesByIds(listForEntity.stream().map(EntityDeviceTypeAttributeModbus::getRelDeviceTypeAttributeId).collect(Collectors.toList()));
        listForEntity.stream().forEach((entityDeviceTypeAttributeModbus) -> {
            ResDtoPageDeviceTypeAttributeModbus resDtoPageDeviceTypeAttributeModbus = new ResDtoPageDeviceTypeAttributeModbus();
            resDtoPageDeviceTypeAttributeModbus.setId(entityDeviceTypeAttributeModbus.getId());
            resDtoPageDeviceTypeAttributeModbus.setRelDeviceTypeId(entityDeviceTypeAttributeModbus.getRelDeviceTypeId());
            resDtoPageDeviceTypeAttributeModbus.setRelDeviceTypeAttributeId(entityDeviceTypeAttributeModbus.getRelDeviceTypeAttributeId());
            resDtoPageDeviceTypeAttributeModbus.setSlaveAddress(entityDeviceTypeAttributeModbus.getSlaveAddress());
            resDtoPageDeviceTypeAttributeModbus.setRegisterAddress(entityDeviceTypeAttributeModbus.getRegisterAddress());
            resDtoPageDeviceTypeAttributeModbus.setReadWriteType(entityDeviceTypeAttributeModbus.getReadWriteType());
            resDtoPageDeviceTypeAttributeModbus.setDataType(entityDeviceTypeAttributeModbus.getDataType());
            resDtoPageDeviceTypeAttributeModbus.setRelDeviceTypeAttributeName(idNameMap.get(entityDeviceTypeAttributeModbus.getRelDeviceTypeAttributeId()));
            list.add(resDtoPageDeviceTypeAttributeModbus);
        });
        pageInfo.setList(list);
        //
        return resResult;
    }
}
