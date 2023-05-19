package iot.cloud.backend.service.utils;

/**
 * @author weichuang
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
