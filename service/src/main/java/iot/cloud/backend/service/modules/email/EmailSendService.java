package iot.cloud.backend.service.modules.email;

import iot.cloud.backend.service.dto.ResDtoValidateCode;
import iot.cloud.backend.service.result.ResResult;

/**
 * @author weichuang
 */
public interface EmailSendService {
    ResResult<ResDtoValidateCode> sendValidateCodeForLoginOrRegister(String email);
}
