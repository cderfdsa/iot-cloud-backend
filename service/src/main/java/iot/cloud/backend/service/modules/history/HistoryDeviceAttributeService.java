package iot.cloud.backend.service.modules.history;

import iot.cloud.backend.service.dto.ResDtoAdd;
import iot.cloud.backend.service.dto.ResDtoDayCount;
import iot.cloud.backend.service.result.ResResult;

import java.util.List;

/**
 * @author weichuang
 */
public interface HistoryDeviceAttributeService {

    ResResult<ResDtoAdd> add(String deviceCode, String attributeCode, Long value);

    ResResult<List<ResDtoDayCount>> statisticsDayForCount();
}
