package opsy.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "articles", schema = "workingschema")
public class Articles {
    private long articleId;
    private String title;
    private Date publishdate;
    private Users author;
    private String articleBody;

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "publishdate")
    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author")
    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @Basic
    @Column(name = "article_body")
    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", publishdate=" + publishdate +
                ", author=" + author +
                ", articleBody='" + articleBody + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articles articles = (Articles) o;
        return articleId == articles.articleId &&
                author == articles.author &&
                Objects.equals(title, articles.title) &&
                Objects.equals(publishdate, articles.publishdate) &&
                Objects.equals(articleBody, articles.articleBody);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleId, title, publishdate, author, articleBody);
    }
}
