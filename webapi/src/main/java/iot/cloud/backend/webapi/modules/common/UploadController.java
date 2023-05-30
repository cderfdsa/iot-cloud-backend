package iot.cloud.backend.webapi.modules.common;

import iot.cloud.backend.service.dto.ResDtoUpload;
import iot.cloud.backend.service.modules.common.FileStorageService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author weichuang
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Resource
    private FileStorageService fileStorageService;

    @PostMapping(value = "/file")
    public ResResult<ResDtoUpload> uploadFile(@RequestParam("file") MultipartFile file) {
        return fileStorageService.storeFile(file);
    }

}
