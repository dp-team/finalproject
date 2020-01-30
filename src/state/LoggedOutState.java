package state;

public class LoggedOutState implements State {
    private AppUser appUser;
    public LoggedOutState(AppUser appUser){
        this.appUser = appUser;
    }
    @Override
    public boolean login(String username) {
        System.out.println("call login on logout state");
        appUser.setUsername(username);
        appUser.changeState(new LoggedInState(appUser));
        return true;
    }
    @Override
    public boolean logout() {
        System.out.println("call logout on logout state");
        return false;
    }

}
