package singleton;


import proxy.UserAuth;
import proxy.UserAuthProxy;
import state.AppUser;

public class AppContext {
    private static final AppContext INSTANCE = new AppContext();
    private UserAuth userAuth;

    private AppContext() {
        userAuth = new UserAuthProxy(new AppUser());
    }
    public UserAuth getUserAuth() {
        return userAuth;
    }

    public static AppContext getInstance() {
        return INSTANCE;
        }

}
