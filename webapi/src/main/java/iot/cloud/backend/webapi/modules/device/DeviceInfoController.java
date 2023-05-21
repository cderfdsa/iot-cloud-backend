package iot.cloud.backend.webapi.modules.device;

import iot.cloud.backend.common.base.PageInfo;
import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.modules.device.DeviceTypeService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.result.ResultCodeCommon;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weichuang
 */
@RestController
@RequestMapping("/device")
public class DeviceInfoController {
    @Resource
    private DeviceTypeService deviceTypeService;
    @Resource
    private DeviceInfoService deviceInfoService;

    @PostMapping(value = "/add")
    public ResResult<ResDtoAdd> add(@Valid @RequestBody ReqDtoAddDeviceInfo reqDtoAddDeviceInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultCodeCommon.PARAMETERS_INCOMPLETE;
        }
        return deviceInfoService.add(reqDtoAddDeviceInfo);
    }

    @PostMapping(value = "/edit")
    public ResResult<ResDtoEdit> edit(@RequestBody ReqDtoEditDeviceType reqDtoEditDeviceType) {
        return deviceTypeService.edit(reqDtoEditDeviceType);
    }

    @PostMapping(value = "/remove")
    public ResResult<ResDtoRemove> remove(@RequestBody ReqDtoRemove reqDtoRemove) {
        return deviceTypeService.remove(reqDtoRemove);
    }

    @PostMapping(value = "/get")
    public ResResult<ResDtoGetDeviceType> get(@RequestBody ReqDtoGetDeviceType reqDtoGetDeviceType) {
        return deviceTypeService.get(reqDtoGetDeviceType);
    }

    @PostMapping(value = "/page")
    public ResResult<PageInfo<ResDtoPageDeviceType>> page(@RequestBody ReqDtoPageDeviceType reqDtoPageDeviceType) {
        return deviceTypeService.page(reqDtoPageDeviceType);
    }


}
