package opsy.entities;
//
//import opsy.data.UsersRepository;
import opsy.data.ArticlesRepository;
import opsy.data.CommentsRepository;
import opsy.data.UsersRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;


@Component("dao")
public class DAO {
    private NamedParameterJdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private ProjectionList projectionList = Projections.projectionList();
    public DAO(){
        projectionList.add(Projections.property("articleId"), "articleId");
        projectionList.add(Projections.property("title"), "title");
        projectionList.add(Projections.property("author"), "author");
        projectionList.add(Projections.property("publishdate"), "publishdate");

    }


//    @Autowired
//    private SessionFactory sessionFactory;

//    @Transactional
//    public List<Comments> getAllComments(){
////        Session session = sessionFactory.openSession();
////        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
////        Session session = sessionFactory.openSession();
////        CriteriaBuilder builder = session.getCriteriaBuilder();
////        javax.persistence.criteria.CriteriaQuery<Comments> query= builder.createQuery(Comments.class);
////        Root<Comments> root = query.from(Comments.class);
////        query.select(root);
////        org.hibernate.query.Query<Comments> commentsQuery = session.createQuery(query);
////        List<Comments> comments = commentsQuery.getResultList();
//        List<Comments> comments = commentsRepository.findAll();
//        Users user =  usersRepository.getByLogin("opsy");
//        List<Comments> opsyComments = commentsRepository.findAllByAuthorId(user);
//        List<Articles> articles = articlesRepository.findAll();
//        List<Articles> articles1 = getArticlesRepresentationByAuthor(user);
//        Articles article = getArticlesRepresentation().get(0);
////        List<Comments> commentsByArticle = commentsRepository.findAllByArticle(article);
//
////
////        System.out.println(commentsByArticle);
////
//
//        return comments;
//    }


    public Users getUserById(long id){
//          Session session = sessionFactory.openSession();
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Users u where u.login = :login ");
        query.setParameter("login", "opsy");
        List users = query.list();
        System.out.println((Users) users.get(0));
        return (Users) users.get(0);
        //return (Users) repository.getByLogin("opsy").get(0);

    }

    @Transactional
    public void insertComment(Comments comment){
//        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
//        Session session = sessionFactory.openSession();
//        comment.setAuthorId(getUserById(1));
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(comment);
//        session.getTransaction().commit();
    }

    public List<Articles> getArticlesRepresentation(){
        DetachedCriteria criteria = DetachedCriteria.forClass(Articles.class, "a");
        createAliases(criteria);
        List<Articles> articles = createArticlesList(criteria);
        return articles;

    }
    public List<Articles> getArticlesRepresentationByAuthor(Users author){
        DetachedCriteria criteria = DetachedCriteria.forClass(Articles.class, "a");
        criteria.add(Restrictions.eq("author", author));
        createAliases(criteria);
        List<Articles> articles = createArticlesList(criteria);
        return articles;
    }

    public Articles getArticleRepresentationById(long id){
        DetachedCriteria criteria = DetachedCriteria.forClass(Articles.class, "a");
        criteria.add(Restrictions.eq("articleId", id));
        createAliases(criteria);
        List<Articles> articles = createArticlesList(criteria);
        return articles.get(0);
    }

//    public List<Comments> getCommentsRepresentationByAuthor(long articleid){
//        DetachedCriteria criteria = DetachedCriteria.forClass(Comments.class, "c");
//        Articles article = getArticleRepresentationById(articleid);
//        criteria.add(Restrictions.eq("author", author));
//        createAliases(criteria);
//        List<Articles> articles = createArticlesList(criteria);
//        return articles;
//    }

    private void createAliases(DetachedCriteria criteria){
        criteria.createAlias("a.author", "author");
//        criteria.createAlias("a.title", "title");
//        criteria.createAlias("a.date", "date");
//        criteria.createAlias("a.articleId", "articleId");
    }

    private List<Articles> createArticlesList(DetachedCriteria criteria){
        Criteria criteria1 = criteria.getExecutableCriteria(entityManagerFactory.unwrap(SessionFactory.class).openSession());
        criteria.setProjection(projectionList).setResultTransformer(Transformers.aliasToBean(Articles.class));
        return criteria1.list();
    }

//    private List<Comments> createCommentsList(DetachedCriteria criteria){
//
//    }

}
