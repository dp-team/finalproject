import factory.RepoFactory;
import singleton.AppContext;
import database.repository.Repository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import database.model.Product;
import database.model.User;
import utils.RepoType;

import java.io.IOException;
import java.net.URL;

public class
Main extends Application {

    public static void main(String[] args) throws IOException {
        loadSampleData();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("ui/resources/fxml/main.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        primaryStage.setTitle("Suppliers Union");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    static void loadSampleData() {
        try {
            Repository<User> userRepo = RepoFactory.getInstance().getRepository(RepoType.USER);
            Repository<Product> productRepo = RepoFactory.getInstance().getRepository(RepoType.PRODUCT);
            User admin = new User("admin");
            User alice = new User("alice");
            User bob = new User("bob");
            User peter = new User("peter");
            User john = new User("john");
            admin.setChildren(new String[]{"bob", "alice", "john", "peter"});
            bob.setChildren(new String[]{"alice", "peter"});
            peter.setChildren(new String[]{"john"});
            userRepo.add(alice);
            userRepo.add(john);
            userRepo.add(peter);
            userRepo.add(bob);
            userRepo.add(admin);
            Product product1 = new Product("Factory", (float) 122.0, "admin");
            Product product2 = new Product("Singleton", (float) 122.0, "alice");
            Product product6 = new Product("Chain of Responsibility", (float) 122.0, "admin");
            Product product5 = new Product("Bridge", (float) 122.0, "john");
            Product product3 = new Product("Composite", (float) 122.0, "bob");
            Product product7 = new Product("Mediator", (float) 122.0, "admin");
            Product product4 = new Product("Proxy", (float) 122.0, "peter");
            productRepo.add(product1);
            productRepo.add(product2);
            productRepo.add(product3);
            productRepo.add(product4);
            productRepo.add(product5);
            productRepo.add(product6);
            productRepo.add(product7);
        } catch (Exception e) {
            System.out.println("data already loaded");
        }
    }
}