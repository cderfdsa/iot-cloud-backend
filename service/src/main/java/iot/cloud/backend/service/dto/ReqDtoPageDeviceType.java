package iot.cloud.backend.service.dto;

import lombok.Data;

import java.util.List;

/**
 * @author weichuang 2023/5/17 23:20
 */
@Data
public class ReqDtoPageDeviceType extends ReqDtoPage {
    private List<Long> ids;
    private String nameKey;
}
