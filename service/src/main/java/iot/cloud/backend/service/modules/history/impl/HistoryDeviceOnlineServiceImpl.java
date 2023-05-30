package iot.cloud.backend.service.modules.history.impl;

import iot.cloud.backend.mapper.entity.EntityDeviceInfo;
import iot.cloud.backend.mapper.entity.EntityHistoryDeviceOnline;
import iot.cloud.backend.mapper.modules.device.MapperDeviceInfo;
import iot.cloud.backend.mapper.modules.history.MapperHistoryDeviceOnline;
import iot.cloud.backend.service.modules.history.HistoryDeviceOnlineService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Service
public class HistoryDeviceOnlineServiceImpl implements HistoryDeviceOnlineService {
    @Resource
    private MapperHistoryDeviceOnline mapperHistoryDeviceOnline;
    @Resource
    private MapperDeviceInfo mapperDeviceInfo;


    @Override
    public void add(String deviceCode, Integer onOrOff, Integer statusReason) {
        //
        EntityDeviceInfo entityDeviceInfo = mapperDeviceInfo.selectByCode(deviceCode);
        //
        EntityHistoryDeviceOnline entityHistoryDeviceOnline = new EntityHistoryDeviceOnline();
        entityHistoryDeviceOnline.setRelUserInfoId(entityDeviceInfo.getRelUserInfoId());
        entityHistoryDeviceOnline.setRelDeviceInfoId(entityDeviceInfo.getId());
        entityHistoryDeviceOnline.setDeviceName(entityDeviceInfo.getName());
        entityHistoryDeviceOnline.setDeviceCode(entityDeviceInfo.getCode());
        entityHistoryDeviceOnline.setStatus(onOrOff);
        entityHistoryDeviceOnline.setStatusReason(statusReason);
        //
        mapperHistoryDeviceOnline.insert(entityHistoryDeviceOnline);
    }
}
