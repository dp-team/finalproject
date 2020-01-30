package factory;


import database.repository.JsonProductRepository;
import database.repository.JsonUserRepository;
import database.repository.Repository;
import utils.RepoType;

public class RepoFactory {
    private static final RepoFactory INSTANCE = new RepoFactory();
    private Repository userRepo;
    private Repository productRepo;

    private RepoFactory() {

    }

    public static RepoFactory getInstance() {
        return INSTANCE;
    }

    public Repository getRepository(RepoType type) {
        switch (type) {
            case USER:
                return userRepo == null ? newUserInstance() : userRepo;
            case PRODUCT:
                return productRepo == null ? newProductInstance() : productRepo;
            default:
                return null;
        }
    }

    private Repository newUserInstance() {
        this.userRepo = new JsonUserRepository();
        return this.userRepo;
    }

    private Repository newProductInstance() {
        this.productRepo = new JsonProductRepository();
        return this.productRepo;
    }

}
