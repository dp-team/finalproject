package ui.controllers;

import proxy.UserAuth;
import singleton.AppContext;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.repository.Repository;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import database.model.User;
import composite.Supplier;
import state.AppUser;
import utils.RepoType;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSupplierController implements Initializable {
    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private JFXButton saveBtn;
    private Boolean isInEditMode = Boolean.FALSE;

    private UserAuth appUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appUser = AppContext.getInstance().getUserAuth();

    }

    public void handleAddUpdate(Event event) {
        if (isInEditMode) {
        } else {
            add(event);
        }
    }

    public void add(Event event) {
        if (nameTextField.getText().equals("")) {
            nameTextField.getStyleClass().add("wrong-credentials");
        } else {
            Supplier supplier = new Supplier(appUser.getUsername());
            supplier.addSupplier(nameTextField.getText());
            ((Node) event.getSource()).getScene().getWindow().hide();
        }

    }

    public void clearFields() {
        nameTextField.setText("");
    }

    public void close(Event event) {
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

    public void inflateUI(Supplier supplier) {
        nameTextField.setText(supplier.getUser().getUsername());
    }
}
