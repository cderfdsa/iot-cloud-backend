package iot.cloud.backend.service.modules.user;

import iot.cloud.backend.service.dto.ReqDtoLogin;
import iot.cloud.backend.service.dto.ResDtoGetUser;
import iot.cloud.backend.service.dto.ResDtoLogin;
import iot.cloud.backend.service.result.ResResult;

/**
 * @author weichuang 2023/5/13 19:39
 */
public interface UserInfoService {
    ResResult<ResDtoLogin> login(ReqDtoLogin reqDtoLogin);

    ResResult<ResDtoGetUser> getUser();
}
