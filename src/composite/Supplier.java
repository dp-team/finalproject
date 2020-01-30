package composite;

import factory.RepoFactory;
import singleton.AppContext;
import database.repository.Repository;
import database.model.Product;
import database.model.User;
import utils.RepoType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Supplier {
    private User user;
    private String username;
    private List<Supplier> children = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private Repository<User> userRepository = RepoFactory.getInstance().getRepository(RepoType.USER);
    private Repository<Product> productRepository = RepoFactory.getInstance().getRepository(RepoType.PRODUCT);

    public Supplier(String username) {
        this.username = username;
        this.user = userRepository.get(username);
        getProductsByUser();
        addChildrenToList();
    }

    public void addSupplier(String username) {
        try {
            // create a user in the database
            userRepository.add(new User(username));
            // update children list in the database
            user.setChildren(getUpdatedList(username));
            userRepository.update(user);
            // add children to the children list
            user = userRepository.get(user.getUsername());
          addChildrenToList();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void addProduct(Product product) {
        try {
            product.setOwner(user.getUsername());
            productRepository.add(product);
            products.add(product);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Supplier> getChildren() {
        return children;
    }

    public List<Product> getProducts() {
        return products;
    }

    public User getUser() {
        return user;
    }

    private void getProductsByUser() {
        try {
            List<Product> productsBySupplier = productRepository.all().stream().filter(product -> product.getOwner().contains(user.getUsername())).collect(Collectors.toList());
            for (Product p : productsBySupplier) {
                products.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void addChildrenToList() {
        try {
            for (String child : user.getChildren()) {
                children.add(new Supplier(child));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    private String[] getUpdatedList(String username){
        String[] oldChildren = user.getChildren();
        if(oldChildren != null){
            String[] newChildren = new String[]{username};
            int oldLength = oldChildren.length;
            int newLength = newChildren.length;
            String[] childrenList = new String[oldLength + newLength];
            System.arraycopy(oldChildren, 0, childrenList, 0, oldLength);
            System.arraycopy(newChildren, 0, childrenList, oldLength, newLength);
            return childrenList;
        }
        return new String[]{username};

    }

}
