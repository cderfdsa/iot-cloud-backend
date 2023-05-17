package iot.cloud.backend.service.modules.user.impl;


import iot.cloud.backend.common.utils.JWTUtils;
import iot.cloud.backend.common.utils.RandomStringUtils;
import iot.cloud.backend.common.utils.StringUtils;
import iot.cloud.backend.common.utils.exception.ParametersIncompleteException;
import iot.cloud.backend.config.ConfigForJWT;
import iot.cloud.backend.mapper.entity.EntityUserInfo;
import iot.cloud.backend.mapper.modules.user.MapperUserInfo;
import iot.cloud.backend.service.dto.ReqDtoLoginOrRegister;
import iot.cloud.backend.service.dto.ResDtoGetUser;
import iot.cloud.backend.service.dto.ResDtoLoginOrRegister;
import iot.cloud.backend.service.modules.user.UserInfoService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.utils.UserUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.RandomUtils;
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
    public ResResult<ResDtoLoginOrRegister> loginOrRegister(ReqDtoLoginOrRegister reqDtoLoginOrRegister) {
        //
        if (StringUtils.isAnyEmpty(reqDtoLoginOrRegister.getEmail(), reqDtoLoginOrRegister.getValidateCode())) {
            throw new ParametersIncompleteException();
        }
        //
        EntityUserInfo entityUserInfo = mapperUserInfo.selectByEmail(reqDtoLoginOrRegister.getEmail());
        //
        ResDtoLoginOrRegister resDtoLoginOrRegister = new ResDtoLoginOrRegister();
        if(entityUserInfo == null){
            entityUserInfo = new EntityUserInfo();
            entityUserInfo.setEmail(reqDtoLoginOrRegister.getEmail());
            // TODO 先这样
            entityUserInfo.setAccount(RandomStringUtils.randomAscii(6,10));
            mapperUserInfo.insert(entityUserInfo);
            entityUserInfo = mapperUserInfo.selectByEmail(reqDtoLoginOrRegister.getEmail());
        }
        resDtoLoginOrRegister.setUser_id(entityUserInfo.getId());
        resDtoLoginOrRegister.setEmail(entityUserInfo.getEmail());
        resDtoLoginOrRegister.setAccount(entityUserInfo.getAccount());
        resDtoLoginOrRegister.setToken(JWTUtils.createToken(entityUserInfo.getId(), entityUserInfo.getEmail(), configForJWT.getSecret(), configForJWT.getExpHours()));
        return new ResResult<>(resDtoLoginOrRegister);
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
