package chainofresponsibility;

import factory.RepoFactory;
import singleton.AppContext;
import database.repository.Repository;
import database.model.Product;
import utils.RepoType;

import java.util.List;
import java.util.stream.Collectors;

public class ProductOwnerSearch extends  SearchHandler<Product>{


    public ProductOwnerSearch(SearchHandler next) {
        super(next);
    }

    @Override
    public List<Product> handleSearch(Search s) {
            if(s.getType().equalsIgnoreCase("product")){
                Repository<Product> productRepository = RepoFactory.getInstance().getRepository(RepoType.PRODUCT);
                List<Product> productsBySupplier = productRepository.all().stream().filter(product -> product.getOwner().equalsIgnoreCase(s.getKeyword())).collect(Collectors.toList());
                System.out.println("owner");
                if(productsBySupplier.size() != 0){
                    return productsBySupplier;
                }
            }
        return super.handleSearch(s);
    }
}
