package iot.cloud.backend.service.dto;

import lombok.Data;

import java.util.Optional;

/**
 * @author weichuang
 */
@Data
public class ReqDtoPageDeviceInfo extends ReqDtoPage {
    private String searchKey;

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey.replaceAll("%", "\\\\%")
                .replaceAll("_", "\\\\_");
        ;
    }

    private Optional<Long> relDeviceTypeId = Optional.empty();
}
