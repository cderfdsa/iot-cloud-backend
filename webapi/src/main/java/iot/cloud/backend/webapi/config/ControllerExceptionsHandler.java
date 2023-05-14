package iot.cloud.backend.webapi.config;

import iot.cloud.backend.common.utils.exception.InvalidateTokenException;
import iot.cloud.backend.common.utils.exception.ParametersIncompleteException;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.result.ResultCodeCommon;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author weichuang 2023/5/14 17:46
 */
@RestControllerAdvice
public class ControllerExceptionsHandler {
    @ExceptionHandler(value = {InvalidateTokenException.class})
    @ResponseBody
    public ResResult<?> invalidateTokenException(InvalidateTokenException exception) {
        return ResultCodeCommon.INVALIDATE_TOKEN;
    }

    @ExceptionHandler(value = {ParametersIncompleteException.class})
    @ResponseBody
    public ResResult<?> parametersIncompleteException(ParametersIncompleteException exception) {
        return ResultCodeCommon.PARAMETERS_INCOMPLETE;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResResult<?> exception(Exception exception) {
        return ResultCodeCommon.FAIL;
    }
}
