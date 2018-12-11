package opsy.entities;

public class ArticleDTO {
    private String title;
    private String articleBody;
    private String category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String body) {
        this.articleBody = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
