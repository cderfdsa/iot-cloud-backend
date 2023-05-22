package iot.cloud.backend.service.modules.email;

import iot.cloud.backend.service.dto.ResDtoEmpty;
import iot.cloud.backend.service.result.ResResult;

/**
 * @author weichuang
 */
public interface EmailSendService {
    ResResult<ResDtoEmpty> sendValidateCodeForLoginOrRegister(String email);
}
