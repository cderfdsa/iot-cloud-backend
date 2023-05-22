package iot.cloud.backend.webapi.modules.common;

import com.alibaba.csp.sentinel.SphU;
import iot.cloud.backend.service.dto.ResDtoEmpty;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.modules.email.EmailSendService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.result.ResultCodeCommon;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weichuang
 */
@RestController
@RequestMapping("/vc")
public class ValidateCodeController {
    @Resource
    private EmailSendService emailSendService;
    @Resource
    private DeviceInfoService deviceInfoService;

    @PostMapping(value = "/sendEmail")
    public ResResult<ResDtoEmpty> add(@Valid @RequestBody @Email String email, BindingResult bindingResult) {
        try {
            SphU.entry("validateCode");
        } catch (Exception e) {
            return ResultCodeCommon.FREQUENCY_TOO_HIGH;
        }
        if (bindingResult.hasErrors()) {
            return ResultCodeCommon.PARAMETERS_INCOMPLETE;
        }
        return emailSendService.sendValidateCodeForLoginOrRegister(email);
    }

}
