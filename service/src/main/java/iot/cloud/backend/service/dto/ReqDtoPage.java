package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoPage {
    private Long offset;
    @Min(1)
    @Max(100)
    private int rows;
}
