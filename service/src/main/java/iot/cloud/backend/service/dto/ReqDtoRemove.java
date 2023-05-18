package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.Data;

import java.util.List;

/**
 * @author weichuang 2023/5/13 19:41
 */
@Data
public class ReqDtoRemove extends BaseResDto {
    private Long id;
    private List<Long> ids;
}
