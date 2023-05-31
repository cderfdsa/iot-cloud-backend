package iot.cloud.backend.service.result;

/**
 * @author weichuang
 */
public class ResultCodeCommon {
    public static ResResult FAIL = new ResResult<>(1000, "fail");
    public static ResResult NO_PERMISSION = new ResResult<>(1001, "no permission");
    public static ResResult INVALIDATE_TOKEN = new ResResult<>(1003, "invalidate token");
    public static ResResult PARAMETERS_INCOMPLETE = new ResResult<>(1005, "parameters incomplete");
    public static ResResult VALIDATE_CODE_ERROR = new ResResult<>(1007, "validate code error");
    public static ResResult FREQUENCY_TOO_HIGH = new ResResult<>(1009, "frequency too high");
    public static ResResult PARAMETERS_VALUE_ERROR = new ResResult<>(1005, "parameters value error");
}
