package state;

public class LoggedInState implements State {
    private AppUser appUser;
    public LoggedInState(AppUser appUser){
        this.appUser = appUser;
    }


    @Override
    public boolean login(String username) {
        System.out.println("call login on login state");
        return false;
    }

    @Override
    public boolean logout() {
        System.out.println("call logout on login state");
        appUser.setUsername(null);
        appUser.changeState(new LoggedOutState(appUser));
        return true;
    }
}
