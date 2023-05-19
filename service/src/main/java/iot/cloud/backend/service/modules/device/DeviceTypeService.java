package iot.cloud.backend.service.modules.device;

import iot.cloud.backend.common.base.PageInfo;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.result.ResResult;

/**
 * @author weichuang 2023/5/18 16:52
 */
public interface DeviceTypeService {

    ResResult<ResDtoAdd> add(ReqDtoAddDeviceType reqDtoAddDeviceType);

    ResResult<ResDtoEdit> edit(ReqDtoEditDeviceType reqDtoEditDeviceType);

    ResResult<ResDtoRemove> remove(ReqDtoRemove reqDtoRemove);

    ResResult<ResDtoGetDeviceType> get(ReqDtoGetDeviceType reqDtoGetDeviceType);

    ResResult<PageInfo<ResDtoPageDeviceType>> page(ReqDtoPageDeviceType reqDtoPageDeviceType);
}
