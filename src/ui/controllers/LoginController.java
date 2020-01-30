package ui.controllers;

import proxy.UserAuth;
import singleton.AppContext;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import database.model.Product;
import state.AppUser;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private JFXTextField usernameTextField;
    private UserAuth appUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appUser = AppContext.getInstance().getUserAuth();

    }

    public void handleLogin(Event event) {
        login(event);
    }

    public void login(Event event) {
        if (usernameTextField.getText().equals("")) {
            usernameTextField.getStyleClass().add("wrong-credentials");
        } else {
            if(appUser.login(usernameTextField.getText())){
                ((Node) event.getSource()).getScene().getWindow().hide();
            }


        }

    }

    public void close(Event event) {
        ((Node) event.getSource()).getScene().getWindow().hide();

    }
}
