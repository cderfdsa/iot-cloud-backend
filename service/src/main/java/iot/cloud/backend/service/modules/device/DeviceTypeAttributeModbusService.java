package iot.cloud.backend.service.modules.device;

import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.result.ResResult;

/**
 * @author weichuang
 */
public interface DeviceTypeAttributeModbusService {
    ResResult<ResDtoAdd> add(ReqDtoAddDeviceTypeAttributeModbus reqDtoAddDeviceTypeAttributeModbus);

    ResResult<ResDtoEdit> edit(ReqDtoEditDeviceTypeAttributeModbus reqDtoEditDeviceTypeAttributeModbus);

    ResResult<ResDtoRemove> remove(ReqDtoRemove reqDtoRemove);

    ResResult<ResDtoGetDeviceTypeAttributeModbus> get(ReqDtoGetDeviceTypeAttributeModbus reqDtoGetDeviceTypeAttributeModbus);
}
