package pqdong.face.recognition.data;

import java.util.HashMap;
import java.util.Map;

/**
 * CurReq
 * copy from:
 * @author zhengw
 * @since 2019/09/18
 */
public class CurReq {

    private static final String KEY_USER = "user";

    private static ThreadLocal<Map<String, Object>> store = new ThreadLocal<>();

    public static void init() {
        store.set(new HashMap<>());
    }

    public static UserInfo getUser() {
        return (UserInfo) store.get().get(KEY_USER);
    }

    public static void setUser(UserInfo user) {
        store.get().put(KEY_USER, user);
    }

    public static boolean existUser() {
        return store.get().containsKey(KEY_USER);
    }
}
