package iot.cloud.backend.service.result;

/**
 * @author weichuang 2023/5/13 23:32
 */
public class ResultCodeCommon {
    public static ResResult FAIL = new ResResult<>(1000, "fail");
    public static ResResult NO_PERMISSION = new ResResult<>(1001, "no permission");
    public static ResResult INVALIDATE_TOKEN = new ResResult<>(1003, "invalidate token");
    public static ResResult PARAMETERS_INCOMPLETE = new ResResult<>(1005, "parameters incomplete");
}
