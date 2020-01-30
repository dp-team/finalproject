package ui.controllers;

import proxy.UserAuth;
import singleton.AppContext;
import state.AppUser;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import composite.Supplier;
import utils.UIUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXButton logoutBtn;
    @FXML
    private VBox userInfo;
    @FXML
    private Text username;
    @FXML
    private Text totalProducts;
    @FXML
    private Text totalDistributors;
    private UserAuth appUser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appUser = AppContext.getInstance().getUserAuth();
        bindVisibility();
        updateInfo();

    }

    private void bindVisibility() {
        loginBtn.managedProperty().bind(loginBtn.visibleProperty());
        logoutBtn.managedProperty().bind(logoutBtn.visibleProperty());
        userInfo.managedProperty().bind(userInfo.visibleProperty());

    }

    @FXML
    private void login() {
        UIUtil.loadWindow(getClass().getResource("/ui/resources/fxml/login.fxml"), "Login", null);
        userInfo.setVisible(true);


    }

    @FXML
    private void logout() {
        appUser.logout();
        toggleLogin(false);
        updateInfo();


    }

    private void toggleLogin(Boolean loggedIn) {
        loginBtn.setVisible(!loggedIn);
        logoutBtn.setVisible(loggedIn);

    }

    @FXML
    private void updateInfo() {
        Supplier supplier = new Supplier(appUser.getUsername());
        if (supplier != null && supplier.getUser() != null) {
            username.setText(supplier.getUser().getUsername());
            totalProducts.setText(String.valueOf(supplier.getProducts().size()));
            totalDistributors.setText(String.valueOf(supplier.getChildren().size()));
            toggleLogin(true);
        } else {
            username.setText("");
            totalProducts.setText("0");
            totalDistributors.setText("0");
            userInfo.setVisible(false);
            toggleLogin(false);
            appUser.logout();
        }

    }
}
