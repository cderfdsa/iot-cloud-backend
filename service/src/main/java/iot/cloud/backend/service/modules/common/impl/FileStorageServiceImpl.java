package iot.cloud.backend.service.modules.common.impl;

import cn.hutool.core.io.file.FileNameUtil;
import iot.cloud.backend.common.utils.RandomStringUtils;
import iot.cloud.backend.service.dto.ResDtoUpload;
import iot.cloud.backend.service.modules.common.FileStorageService;
import iot.cloud.backend.service.result.ResResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author weichuang
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {
    private Path fileStoragePath;
    private String fileStorageLocation;
    @Value("${iot.cloud.backend.file.url.path:/uploads}")
    private String urlPath;
    @Value("${iot.cloud.backend.file.url.full-path}")
    private String urlFullPath = "";

    public FileStorageServiceImpl(@Value("${iot.cloud.backend.file.storage.location:temp}") String fileStorageLocation) {
        this.fileStorageLocation = fileStorageLocation;
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }
    }

    @Override
    public ResResult<ResDtoUpload> storeFile(MultipartFile file) {
        //
        ResResult<ResDtoUpload> resResult = new ResResult<>();
        ResDtoUpload resDtoUpload = new ResDtoUpload();
        resResult.setData(resDtoUpload);
        //
        String fileName = System.currentTimeMillis() + "-" + RandomStringUtils.randomAlphabetic(4) + "." + FileNameUtil.extName(file.getOriginalFilename());
        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
        //
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the file", e);
        }
        //
        resDtoUpload.setUrl(urlPath + "/" + fileName);
        resDtoUpload.setFullUrl(urlFullPath + "/" + fileName);
        //
        return resResult;
    }
}
