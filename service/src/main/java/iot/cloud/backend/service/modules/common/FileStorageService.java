package iot.cloud.backend.service.modules.common;

import iot.cloud.backend.service.dto.ResDtoUpload;
import iot.cloud.backend.service.result.ResResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author weichuang
 */
public interface FileStorageService {
    ResResult<ResDtoUpload> storeFile(MultipartFile file);
}
