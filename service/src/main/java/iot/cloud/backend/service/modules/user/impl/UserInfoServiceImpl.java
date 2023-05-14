package iot.cloud.backend.service.modules.user.impl;


import iot.cloud.backend.mapper.entity.EntityUserInfo;
import iot.cloud.backend.mapper.modules.user.MapperUserInfo;
import iot.cloud.backend.service.dto.ReqDtoLogin;
import iot.cloud.backend.service.dto.ResDtoLogin;
import iot.cloud.backend.service.modules.user.UserInfoService;
import iot.cloud.backend.service.result.ResResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang 2023/5/13 19:43
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private MapperUserInfo mapperUserInfo;

    @Override
    public ResResult<ResDtoLogin> login(ReqDtoLogin reqDtoLogin) {
        EntityUserInfo entityUserInfo = mapperUserInfo.selectById();
        ResDtoLogin resDtoLogin = new ResDtoLogin();
        resDtoLogin.setUser_id(entityUserInfo.getId());
        resDtoLogin.setEmail(entityUserInfo.getEmail());
        resDtoLogin.setToken("aaa");
        return new ResResult<>(resDtoLogin);
    }
}
