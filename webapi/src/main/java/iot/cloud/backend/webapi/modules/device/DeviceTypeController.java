package iot.cloud.backend.webapi.modules.device;

import iot.cloud.backend.service.dto.ReqDtoAddDeviceType;
import iot.cloud.backend.service.dto.ResDtoAdd;
import iot.cloud.backend.service.result.ResResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weichuang 2023/5/17 23:18
 */
@RestController
@RequestMapping("/device/type")
public class DeviceTypeController {
    @PostMapping(value = "/add")
    public ResResult<ResDtoAdd> add(@RequestBody ReqDtoAddDeviceType reqDtoAddDeviceType) {
        return null;
    }
}
