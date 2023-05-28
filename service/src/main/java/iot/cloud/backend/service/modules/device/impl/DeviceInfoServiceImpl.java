package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.common.utils.RandomStringUtils;
import iot.cloud.backend.mapper.entity.EntityDeviceInfo;
import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttributeModbus;
import iot.cloud.backend.mapper.modules.device.MapperDeviceInfo;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weichuang
 */
@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {
    @Resource
    private MapperDeviceInfo mapperDeviceInfo;

    @Override
    @Cacheable(cacheManager = "cacheManagerForDevice", cacheNames = "auth", key = "#code")
    public boolean auth(String code, String pwd) {
        return mapperDeviceInfo.countByCodeAndPwd(code, pwd) == 1;
    }

    @Override
    public ResResult<ResDtoAdd> add(ReqDtoAddDeviceInfo reqDtoAddDeviceInfo) {
        //
        ResResult<ResDtoAdd> resResult = new ResResult<>();
        ResDtoAdd resDtoAdd = new ResDtoAdd();
        resResult.setData(resDtoAdd);
        //
        EntityDeviceInfo entityDeviceInfo = new EntityDeviceInfo();
        entityDeviceInfo.setRelDeviceTypeId(reqDtoAddDeviceInfo.getRelDeviceTypeId());
        entityDeviceInfo.setRelUserInfoId(UserUtils.getCurrentRequestUserId());
        entityDeviceInfo.setName(reqDtoAddDeviceInfo.getName());
        entityDeviceInfo.setCode(RandomStringUtils.randomAlphanumeric(6, 10));
        entityDeviceInfo.setPwd(RandomStringUtils.randomAlphanumeric(6, 10));
        //
        mapperDeviceInfo.insert(entityDeviceInfo);
        resDtoAdd.setId(entityDeviceInfo.getId());
        //
        return resResult;
    }

    @Override
    public ResResult<ResDtoGetDeviceInfo> get(ReqDtoGetDeviceInfo reqDtoGetDeviceInfo) {
        //
        ResResult<ResDtoGetDeviceInfo> resResult = new ResResult<>();
        ResDtoGetDeviceInfo resDtoGetDeviceInfo = new ResDtoGetDeviceInfo();
        resResult.setData(resDtoGetDeviceInfo);
        //
        EntityDeviceInfo entityDeviceInfo = mapperDeviceInfo.selectByCode(reqDtoGetDeviceInfo.getCode().get());
        resDtoGetDeviceInfo.setId(entityDeviceInfo.getId());
        resDtoGetDeviceInfo.setRelUserInfoId(entityDeviceInfo.getRelUserInfoId());
        resDtoGetDeviceInfo.setCode(entityDeviceInfo.getCode());
        resDtoGetDeviceInfo.setName(entityDeviceInfo.getName());
        resDtoGetDeviceInfo.setRelDeviceTypeId(entityDeviceInfo.getRelDeviceTypeId());
        //
        return resResult;
    }

    @Override
    public ResResult<List<ResDtoGetDeviceTypeAttributeModbus>> getAttributeModbusByCodeAndTimeBus(ReqDtoGetDeviceInfo reqDtoGetDeviceInfo) {
        //
        ResResult<List<ResDtoGetDeviceTypeAttributeModbus>> resResult = new ResResult<>();
        List<ResDtoGetDeviceTypeAttributeModbus> list = new ArrayList<>();
        resResult.setData(list);
        //
        List<EntityDeviceTypeAttributeModbus> attributeModbusList = mapperDeviceInfo.selectAttributeModbusByCodeAndTimeBus(reqDtoGetDeviceInfo.getCode().get(), reqDtoGetDeviceInfo.getBusTimeValue().get(), reqDtoGetDeviceInfo.getBusTimeUnit().get());
        for (EntityDeviceTypeAttributeModbus entityDeviceTypeAttributeModbus : attributeModbusList) {
            //
            ResDtoGetDeviceTypeAttributeModbus resDtoGetDeviceTypeAttributeModbus = new ResDtoGetDeviceTypeAttributeModbus();
            resDtoGetDeviceTypeAttributeModbus.setSlaveAddress(entityDeviceTypeAttributeModbus.getSlaveAddress());
            resDtoGetDeviceTypeAttributeModbus.setRegisterAddress(entityDeviceTypeAttributeModbus.getRegisterAddress());
            //
            list.add(resDtoGetDeviceTypeAttributeModbus);
        }
        //
        return resResult;
    }
}
