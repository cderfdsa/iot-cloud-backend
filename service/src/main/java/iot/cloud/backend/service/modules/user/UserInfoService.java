package iot.cloud.backend.service.modules.user;

import iot.cloud.backend.service.dto.ReqDtoLoginOrRegister;
import iot.cloud.backend.service.dto.ResDtoGetUser;
import iot.cloud.backend.service.dto.ResDtoLoginOrRegister;
import iot.cloud.backend.service.result.ResResult;

/**
 * @author weichuang 2023/5/13 19:39
 */
public interface UserInfoService {

    ResResult<ResDtoLoginOrRegister> loginOrRegister(ReqDtoLoginOrRegister reqDtoLoginOrRegister);

    ResResult<ResDtoGetUser> getUser();
}
