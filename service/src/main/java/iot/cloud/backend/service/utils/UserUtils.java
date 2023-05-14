package iot.cloud.backend.service.utils;

/**
 * @author weichuang 2023/5/14 19:39
 */
public class UserUtils {
    private static final ThreadLocal<Long> threadLocalForUserId = new ThreadLocal<>();

    public static void saveUserId(Long userId) {
        threadLocalForUserId.set(userId);
    }

    public static void removeAll() {
        threadLocalForUserId.remove();
    }

    public static Long getCurrentRequestUserId() {
        return threadLocalForUserId.get();
    }
}
