package iot.cloud.backend.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoPageDeviceTypeAttributeModbus extends ReqDtoPage {
    @NotBlank
    private Long relDeviceTypeId;
    private String searchKey;

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey.replaceAll("%", "\\\\%")
                .replaceAll("_", "\\\\_");
        ;
    }
}
