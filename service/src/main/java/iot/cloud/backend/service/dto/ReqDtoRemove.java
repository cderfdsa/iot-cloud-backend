package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author weichuang 2023/5/13 19:41
 */
@Data
public class ReqDtoRemove extends BaseResDto {
    @Min(1)
    private Long id;
}
