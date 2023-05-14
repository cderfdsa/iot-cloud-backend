package iot.cloud.backend.service.modules.user.impl;


import iot.cloud.backend.common.utils.JWTUtils;
import iot.cloud.backend.common.utils.StringUtils;
import iot.cloud.backend.common.utils.exception.ParametersIncompleteException;
import iot.cloud.backend.config.ConfigForJWT;
import iot.cloud.backend.mapper.entity.EntityUserInfo;
import iot.cloud.backend.mapper.modules.user.MapperUserInfo;
import iot.cloud.backend.service.dto.ReqDtoLogin;
import iot.cloud.backend.service.dto.ResDtoGetUser;
import iot.cloud.backend.service.dto.ResDtoLogin;
import iot.cloud.backend.service.modules.user.UserInfoService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author weichuang 2023/5/13 19:43
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private MapperUserInfo mapperUserInfo;
    @Resource
    ConfigForJWT configForJWT;

    @Override
    public ResResult<ResDtoLogin> login(ReqDtoLogin reqDtoLogin) {
        //
        if (StringUtils.isAnyEmpty(reqDtoLogin.getEmail(), reqDtoLogin.getValidateCode())) {
            throw new ParametersIncompleteException();
        }
        //
        EntityUserInfo entityUserInfo = mapperUserInfo.selectByEmail(reqDtoLogin.getEmail());
        ResDtoLogin resDtoLogin = new ResDtoLogin();
        resDtoLogin.setUser_id(entityUserInfo.getId());
        resDtoLogin.setEmail(entityUserInfo.getEmail());
        resDtoLogin.setToken(JWTUtils.createToken(entityUserInfo.getId(), entityUserInfo.getEmail(), configForJWT.getSecret(), configForJWT.getExpHours()));
        return new ResResult<>(resDtoLogin);
    }

    @Override
    public ResResult<ResDtoGetUser> getUser() {
        EntityUserInfo entityUserInfo = mapperUserInfo.selectById(UserUtils.getCurrentRequestUserId());
        ResDtoGetUser resDtoGetUser = new ResDtoGetUser();
        resDtoGetUser.setUser_id(entityUserInfo.getId());
        resDtoGetUser.setEmail(entityUserInfo.getEmail());
        return new ResResult<>(resDtoGetUser);
    }
}
