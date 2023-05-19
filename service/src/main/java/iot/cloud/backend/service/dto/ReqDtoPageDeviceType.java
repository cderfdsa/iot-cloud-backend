package iot.cloud.backend.service.dto;

import lombok.Data;

import java.util.List;

/**
 * @author weichuang
 */
@Data
public class ReqDtoPageDeviceType extends ReqDtoPage {
    private List<Long> ids;
    private String nameKey;
}
