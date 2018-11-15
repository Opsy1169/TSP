package opsy.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "comments", schema = "workingschema", catalog = "blog")
public class Comments {
    private long commentId;
    private String body;
    private Users authorId;
    private Date date;
    private int article;


    @Id
    @Column(name = "comment_id")
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

    @Override
    public String toString() {
        return "Comments{" +
                "commentId=" + commentId +
                ", body='" + body + '\'' +
                ", authorId=" + authorId +
                ", date=" + date +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    public Users getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Users authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "article")
    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments Comments = (Comments) o;
        return commentId == Comments.commentId &&
                authorId == Comments.authorId &&
                article == Comments.article &&
                Objects.equals(body, Comments.body) &&
                Objects.equals(date, Comments.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commentId, body, authorId, date, article);
    }
}
