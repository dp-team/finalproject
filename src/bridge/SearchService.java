package bridge;

import chainofresponsibility.Search;

import java.util.List;

public interface SearchService<T> {
    List<T> search(Search s);

}
