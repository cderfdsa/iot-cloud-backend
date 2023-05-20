package iot.cloud.backend.service.modules.device;

/**
 * @author weichuang
 */
public interface DeviceInfoService {
    boolean auth(String code, String pwd);
}
