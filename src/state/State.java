package state;

public interface State {
    boolean login(String username);
    boolean logout();
}
