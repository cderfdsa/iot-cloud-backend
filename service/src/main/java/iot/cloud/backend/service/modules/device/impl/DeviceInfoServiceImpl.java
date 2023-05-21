package iot.cloud.backend.service.modules.device.impl;

import iot.cloud.backend.common.utils.RandomStringUtils;
import iot.cloud.backend.mapper.entity.EntityDeviceInfo;
import iot.cloud.backend.mapper.modules.device.MapperDeviceInfo;
import iot.cloud.backend.service.dto.ReqDtoAddDeviceInfo;
import iot.cloud.backend.service.dto.ResDtoAdd;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {
    @Resource
    private MapperDeviceInfo mapperDeviceInfo;

    @Override
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
}
