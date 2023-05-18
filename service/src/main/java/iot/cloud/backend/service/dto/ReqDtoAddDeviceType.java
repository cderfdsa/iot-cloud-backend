package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author weichuang 2023/5/17 23:20
 */
@Data
public class ReqDtoAddDeviceType {
    @NotEmpty
    private String name;
    @Range(min = 1, max = 3)
    private int type;

    private int communicationType;
    @Range(min = 1, max = 3)
    private int protocolType;
    
    private int protocolFormat;
}
