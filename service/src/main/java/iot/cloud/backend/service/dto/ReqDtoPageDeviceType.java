package iot.cloud.backend.service.dto;

import lombok.Data;

/**
 * @author weichuang
 */
@Data
public class ReqDtoPageDeviceType extends ReqDtoPage {
    private String searchKey;

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey.replaceAll("%", "\\\\%")
                .replaceAll("_", "\\\\_");
        ;
    }
}
