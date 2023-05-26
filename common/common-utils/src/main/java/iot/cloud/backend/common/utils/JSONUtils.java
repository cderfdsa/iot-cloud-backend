package iot.cloud.backend.common.utils;

import com.alibaba.fastjson2.JSON;

/**
 * @author weichuang
 */
public class JSONUtils {
    public static String formatString(String jsonStr) {
        return JSON.parse(jsonStr).toString();
    }
}
