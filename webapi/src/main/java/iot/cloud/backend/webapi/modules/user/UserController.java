package iot.cloud.backend.webapi.modules.user;

import iot.cloud.backend.service.dto.ReqDtoLogin;
import iot.cloud.backend.service.dto.ResDtoLogin;
import iot.cloud.backend.service.modules.user.UserInfoService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weichuang 2023/5/13 19:48
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserInfoService userInfoService;

    @PostMapping(value = "/login")
    public ResResult<ResDtoLogin> login(@RequestBody ReqDtoLogin reqDtoLogin) {
        return userInfoService.login(reqDtoLogin);
    }
}
