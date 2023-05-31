package iot.cloud.backend.webapi.config;

import iot.cloud.backend.common.utils.exception.InvalidateTokenException;
import iot.cloud.backend.common.utils.exception.ParametersIncompleteException;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.result.ResultCodeCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author weichuang
 */
@RestControllerAdvice
@Slf4j
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

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    @ResponseBody
    public ResResult<?> missingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return ResultCodeCommon.PARAMETERS_INCOMPLETE;
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseBody
    public ResResult<?> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResultCodeCommon.PARAMETERS_VALUE_ERROR;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResResult<?> exception(Exception exception) {
        exception.printStackTrace();
        log.error(exception.toString());
        return ResultCodeCommon.FAIL;
    }
}
