package iot.cloud.backend.service.modules.user.impl;


import iot.cloud.backend.common.utils.JWTUtils;
import iot.cloud.backend.common.utils.RandomStringUtils;
import iot.cloud.backend.common.utils.StringUtils;
import iot.cloud.backend.common.utils.exception.InvalidateTokenException;
import iot.cloud.backend.common.utils.exception.ParametersIncompleteException;
import iot.cloud.backend.config.ConfigForJWT;
import iot.cloud.backend.mapper.entity.EntityUserInfo;
import iot.cloud.backend.mapper.modules.user.MapperUserInfo;
import iot.cloud.backend.service.dto.ReqDtoLoginOrRegister;
import iot.cloud.backend.service.dto.ResDtoGetUser;
import iot.cloud.backend.service.dto.ResDtoLoginOrRegister;
import iot.cloud.backend.service.modules.user.UserInfoService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.result.ResultCodeCommon;
import iot.cloud.backend.service.utils.UserUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author weichuang
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private MapperUserInfo mapperUserInfo;
    @Resource
    ConfigForJWT configForJWT;
    @Resource(name = "cacheManagerForLoginOrRegister")
    private CacheManager cacheManagerForLoginOrRegister;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResResult<ResDtoLoginOrRegister> loginOrRegister(ReqDtoLoginOrRegister reqDtoLoginOrRegister) {
        //
        if (StringUtils.isAnyEmpty(reqDtoLoginOrRegister.getEmail(), reqDtoLoginOrRegister.getValidateCode())) {
            throw new ParametersIncompleteException();
        }
        Cache cache = cacheManagerForLoginOrRegister.getCache("vcForLoginOrRegister");
        String realValidateCode = cache.get(reqDtoLoginOrRegister.getEmail(), String.class);
        if (realValidateCode == null || !realValidateCode.equals(reqDtoLoginOrRegister.getValidateCode())) {
            return ResultCodeCommon.VALIDATE_CODE_ERROR;
        }
        //
        EntityUserInfo entityUserInfo = mapperUserInfo.selectByEmail(reqDtoLoginOrRegister.getEmail());
        //
        ResDtoLoginOrRegister resDtoLoginOrRegister = new ResDtoLoginOrRegister();
        if (entityUserInfo == null) {
            entityUserInfo = new EntityUserInfo();
            entityUserInfo.setEmail(reqDtoLoginOrRegister.getEmail());
            // TODO 先这样
            entityUserInfo.setAccount(RandomStringUtils.randomAlphanumeric(6, 10));
            //
            entityUserInfo.setSecret(RandomStringUtils.randomAlphanumeric(6, 10));
            mapperUserInfo.insert(entityUserInfo);
            entityUserInfo = mapperUserInfo.selectByEmail(reqDtoLoginOrRegister.getEmail());
        }
        resDtoLoginOrRegister.setUser_id(entityUserInfo.getId());
        resDtoLoginOrRegister.setEmail(entityUserInfo.getEmail());
        resDtoLoginOrRegister.setAccount(entityUserInfo.getAccount());
        if (reqDtoLoginOrRegister.isDay30()) {
            resDtoLoginOrRegister.setToken(JWTUtils.createToken(entityUserInfo.getId(), entityUserInfo.getEmail(), configForJWT.getSecret(), 24 * 30));
        } else {
            resDtoLoginOrRegister.setToken(JWTUtils.createToken(entityUserInfo.getId(), entityUserInfo.getEmail(), configForJWT.getSecret(), configForJWT.getExpHours()));
        }
        //
        cache.evict(entityUserInfo.getEmail());
        //
        return new ResResult<>(resDtoLoginOrRegister);
    }

    @Override
    public ResResult<ResDtoGetUser> getUser() {
        EntityUserInfo entityUserInfo = mapperUserInfo.selectById(UserUtils.getCurrentRequestUserId());
        ResDtoGetUser resDtoGetUser = new ResDtoGetUser();
        resDtoGetUser.setUserId(entityUserInfo.getId());
        resDtoGetUser.setEmail(entityUserInfo.getEmail());
        return new ResResult<>(resDtoGetUser);
    }

    @Override
    public boolean authForMqtt(String account, String secret) {
        return mapperUserInfo.countByAccountAndSecret(account, secret) == 1;
    }

    @Override
    public String refreshToken(String oldToken) {
        try {
            return JWTUtils.createTokenByOld(oldToken, configForJWT.getSecret(), configForJWT.getExpHours());
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw new InvalidateTokenException();
        }
    }
}
