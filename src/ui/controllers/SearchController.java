package ui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import bridge.ProductSearch;
import bridge.SearchAbstraction;
import bridge.SearchService;
import database.model.Product;
import chainofresponsibility.Search;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable, SearchAbstraction<Product> {
    private SearchService<Product> searchService;
    private ObservableList<ProductsController.ProductInfo> list = FXCollections.observableArrayList();
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
    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        setSearchService(new ProductSearch());
    }

    private void initCol() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        owner.setCellValueFactory(new PropertyValueFactory<>("owner"));
        tableView.setItems(list);

    }

    @Override
    public void setSearchService(SearchService<Product> searchService) {
        this.searchService = searchService;

    }
@FXML
private void search(){
    searchProduct(searchTextField.getText());
}
    private void searchProduct(String searchInput) {
        list.clear();
        Search search = new Search(searchInput, "product");
        List<Product> result = searchService.search(search);
        if(result !=null){
            for (Product p : result) {
                list.add(new ProductsController.ProductInfo(String.valueOf(result.indexOf(p)+1),p.getName(),p.getPrice().toString(),p.getOwner()));

            }
        }
        searchTextField.setText("");
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
