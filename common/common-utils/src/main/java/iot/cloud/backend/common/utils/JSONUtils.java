package iot.cloud.backend.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author weichuang
 */
public class JSONUtils {
    public static String formatString(String jsonStr) {
        return JSON.parse(jsonStr).toString();
    }

    public static JSONObject parseObject(String str) {
        return JSONObject.parseObject(str);
    }
}
