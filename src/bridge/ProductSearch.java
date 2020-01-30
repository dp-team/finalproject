package bridge;

import database.model.Product;
import chainofresponsibility.*;

import java.util.List;

public class ProductSearch implements SearchService<Product> {
    private SearchHandler<Product> chain;
    public ProductSearch(){
        buildChain();
    }
    @Override
    public List<Product> search(Search s) {
        return chain.handleSearch(s);
    }
    private void buildChain(){
        this.chain = new ProductNameSearch(new ProductPriceSearch(new ProductOwnerSearch(null)));
    }
}
