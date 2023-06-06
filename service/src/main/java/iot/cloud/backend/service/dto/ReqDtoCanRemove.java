package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoCanRemove extends BaseResDto {
    @Min(1)
    private Long id;
}
