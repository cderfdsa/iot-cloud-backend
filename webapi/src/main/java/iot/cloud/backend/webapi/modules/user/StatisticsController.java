package iot.cloud.backend.webapi.modules.user;

import iot.cloud.backend.service.dto.ResDtoDayCount;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.modules.history.HistoryDeviceAttributeService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weichuang
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Resource
    private HistoryDeviceAttributeService historyDeviceAttributeService;
    @Resource
    private DeviceInfoService deviceInfoService;

    @PostMapping(value = "/attributes/statisticsDayForCount")
    public ResResult<List<ResDtoDayCount>> statisticsDayForCount() {
        return historyDeviceAttributeService.statisticsDayForCount();
    }

    @PostMapping(value = "/device/status/statisticsDeviceStatus")
    public ResResult<List<Integer>> statisticsDeviceStatus() {
        return deviceInfoService.statisticsManyDeviceStatus();
    }

    @PostMapping(value = "/device/type/statisticsDeviceType")
    public ResResult<List<ResDtoDayCount>> statisticsDeviceType() {
        return historyDeviceAttributeService.statisticsDayForCount();
    }

    @PostMapping(value = "/device/status/statisticsDeviceOnline")
    public ResResult<List<ResDtoDayCount>> statisticsDeviceOnline() {
        return historyDeviceAttributeService.statisticsDayForCount();
    }

    @PostMapping(value = "/device/status/statisticsDeviceActive")
    public ResResult<List<ResDtoDayCount>> statisticsDeviceActive() {
        return historyDeviceAttributeService.statisticsDayForCount();
    }

    @PostMapping(value = "/device/status/statisticsDeviceAlarm")
    public ResResult<List<ResDtoDayCount>> statisticsDeviceAlarm() {
        return historyDeviceAttributeService.statisticsDayForCount();
    }


}
