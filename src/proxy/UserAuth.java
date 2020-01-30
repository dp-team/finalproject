package proxy;

public interface UserAuth {
    boolean login(String username);
    boolean logout();
    String getUsername();
}
