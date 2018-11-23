package opsy.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comments", schema = "workingschema")
public class shit {
    private long commentId;
    private String body;
    private Users authorId;
    private Date date;
    private long article;

    @Override
    public String toString() {
        return "shit{" +
                "commentId=" + commentId +
                ", body='" + body + '\'' +
                ", authorId=" + authorId +
                ", date=" + date +
                ", article=" + article +
                '}';
    }

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    public Users getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Users authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "article")
    public long getArticle() {
        return article;
    }

    public void setArticle(long authorId) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        shit comments = (shit) o;
        return commentId == comments.commentId &&
                authorId == comments.authorId &&
                Objects.equals(body, comments.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commentId, body, authorId);
    }
}
