package iot.cloud.backend.common.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author weichuang 2023/5/17 16:54
 */
@Slf4j
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {
    public static boolean isAnyEmpty(Object... args) {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (org.apache.commons.lang3.ObjectUtils.isEmpty(arg)) {
                return false;
            }
        }
        return true;
    }
}
