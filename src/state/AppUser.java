package state;

import proxy.UserAuth;

public class AppUser implements UserAuth {
    private String username;
    private State state;
    public AppUser(){
            state = new LoggedOutState(this);
            username = null;

    }
    protected void changeState(State state){
        this.state = state;
    }
    public boolean login(String username){
        System.out.println(state.getClass());
        return this.state.login(username);
    }
    public boolean logout(){
        return  this.state.logout();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
