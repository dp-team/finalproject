package chainofresponsibility;

import java.util.List;

public abstract class SearchHandler<T> {
    private SearchHandler next;

    public SearchHandler(SearchHandler next) {
        this.next = next;
    }

    public List<T> handleSearch(Search s) {
        if (next != null) {
            return next.handleSearch(s);
        }
        return null;
    }

}
