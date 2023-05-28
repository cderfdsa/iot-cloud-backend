package iot.cloud.backend.service.modules.history;

import iot.cloud.backend.service.dto.ReqDtoAddHistoryDeviceOnline;
import iot.cloud.backend.service.dto.ResDtoAdd;
import iot.cloud.backend.service.result.ResResult;

/**
 * @author weichuang
 */
public interface HistoryDeviceOnlineService {
    
    ResResult<ResDtoAdd> add(ReqDtoAddHistoryDeviceOnline reqDtoAddHistoryDeviceOnline);
}
