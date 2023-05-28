package iot.cloud.backend.service.dto;

import lombok.Data;

import java.util.Optional;

/**
 * @author weichuang
 */
@Data
public class ReqDtoGetDeviceInfo {
    private Optional<Long> id;
    private Optional<String> code;
    private Optional<Integer> busTimeValue;
    private Optional<Character> busTimeUnit;

    public ReqDtoGetDeviceInfo(String code) {
        this.code = Optional.of(code);
    }

    public ReqDtoGetDeviceInfo(String code, Integer busTimeValue, Character busTimeUnit) {
        this.code = Optional.of(code);
        this.busTimeValue = Optional.of(busTimeValue);
        this.busTimeUnit = Optional.of(busTimeUnit);
    }
}
