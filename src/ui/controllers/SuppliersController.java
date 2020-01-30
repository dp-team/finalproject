package ui.controllers;

import com.jfoenix.controls.JFXTreeView;
import proxy.UserAuth;
import singleton.AppContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import composite.Supplier;
import state.AppUser;
import utils.UIUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class SuppliersController implements Initializable {
    @FXML
    private JFXTreeView<String> supplierTreeView;
    private UserAuth appUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appUser = AppContext.getInstance().getUserAuth();


    }

    private void loadTree(String[] suppliers) {
        String rootName =appUser.getUsername() == null ? "Not Logged In" : appUser.getUsername();
        TreeItem<String> root = new TreeItem<>(rootName);
        root.setExpanded(true);
        if (suppliers != null) {


            for (String supplier : suppliers) {
                root.getChildren().add(new TreeItem<>(supplier));
            }
        }
        supplierTreeView.setRoot(root);
    }

    @FXML
    private void addSupplier() {
        UIUtil.loadWindow(getClass().getResource("/ui/resources/fxml/addSupplier.fxml"), "Add Supplier", null);
    }

    @FXML
    private void reload() {
        Supplier supplier = new Supplier(appUser.getUsername());
        String [] suppliers = new String[supplier.getChildren().size()];
        for(int i=0; i<supplier.getChildren().size();i++){
            suppliers[i] = supplier.getChildren().get(i).getUsername();
        }
        loadTree(suppliers);


    }
}
