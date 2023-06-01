package iot.cloud.backend.service.modules.history.impl;

import iot.cloud.backend.mapper.entity.EntityHistoryDeviceAttribute;
import iot.cloud.backend.mapper.modules.history.MapperHistoryDeviceAttribute;
import iot.cloud.backend.mapper.vo.VoDayCount;
import iot.cloud.backend.service.dto.ResDtoAdd;
import iot.cloud.backend.service.dto.ResDtoDayCount;
import iot.cloud.backend.service.modules.history.HistoryDeviceAttributeService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weichuang
 */
@Service
public class HistoryDeviceAttributeServiceImpl implements HistoryDeviceAttributeService {
    @Resource
    private MapperHistoryDeviceAttribute mapperHistoryDeviceAttribute;


    @Override
    public ResResult<ResDtoAdd> add(String deviceCode, String attributeCode, Long value) {
        //
        ResResult<ResDtoAdd> resResult = new ResResult<>();
        ResDtoAdd resDtoAdd = new ResDtoAdd();
        resResult.setData(resDtoAdd);
        //
        EntityHistoryDeviceAttribute aentityHistoryDeviceAttribute = mapperHistoryDeviceAttribute.selectAttributeByTwoCode(deviceCode, attributeCode);
        //
        EntityHistoryDeviceAttribute entityHistoryDeviceAttribute = new EntityHistoryDeviceAttribute();
        entityHistoryDeviceAttribute.setRelUserInfoId(aentityHistoryDeviceAttribute.getRelUserInfoId());
        entityHistoryDeviceAttribute.setRelDeviceInfoId(aentityHistoryDeviceAttribute.getRelDeviceInfoId());
        entityHistoryDeviceAttribute.setDeviceName(aentityHistoryDeviceAttribute.getDeviceName());
        entityHistoryDeviceAttribute.setDeviceCode(aentityHistoryDeviceAttribute.getDeviceCode());
        entityHistoryDeviceAttribute.setDeviceTypeName(aentityHistoryDeviceAttribute.getDeviceTypeName());
        entityHistoryDeviceAttribute.setDeviceTypeAttributeName(aentityHistoryDeviceAttribute.getDeviceTypeAttributeName());
        entityHistoryDeviceAttribute.setDeviceTypeAttributeCode(aentityHistoryDeviceAttribute.getDeviceTypeAttributeCode());
        entityHistoryDeviceAttribute.setValue(value);
        //
        mapperHistoryDeviceAttribute.insert(entityHistoryDeviceAttribute);
        resDtoAdd.setId(entityHistoryDeviceAttribute.getId());
        //
        return resResult;
    }

    @Override
    public ResResult<List<ResDtoDayCount>> statisticsDayForCount() {
        List<VoDayCount> listVos = mapperHistoryDeviceAttribute.limitGroupCreateDtByUserInfoId(UserUtils.getCurrentRequestUserId(), 10);
        List<ResDtoDayCount> list = new ArrayList<>(listVos.size());
        for (VoDayCount voDayCount : listVos) {
            list.add(new ResDtoDayCount(voDayCount.getDay(), voDayCount.getCount()));
        }
        return new ResResult<>(list);
    }

}
