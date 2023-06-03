package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author weichuang
 */
@Data
public class ReqDtoAddDeviceType {
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
    @Range(min = 1, max = 100)
    private int busTimeValue;
    @NotEmpty
    private String busTimeUnit;
}
