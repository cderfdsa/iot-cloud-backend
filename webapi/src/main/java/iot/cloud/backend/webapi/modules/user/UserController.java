package iot.cloud.backend.webapi.modules.user;

import iot.cloud.backend.service.dto.ReqDtoLoginOrRegister;
import iot.cloud.backend.service.dto.ResDtoGetUser;
import iot.cloud.backend.service.dto.ResDtoLoginOrRegister;
import iot.cloud.backend.service.modules.user.UserInfoService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author weichuang
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserInfoService userInfoService;

    @PostMapping(value = "/ns/loginOrRegister")
    public ResResult<ResDtoLoginOrRegister> loginOrRegister(@RequestBody ReqDtoLoginOrRegister reqDtoLoginOrRegister) {
        return userInfoService.loginOrRegister(reqDtoLoginOrRegister);
    }

    @PostMapping(value = "/getUser")
    public ResResult<ResDtoGetUser> getUser() {
        return userInfoService.getUser();
    }

    @GetMapping("/ns/token/refresh")
    public ResResult<String> refreshToken(@RequestParam("oldToken") String oldToken) {
        return new ResResult<>(userInfoService.refreshToken(oldToken));
    }


}
