package iot.cloud.backend.service.modules.history;

/**
 * @author weichuang
 */
public interface HistoryDeviceOnlineService {

    void add(String deviceCode, Integer onOrOff, Integer statusReason);
}
