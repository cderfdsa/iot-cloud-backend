package iot.cloud.backend.service.modules.device;

import iot.cloud.backend.common.base.PageInfo;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.result.ResResult;

/**
 * @author weichuang
 */
public interface DeviceTypeAttributeService {
    ResResult<ResDtoAdd> add(ReqDtoAddDeviceTypeAttribute reqDtoAddDeviceTypeAttribute);

    ResResult<ResDtoEdit> edit(ReqDtoEditDeviceTypeAttribute reqDtoEditDeviceTypeAttribute);

    ResResult<ResDtoRemove> remove(ReqDtoRemove reqDtoRemove);

    ResResult<ResDtoGetDeviceTypeAttribute> get(ReqDtoGetDeviceTypeAttribute reqDtoGetDeviceTypeAttribute);

    ResResult<PageInfo<ResDtoPageDeviceTypeAttribute>> page(ReqDtoPageDeviceTypeAttribute reqDtoPageDeviceTypeAttribute);
}
