package ui.controllers;

import proxy.UserAuth;
import singleton.AppContext;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import database.model.Product;
import composite.Supplier;
import state.AppUser;
import utils.UIUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {
    private ObservableList<ProductInfo> list = FXCollections.observableArrayList();
    @FXML
    private TableView<ProductsController.ProductInfo> tableView;
    @FXML
    private TableColumn<ProductsController.ProductInfo, String> id;
    @FXML
    private TableColumn<ProductsController.ProductInfo, String> name;
    @FXML
    private TableColumn<ProductsController.ProductInfo, String> price;
    @FXML
    private TableColumn<ProductsController.ProductInfo, String> owner;
    private UserAuth appUser;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       initCol();
        appUser = AppContext.getInstance().getUserAuth();

    }
    private void initCol() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        owner.setCellValueFactory(new PropertyValueFactory<>("owner"));
        tableView.setItems(list);

    }
    @FXML
    private void loadData(){
        list.clear();
        Supplier supplier = new Supplier(appUser.getUsername());
        if(supplier !=null){
            for (Product p : supplier.getProducts()) {
                list.add(new ProductInfo(String.valueOf(supplier.getProducts().indexOf(p)),p.getName(),p.getPrice().toString(),p.getOwner()));

            }
        }
    }
    @FXML
    private void addProduct(){
        UIUtil.loadWindow(getClass().getResource("/ui/resources/fxml/addProduct.fxml"), "Add Product", null);
    }

    public static class ProductInfo {
        private final SimpleStringProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty price;
        private final SimpleStringProperty owner;

        public ProductInfo(String id, String name, String price, String owner) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleStringProperty(price);
            this.owner = new SimpleStringProperty(owner);
        }

        public String getId() {
            return id.get();
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public String getPrice() {
            return price.get();
        }

        public SimpleStringProperty priceProperty() {
            return price;
        }

        public String getOwner() {
            return owner.get();
        }

        public SimpleStringProperty ownerProperty() {
            return owner;
        }
    }
}
