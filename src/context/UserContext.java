package context;

import entity.User;

/**
 * 单例模式存储登录用户信息
 */
public class UserContext {
    private static final UserContext instance = new UserContext();
    private User currentUser;

    private UserContext() {}

    public static UserContext getInstance() {
        return instance;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
