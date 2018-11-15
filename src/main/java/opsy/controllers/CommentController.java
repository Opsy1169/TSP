package opsy.controllers;

import opsy.data.ArticlesRepository;
import opsy.data.CommentsRepository;
import opsy.data.UsersRepository;
import opsy.entities.*;
import opsy.util.UtilStuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private DAO dao;

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UtilStuff utilStuff;

    private long editableArticleId;


    /**
     * Какой-то тестовый метод, вроде, тестил энкодер
     */
    @RequestMapping(value = "/addUser")
    public void add(){
        Users users = new Users();
        users.setLogin("admin");
        users.setPassword(bCryptPasswordEncoder.encode("password"));
        usersRepository.save(users);
    }


    /**
     * Отрисовывает страницу с комментариями
     */
    @RequestMapping(value="/comments", method = RequestMethod.GET)
    public ModelAndView test(HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("comments");
//        List<shit> comments =  dao.getAllComments();
        List<Comments> comments =  commentsRepository.findAll();
        modelAndView.addObject("comments", comments);
//        Model model = (Model) modelAndView.getModel();
        String s = "heyhey another param";
        modelAndView.addObject("string", s);
        System.out.println(bCryptPasswordEncoder.encode("1234"));
//        model.addAttribute("string", s);

        return modelAndView;
    }


    /**
     * Тестовый метод аджакса, раньше работал, но с того момента поменялась структура проекта
     * В принципе общая логика поменяться не должна
     * Можно использовать как примеры для будущих аджакс-запросов
     */
    @RequestMapping(value = "/addcomment", method = RequestMethod.POST, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody String ajaxTest(@RequestParam String body, @RequestParam String user, @RequestParam String article){
        Comments comment = new Comments();
        comment.setArticle(Integer.valueOf(article));
        comment.setAuthorId(usersRepository.findByLogin(user));
        comment.setBody(body);
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        comment.setDate(sqlDate);
        commentsRepository.save(comment);

        return sqlDate.toString();
    }


    /**
     * Отрисовывает список статей
     */
    @RequestMapping(value="/articlelist", method = RequestMethod.GET)
    public ModelAndView articlesList(HttpServletResponse response) throws IOException {


        ModelAndView modelAndView = new ModelAndView("articles");
        List<Articles> articles = dao.getArticlesRepresentation();
        modelAndView.addObject("articles", articles);

        return modelAndView;
    }


    /**
     * @param id айди статьи для открытия
     * Отрабатывает при нажатии на какую-то из статей
     */
    @RequestMapping(value="/article{id}", method = RequestMethod.GET)
    public ModelAndView article(@PathVariable("id") int id, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        Articles article = articlesRepository.findByArticleId(id);
        if(article == null){
            throw new Exception("No such article found");
        }
        List<Comments> list = commentsRepository.findAllByArticle(id);

        modelAndView.setViewName("fullarticle");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users activeUser = null;
        activeUser = usersRepository.findByLogin(user.getUsername());
        modelAndView.addObject("article", article);
        modelAndView.addObject("comments", list);
        modelAndView.addObject("user", activeUser);
        return modelAndView;
    }

    /**
     * Отрисовывает страницу логина
     * сам функционал авторизации лежит на спринге
     * Например, url /login и /logout зарезервированы и отрабаываются контейнером
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView login(HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("Login");

        return modelAndView;
    }

    /**
     * Отрисовывает страницу регистрации
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationRender(){
        ModelAndView modelAndView = new ModelAndView();
        UserDTO userDTO = new UserDTO();
        modelAndView.setViewName("registration");
        modelAndView.addObject("user", userDTO);

        return modelAndView;
    }


    /**
     * Обрабатывает пришедшую форму регистрации, выполняет базовую проверку
     * Проверку стоит логически вынести в какие-то методы валидации
     * Если требования соблюдены, то происходит запись пользователя в базу
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("user") UserDTO userDTO){
        ModelAndView modelAndView = new ModelAndView();
        if(usersRepository.findByLogin(userDTO.getUsername()) != null){
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        if(!userDTO.getPassword().equals(userDTO.getConfirmPass())) {
            modelAndView.setViewName("registration");
            return modelAndView;
        }

        Users user = new Users(userDTO.getUsername(), bCryptPasswordEncoder.encode(userDTO.getPassword()), false, false);
        usersRepository.save(user);
        modelAndView.setViewName("Login");
        return modelAndView;
    }

    /**
     * Метод, определяющий домашнюю страницу пользователя в зависимости от роли при авторизации
     * Пока больше черновик, нужно определиться с принципом образования url в связи с особенностями
     * реализации технологии предоставления доступа в спринге
     */
    @RequestMapping(value = "/router", method = RequestMethod.GET)
    public ModelAndView route(HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
//        dao.deleteUser(8);System.out.println(articlesRepository.findByArticleId(2));
        System.out.println(articlesRepository.findAllByOrderByPublishdateAsc().size());

        //System.out.println(dao.getUserById(new Long(8)));
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            modelAndView.setViewName("redirect:adminhome");
            return modelAndView;
        }
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            modelAndView.setViewName("redirect:usershome");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:restricted");
        return modelAndView;

    }

    /**
     * Отрисовывает страницу-заглушку при попытке получения доступа к админскому ресурсу
     */
    @RequestMapping(value = "/forbidden", method = RequestMethod.GET)
    public ModelAndView routeForbidden(HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")) && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            modelAndView.setViewName("redirect:restricted");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:restricted");
        return modelAndView;

    }

    @RequestMapping(value = "adminuserslist", method = RequestMethod.GET)
    public ModelAndView listOfUsers(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userslist");
        List<Users> users = usersRepository.findAll();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = "/userprofile{id}", method = RequestMethod.GET)
    public ModelAndView userProfile(@PathVariable("id") long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userprofile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Users users = usersRepository.findByUserId(id);
        if(users == null){
            throw new Exception("No such user found");
        }
        modelAndView.addObject("user", users);
        modelAndView.addObject("auth", user);
        List<Articles> articles = dao.getArticlesRepresentationByAuthor(users);
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @RequestMapping(value = "/yourprofile", method = RequestMethod.GET)
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Users users = usersRepository.findByLogin(user.getUsername());
        modelAndView.setViewName("redirect:userprofile"+ users.getUserId());
        return modelAndView;
    }

    //Чтобы отредактировать статью, нужно вместо новой статьи передать на форму ту самую статью, спринг сам все забиндит
    //Только, наверное, нужно будет обратно заменять экранированные символы на исходные
    @RequestMapping(value ="/createarticle", method = RequestMethod.GET)
    public ModelAndView renderCreateArticle(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createarticle");
        modelAndView.addObject("article", new Articles());
        return modelAndView;
    }

    @RequestMapping(value ="/createarticle", method = RequestMethod.POST)
    public ModelAndView createArticle(@ModelAttribute("article") Articles articles){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User object = (User) authentication.getPrincipal();
        String login = object.getUsername();
        Users user = usersRepository.findByLogin(login);
        articles.setAuthor(user);
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        articles.setPublishdate(sqlDate);
        articles.setArticleBody(utilStuff.replaceTags(articles.getArticleBody()));
        articlesRepository.save(articles);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:article" + articles.getArticleId());

        return modelAndView;
    }

    @RequestMapping(value ="/editarticle{id}", method = RequestMethod.GET)
    public ModelAndView renderEditArticle(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Articles articles = articlesRepository.findByArticleId(id);
        articles.setArticleBody(utilStuff.replaceBackToTags(articles.getArticleBody()));
        if(!articles.getAuthor().getLogin().equals(user.getUsername())) {
            modelAndView.setViewName("redirect:forbidden");
            return modelAndView;
        }
        setEditableArticleId(id);
        modelAndView.setViewName("editarticle");
        modelAndView.addObject("article", articles);
        return modelAndView;
    }

    @RequestMapping(value ="/editarticle", method = RequestMethod.POST)
    public ModelAndView editArticle(@ModelAttribute("article") Articles articles){
        Articles dbarticle = articlesRepository.findByArticleId(getEditableArticleId());
        dbarticle.setArticleBody(utilStuff.replaceTags(articles.getArticleBody()));
        dbarticle.setTitle(articles.getTitle());
        //articlesRepository.updateArticle(articles.getArticleBody(), articles.getTitle(), articles.getArticleId());
        articlesRepository.save(dbarticle);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:article" + dbarticle.getArticleId());

        return modelAndView;
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Object> deleteUser(@RequestParam String id){

        long lid = Long.valueOf(id);
        Users userForDelete = usersRepository.findByUserId(lid);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users activeUser = usersRepository.findByLogin(user.getUsername());
        if(userForDelete == null || !activeUser.getIsadmin())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        dao.deleteUser(lid);
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }

    public void setEditableArticleId(Long id){
        this.editableArticleId = id;
    }
    public long getEditableArticleId(){
        return editableArticleId;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView errorPage(Exception ex){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormsg", ex.getMessage());
        return modelAndView;
    }

}
