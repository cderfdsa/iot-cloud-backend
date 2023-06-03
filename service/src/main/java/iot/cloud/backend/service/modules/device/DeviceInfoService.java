package iot.cloud.backend.service.modules.device;

import iot.cloud.backend.common.base.PageInfo;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.result.ResResult;

import java.util.List;

/**
 * @author weichuang
 */
public interface DeviceInfoService {
    boolean auth(String code, String pwd);

    ResResult<ResDtoAdd> add(ReqDtoAddDeviceInfo reqDtoAddDeviceInfo);

    ResResult<ResDtoGetDeviceInfo> get(ReqDtoGetDeviceInfo reqDtoGetDeviceInfo);

    ResResult<List<ResDtoGetDeviceTypeAttributeModbus>> getAttributeModbusByCodeAndTimeBus(ReqDtoGetDeviceInfo reqDtoGetDeviceInfo);

    String getAccountByDeviceCode(String deviceCode);

    ResResult<List<Integer>> statisticsManyDeviceStatus();

    ResResult<PageInfo<ResDtoPageDeviceInfo>> page(ReqDtoPageDeviceInfo reqDtoPageDeviceInfo);
}
