package iot.cloud.backend.webapi.modules.device;

import iot.cloud.backend.service.dto.*;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeModbusService;
import iot.cloud.backend.service.modules.device.DeviceTypeAttributeService;
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
 * @author weichuang 2023/5/17 23:18
 */
@RestController
@RequestMapping("/device/type")
public class DeviceTypeController {
    @Resource
    private DeviceTypeService deviceTypeService;
    @Resource
    private DeviceTypeAttributeService deviceTypeAttributeService;
    @Resource
    private DeviceTypeAttributeModbusService deviceTypeAttributeModbusService;

    @PostMapping(value = "/add")
    public ResResult<ResDtoAdd> add(@Valid @RequestBody ReqDtoAddDeviceType reqDtoAddDeviceType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultCodeCommon.PARAMETERS_INCOMPLETE;
        }
        return deviceTypeService.add(reqDtoAddDeviceType);
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

    @PostMapping(value = "/attribute/add")
    public ResResult<ResDtoAdd> addAttribute(@RequestBody ReqDtoAddDeviceTypeAttribute reqDtoAddDeviceTypeAttribute) {
        return deviceTypeAttributeService.add(reqDtoAddDeviceTypeAttribute);
    }

    @PostMapping(value = "/attribute/edit")
    public ResResult<ResDtoEdit> editAttribute(@RequestBody ReqDtoEditDeviceTypeAttribute reqDtoEditDeviceTypeAttribute) {
        return deviceTypeAttributeService.edit(reqDtoEditDeviceTypeAttribute);
    }

    @PostMapping(value = "/attribute/remove")
    public ResResult<ResDtoRemove> removeAttribute(@RequestBody ReqDtoRemove reqDtoRemove) {
        return deviceTypeAttributeService.remove(reqDtoRemove);
    }

    @PostMapping(value = "/attribute/get")
    public ResResult<ResDtoGetDeviceTypeAttribute> getAttributes(@RequestBody ReqDtoGetDeviceTypeAttribute reqDtoGetDeviceTypeAttribute) {
        return deviceTypeAttributeService.get(reqDtoGetDeviceTypeAttribute);
    }

    @PostMapping(value = "/attribute/modbus/add")
    public ResResult<ResDtoAdd> addAttributeModbus(@RequestBody ReqDtoAddDeviceTypeAttributeModbus reqDtoAddDeviceTypeAttributeModbus) {
        return deviceTypeAttributeModbusService.add(reqDtoAddDeviceTypeAttributeModbus);
    }

    @PostMapping(value = "/attribute/modbus/edit")
    public ResResult<ResDtoEdit> editAttributeModbus(@RequestBody ReqDtoEditDeviceTypeAttributeModbus reqDtoEditDeviceTypeAttributeModbus) {
        return deviceTypeAttributeModbusService.edit(reqDtoEditDeviceTypeAttributeModbus);
    }

    @PostMapping(value = "/attribute/modbus/remove")
    public ResResult<ResDtoRemove> removeAttributeModbus(@RequestBody ReqDtoRemove reqDtoRemove) {
        return deviceTypeAttributeModbusService.remove(reqDtoRemove);
    }

    @PostMapping(value = "/attribute/modbus/get")
    public ResResult<ResDtoGetDeviceTypeAttributeModbus> getAttributesModbus(@RequestBody ReqDtoGetDeviceTypeAttributeModbus reqDtoGetDeviceTypeAttributeModbus) {
        return deviceTypeAttributeModbusService.get(reqDtoGetDeviceTypeAttributeModbus);
    }
}
