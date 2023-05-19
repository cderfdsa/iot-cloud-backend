package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author weichuang 2023/5/17 23:20
 */
@Data
public class ReqDtoEditDeviceType {
    @Min(1)
    private Long id;
    @NotEmpty
    private String name;
    @Range(min = 1, max = 3)
    private int type;
    @Range(min = 100, max = 400)
    private int communicationType;
    @Range(min = 1, max = 3)
    private int protocolType;
    @Range(min = 100, max = 400)
    private int protocolFormat;
}
