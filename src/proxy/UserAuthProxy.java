package proxy;

import database.model.User;
import database.repository.Repository;
import factory.RepoFactory;
import utils.RepoType;
import utils.UIUtil;

public class UserAuthProxy implements UserAuth {
    private UserAuth userAuth;
    private Repository<User> userRepository;

    public UserAuthProxy(UserAuth userAuth) {
        this.userAuth = userAuth;
        this.userRepository = RepoFactory.getInstance().getRepository(RepoType.USER);
    }

    @Override
    public boolean login(String username) {
        User user = userRepository.get(username);
        if(user == null){
            UIUtil.showErrorMessage("User does not exist");
            return false;
        }
        return userAuth.login(username);

    }

    @Override
    public boolean logout() {
        return userAuth.logout();
    }

    @Override
    public String getUsername() {
        return userAuth.getUsername();
    }
}
