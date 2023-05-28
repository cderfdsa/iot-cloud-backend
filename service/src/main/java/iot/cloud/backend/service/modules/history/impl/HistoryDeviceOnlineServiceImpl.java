package iot.cloud.backend.service.modules.history.impl;

import iot.cloud.backend.mapper.entity.EntityHistoryDeviceOnline;
import iot.cloud.backend.mapper.modules.history.MapperHistoryDeviceOnline;
import iot.cloud.backend.service.dto.ReqDtoAddHistoryDeviceOnline;
import iot.cloud.backend.service.dto.ResDtoAdd;
import iot.cloud.backend.service.modules.history.HistoryDeviceOnlineService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Service
public class HistoryDeviceOnlineServiceImpl implements HistoryDeviceOnlineService {
    @Resource
    private MapperHistoryDeviceOnline mapperHistoryDeviceOnline;

    @Override
    public ResResult<ResDtoAdd> add(ReqDtoAddHistoryDeviceOnline reqDtoAddHistoryDeviceOnline) {
        //
        ResResult<ResDtoAdd> resResult = new ResResult<>();
        ResDtoAdd resDtoAdd = new ResDtoAdd();
        resResult.setData(resDtoAdd);
        //
        EntityHistoryDeviceOnline entityHistoryDeviceOnline = new EntityHistoryDeviceOnline();
        entityHistoryDeviceOnline.setRelUserInfoId(reqDtoAddHistoryDeviceOnline.getRelUserInfoId());
        entityHistoryDeviceOnline.setRelDeviceInfoId(reqDtoAddHistoryDeviceOnline.getRelDeviceInfoId());
        entityHistoryDeviceOnline.setDeviceName(reqDtoAddHistoryDeviceOnline.getDeviceName());
        entityHistoryDeviceOnline.setDeviceCode(reqDtoAddHistoryDeviceOnline.getDeviceCode());
        entityHistoryDeviceOnline.setStatus(reqDtoAddHistoryDeviceOnline.getStatus());
        entityHistoryDeviceOnline.setStatusReason(reqDtoAddHistoryDeviceOnline.getStatusReason());
        //
        mapperHistoryDeviceOnline.insert(entityHistoryDeviceOnline);
        resDtoAdd.setId(entityHistoryDeviceOnline.getId());
        //
        return resResult;
    }
}
