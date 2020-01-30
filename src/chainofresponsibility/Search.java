package chainofresponsibility;

public class Search {
    private final String keyword;
    private final String type;

    public Search(String keyword, String type) {
        this.keyword = keyword;
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getType() {
        return type;
    }
}
