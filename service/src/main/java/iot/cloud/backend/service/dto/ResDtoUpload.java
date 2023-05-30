package iot.cloud.backend.service.dto;

import iot.cloud.backend.common.base.BaseResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weichuang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResDtoUpload extends BaseResDto {
    private String url;
    private String fullUrl;
}
