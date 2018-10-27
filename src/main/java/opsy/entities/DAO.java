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
import javax.security.auth.login.Configuration;
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
//    public List<shit> getAllComments(){
////        Session session = sessionFactory.openSession();
////        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
////        Session session = sessionFactory.openSession();
////        CriteriaBuilder builder = session.getCriteriaBuilder();
////        javax.persistence.criteria.CriteriaQuery<shit> query= builder.createQuery(shit.class);
////        Root<shit> root = query.from(shit.class);
////        query.select(root);
////        org.hibernate.query.Query<shit> commentsQuery = session.createQuery(query);
////        List<shit> comments = commentsQuery.getResultList();
//        List<shit> comments = commentsRepository.findAll();
//        Users user =  usersRepository.getByLogin("opsy");
//        List<shit> opsyComments = commentsRepository.findAllByAuthorId(user);
//        List<Articles> articles = articlesRepository.findAll();
//        List<Articles> articles1 = getArticlesRepresentationByAuthor(user);
//        Articles article = getArticlesRepresentation().get(0);
////        List<shit> commentsByArticle = commentsRepository.findAllByArticle(article);
//
////
////        System.out.println(commentsByArticle);
////
//
//        return comments;
//    }

    /**
     * пример работы не с репозиторием, а с гибернейтом
     * Нужно взять первые две строчки, а дальше работать с обычной гибернейтовской сессией
     * Возможны разные конфликты при использовании одновлеменно репозиториев и гибернейт-сессии
     * По решению проблемы искать что-то entity manager merge...
     */
    public Users getUserById(long id){
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Users u where u.login = :login ");
        query.setParameter("login", "opsy");
        List users = query.list();
        System.out.println((Users) users.get(0));
        return (Users) users.get(0);
        //return (Users) repository.getByLogin("opsy").get(0);

    }

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users getUserByLogin(String login){
        return usersRepository.findByLogin(login);
    }

    public Users getUserById(Long id){
        return usersRepository.findByUserId(id);
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


    /**
     * Метод для получения списка всех статей без тела
     * По замыслу нужен для отрисовки списка статей и т.д.(чтобы не тащить объемное тело без надобности)
     */
    public List<Articles> getArticlesRepresentation(){
        DetachedCriteria criteria = DetachedCriteria.forClass(Articles.class, "a");
        createAliases(criteria);
        List<Articles> articles = createArticlesList(criteria);
        return articles;

    }

    /**
     * Метод для получения списка статей без тела по автору
     * По замыслу нужен для отрисовки списка статей и т.д.(чтобы не тащить объемное тело без надобности)
     * @param author
     * @return список статей
     */
    public List<Articles> getArticlesRepresentationByAuthor(Users author){
        DetachedCriteria criteria = DetachedCriteria.forClass(Articles.class, "a");
        criteria.add(Restrictions.eq("author", author));
        createAliases(criteria);
        List<Articles> articles = createArticlesList(criteria);
        return articles;
    }

    /**
     * Метод для получения объекта статьи без тела по айди
     * По замыслу нужен для отрисовки списка статей и т.д.(чтобы не тащить объемное тело без надобности)
     * @param id
     * @return статья
     */
    public Articles getArticleRepresentationById(long id){
        DetachedCriteria criteria = DetachedCriteria.forClass(Articles.class, "a");
        criteria.add(Restrictions.eq("articleId", id));
        createAliases(criteria);
        List<Articles> articles = createArticlesList(criteria);
        return articles.get(0);
    }

//    public List<shit> getCommentsRepresentationByAuthor(long articleid){
//        DetachedCriteria criteria = DetachedCriteria.forClass(shit.class, "c");
//        Articles article = getArticleRepresentationById(articleid);
//        criteria.add(Restrictions.eq("author", author));
//        createAliases(criteria);
//        List<Articles> articles = createArticlesList(criteria);
//        return articles;
//    }


    /**
     * этот и следующий методы -- вспомогательные методы, позволящие работать
     * с гибернейт-проекциями(для получения не целого объекта, а отдельных его полей)
     * @param criteria
     */
    private void createAliases(DetachedCriteria criteria){
        criteria.createAlias("a.author", "author");
    }

    private List<Articles> createArticlesList(DetachedCriteria criteria){
        Criteria criteria1 = criteria.getExecutableCriteria(entityManagerFactory.unwrap(SessionFactory.class).openSession());
        criteria.setProjection(projectionList).setResultTransformer(Transformers.aliasToBean(Articles.class));
        return criteria1.list();
    }


    /**
     * Удаление отдельно статьи
     *     Размышления аналогичны методу снизу
     *     В deleteUser не вызываю этот метод, чтобы удаление статей происходило за один запрос
     *     По крайней мере чтобы было такое ощущение
     * @param articleId
     */
    @Transactional
    public void deleteArticle(long articleId){
        commentsRepository.deleteAllByArticle((int) articleId);
        articlesRepository.deleteById((int) articleId);
    }


    /**
     * Удаление пользователя
     *     Сделано черт пойми как, пытался сделать транзакционно, но в принципе архитектура БД составлена плохл
     *     и в целом ситуация нетипичная, так что реализовано таким образом, во всяком случае пока
     *     закомменчены всякие варианты удаления через гибернейт, но в итоге они не лучше конечного
     * @param id
     */
    @Transactional
    public void deleteUser(long id){
        Users users = usersRepository.findByUserId(id);
        List<Articles> articles = getArticlesRepresentationByAuthor(users);
        for (Articles article: articles) {
            commentsRepository.deleteAllByArticle((int) article.getArticleId());
        }
        articlesRepository.deleteAllByAuthor(users);
        usersRepository.delete(users);
//        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("delete from workingschema.articles where author = ").addEntity(Articles.class);
//        query.setParameter("id", 8);
//        Query query1 = session.createQuery("from Users  u where userId = 8");
        //Users users = usersRepository.findByUserId(8);
//        Query query = session.createQuery("from Articles a where author = :author");
//        query.setParameter("author", users);
//        List<Articles> articles =  query.list();
        //List<Articles> articles =  articlesRepository.findAllByAuthor(users);
        //session.beginTransaction();
//        for (Articles a : articles) {
//            session.merge(a);
//            session.delete(a);
//        }
        //query.executeUpdate();
        //session.getTransaction().commit();
//        Query query = session.createQuery("delete Articles where articleId = 5 ");
//        query.setParameter("id", new Long(5));
//        query.executeUpdate();
//        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
//        Session session = sessionFactory.openSession();
//        Users users = usersRepository.findByUserId(id);
//        List<Articles> list = articlesRepository.findAllByAuthor(users);
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("delete  Articles where author = :id ");
//        query.setParameter("id", users);
//        query.executeUpdate();
//        for (Articles a :list) {
//            Query query = session.createQuery("delete from Articles where articleId = :id ");
//            query.setParameter("id", a.getArticleId());
//            query.executeUpdate();
//            session.delete(a);
//        }
//        transaction.commit();
//
//        Query query1 = session.createQuery("delete Articles  where articleId = 30 ");
//
//        query1.executeUpdate();

//        for (Articles article : list) {
//            articlesRepository.deleteByArticleId(article.getArticleId());
//        }
//        articlesRepository.deleteByArticleId(new Long(20));
//        usersRepository.delete(users);
//        articlesRepository.deleteByArticleId(4);
    }


}
