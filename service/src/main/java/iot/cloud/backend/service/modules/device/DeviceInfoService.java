package iot.cloud.backend.service.modules.device;

import iot.cloud.backend.service.dto.ReqDtoAddDeviceInfo;
import iot.cloud.backend.service.dto.ResDtoAdd;
import iot.cloud.backend.service.result.ResResult;

/**
 * @author weichuang
 */
public interface DeviceInfoService {
    boolean auth(String code, String pwd);

    ResResult<ResDtoAdd> add(ReqDtoAddDeviceInfo reqDtoAddDeviceInfo);
}
