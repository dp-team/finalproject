package ui.controllers;

import proxy.UserAuth;
import singleton.AppContext;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import database.model.Product;
import composite.Supplier;
import state.AppUser;


import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXTextField priceTextField;
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
    public void handleAddUpdate(Event event){
        if(isInEditMode){
            update(event);
        }
        else {
            add(event);
        }
    }

    public void add(Event event){
        if (nameTextField.getText().equals("") || priceTextField.getText().equals("")){
            nameTextField.getStyleClass().add("wrong-credentials");
            priceTextField.getStyleClass().add("wrong-credentials");
        }
        else {
            Float price =   Float.parseFloat(priceTextField.getText());
            Supplier supplier = new Supplier(appUser.getUsername());
            supplier.addProduct(new Product(nameTextField.getText(),Float.parseFloat(priceTextField.getText()),supplier.getUser().getUsername()));
            ((Node) event.getSource()).getScene().getWindow().hide();
        }

    }
    public void update(Event event){
        if (nameTextField.getText().equals("") || priceTextField.getText().equals("")){
            nameTextField.getStyleClass().add("wrong-credentials");
            priceTextField.getStyleClass().add("wrong-credentials");
        }
        else {
            // TODO Handle update
        }

    }
    public void clearFields(){
        nameTextField.setText("");
        priceTextField.setText("");
    }
    public void close(Event event){
        ((Node) event.getSource()).getScene().getWindow().hide();

    }
    public void inflateUI(Product product) {
        nameTextField.setText(product.getName());
        priceTextField.setText(product.getPrice().toString());
        saveBtn.setText("Update");
        isInEditMode = Boolean.TRUE;
    }
}
