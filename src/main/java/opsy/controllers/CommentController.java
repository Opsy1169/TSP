package opsy.controllers;

import opsy.data.ArticlesRepository;
import opsy.data.CategoriesRepository;
import opsy.data.CommentsRepository;
import opsy.data.UsersRepository;
import opsy.entities.*;
import opsy.util.UtilStuff;
import opsy.validators.ArticleValidator;
import opsy.validators.UserDTOValidator;
import opsy.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private CategoriesRepository categoriesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UtilStuff utilStuff;

//    @Autowired
//    UserDTOValidator userDTOValidator;
//
//    @Autowired
//    ArticleValidator articleValidator;

//    @Autowired
//    UserValidator userValidator;


    /**
     * Как выяснилось для нормальной работы этой беды нужно параметром в аннотацию
     * передать имя, под которым объект передается и принимается на/со страницы
     * Иначе он будет проверять все объекты, и падать, потому что метод саппорт в валидаторе
     * настроен только на один класс
     * Таким образом принимается соглашение относительно того, как должна именоваться сущность UserDTO
     * при передаче и приеме ее в качестве параметра на страницу
     */
//    @InitBinder("userdto")
//    private void initUserBinder(WebDataBinder binder) {
//        binder.setValidator( userDTOValidator);
//    }
//
//    @InitBinder("article")
//    private void initArticleBinder(WebDataBinder binder) {
//        binder.setValidator( articleValidator);
//    }



    private long editableArticleId;

    private Users autenticatedUser; //Записать сразу залогиненного пользователя сюда и обращаться сюда, а не каждый раз к базе
                                    //Контролить состояние переменной при логине/логауте


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

    private User getAuthUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }
    /**
     * Отрисовывает страницу с комментариями
     */
    @RequestMapping(value="/comments", method = RequestMethod.GET)
    public ModelAndView test(HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("comments");
        List<Comments> comments =  commentsRepository.findAll();
        comments.sort((Comments c1, Comments c2)-> {
            return -c1.getTime().compareTo(c2.getTime());
        });
        modelAndView.addObject("comments", comments);



        return modelAndView;
    }


    /**
     * Тестовый метод аджакса, раньше работал, но с того момента поменялась структура проекта
     * В принципе общая логика поменяться не должна
     * Можно использовать как примеры для будущих аджакс-запросов
     */
    @RequestMapping(value = "/addcomment", method = RequestMethod.POST)
    public @ResponseBody CommentDTO ajaxTest(@RequestParam String body, @RequestParam String user, @RequestParam String article){
        Comments comment = new Comments();
        comment.setArticle(Integer.valueOf(article));
        comment.setAuthorId(usersRepository.findByLogin(user));
        body = utilStuff.replaceTags(body);
        comment.setBody(body);
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setTime(timestamp);
        comment.setFormatTime(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(timestamp));
        comment.setDate(sqlDate);
        commentsRepository.save(comment);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody(body);
        commentDTO.setTime(comment.getFormatTime());
        return commentDTO;
    }



//    @RequestMapping(value = "/savearticle", method = RequestMethod.POST)
//    public ModelAndView saveArticle(@RequestParam String title, @RequestParam String body, @RequestParam String category){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User object = (User) authentication.getPrincipal();
//        Articles articles = new Articles();
//        String login = object.getUsername();
//        Users user = usersRepository.findByLogin(login);
//        articles.setTitle(title);
//        articles.setArticleBody(utilStuff.replaceTags(body));
//        articles.setCategories(categoriesRepository.findByCategory(category));
//        articles.setAuthor(user);
//        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
//        articles.setCategories(categoriesRepository.findByCategory(articles.getStringCategory()));
//        System.out.println(articles.getStringCategory());
//
//        articles.setPublishdate(sqlDate);
//        articles.setArticleBody(utilStuff.replaceTags(articles.getArticleBody()));
//        articlesRepository.save(articles);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:article" + articles.getArticleId());
//        return modelAndView;
//    }




    /**
     * Отрисовывает список статей
     */
    @RequestMapping(value="/articlelist", method = RequestMethod.GET)
    public ModelAndView articlesList(HttpServletResponse response) throws IOException {


        ModelAndView modelAndView = new ModelAndView("articles");
        List<Articles> articles = dao.getArticlesRepresentation();
        for (Articles article:  articles) {
            article.setStringCategory(categoriesRepository.findById(article.getCategories().getId()).getCategory());
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users activeUser = usersRepository.findByLogin(user.getUsername());
        modelAndView.addObject("user", activeUser);
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
        list.sort((Comments c1, Comments c2)-> {
            return -c1.getTime().compareTo(c2.getTime());
        });

        modelAndView.setViewName("fullarticle");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users activeUser = null;
        activeUser = usersRepository.findByLogin(user.getUsername());
        article.setStringCategory(categoriesRepository.findById(article.getCategories().getId()).getCategory());
        modelAndView.addObject("article", article);
        modelAndView.addObject("comments", list);
        modelAndView.addObject("user", activeUser);
        return modelAndView;
    }

    @RequestMapping(value="selectcat", method = RequestMethod.GET)
    public ModelAndView article(@RequestParam("cat") String cat) throws Exception {

        ModelAndView modelAndView = new ModelAndView("articles");
        List<Articles> articles = dao.getArticlesRepresentationByCat(categoriesRepository.findByCategory(cat));
//        for (Articles article:  articles) {
//            article.setStringCategory(categoriesRepository.findById(article.getCategories().getId()).getCategory());
//        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users activeUser = usersRepository.findByLogin(user.getUsername());
        modelAndView.addObject("user", activeUser);
        modelAndView.addObject("articles", articles);


        return modelAndView;
    }

    /**
     * Отрисовывает страницу логина
     * сам функционал авторизации лежит на спринге
     * Например, url /login и /logout зарезервированы и отрабаываются контейнером
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) throws IOException {

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            ModelAndView modelAndView = new ModelAndView("redirect:router");
            return modelAndView;
        }
        catch (Exception ex){
            ModelAndView modelAndView = new ModelAndView("Login");
            if(error != null){
                modelAndView.addObject("error", "Wrong username or password");
            }
            return modelAndView;
        }


    }

    /**
     * Отрисовывает страницу регистрации
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationRender(){
        ModelAndView modelAndView = new ModelAndView();
        UserDTO userDTO = new UserDTO();
        modelAndView.setViewName("registration");
        modelAndView.addObject("userdto", userDTO);

        return modelAndView;
    }


    /**
     * Обрабатывает пришедшую форму регистрации, выполняет базовую проверку
     * Проверку стоит логически вынести в какие-то методы валидации
     * Если требования соблюдены, то происходит запись пользователя в базу
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("userdto") @Validated UserDTO userDTO, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
            return modelAndView;
        }


        Users user = new Users();
        user.setLogin(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setIsadmin(false);
        user.setIsmoder(false);
        //usersRepository.save(user);
        usersRepository.rawSave(user.getLogin(),  user.getPassword(), user.getIsmoder(), user.getIsadmin());
        modelAndView.setViewName("Login");
        return modelAndView;
    }

    @RequestMapping(value = "/checkexistance", method = RequestMethod.POST)
    public @ResponseBody Boolean checkValidLogin(@RequestParam String login){
        Users users = usersRepository.findByLogin(login);
        boolean exists = false;
        if(users != null)
            exists = true;
        return exists;
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

        User currentuser =  getAuthUser();
        modelAndView.addObject("authuser", currentuser);
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
//    @RequestMapping(value ="/createarticle", method = RequestMethod.GET)
//    public ModelAndView renderCreateArticle(ModelAndView mnv){
//        if(!mnv.getModel().containsKey("article")){
//            mnv.addObject("article", new Articles());
//            mnv.addObject("isNew", true);
//            List<Categories> categories = (List<Categories>)categoriesRepository.findAll();
//            List<String> onlyNames = new ArrayList<>();
//            categories.forEach((category) -> onlyNames.add(category.getCategory()));
//            mnv.addObject("categories", onlyNames);
//
//        }
//        //ModelAndView modelAndView = new ModelAndView();
//
//        //modelAndView.setViewName("createarticle");
//        //mnv.addObject(mnv.getModel().get("article"));
//        mnv.setViewName("createarticle");
//        //modelAndView.addObject("isNew", true);
//
//        //modelAndView.addObject("categories", onlyNames);
//
//        return mnv;
//    }


    /**
     * попробовать сделать пост-редирект-гет через model, пока ниче не получается
     */
//    @RequestMapping(value ="/createarticle1", method = RequestMethod.POST)
//    public ModelAndView createArticle(@ModelAttribute("article") @Validated Articles articles, BindingResult bindingResult,
//                                      RedirectAttributes redirectAttributes){
//        ModelAndView modelAndView = new ModelAndView();
//        if(bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingRsult.article", bindingResult);
//            redirectAttributes.addFlashAttribute("article", articles);
//            modelAndView.setViewName("redirect:createarticle");
//            return modelAndView;
//        }
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User object = (User) authentication.getPrincipal();
//        String login = object.getUsername();
//        Users user = usersRepository.findByLogin(login);
//        articles.setAuthor(user);
//        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
//        articles.setCategories(categoriesRepository.findByCategory(articles.getStringCategory()));
//        System.out.println(articles.getStringCategory());
//
//        articles.setPublishdate(sqlDate);
//        articles.setArticleBody(utilStuff.replaceTags(articles.getArticleBody()));
//        articlesRepository.save(articles);
//
//
//        modelAndView.setViewName("redirect:article" + articles.getArticleId());
//
//        return modelAndView;
//    }

    @RequestMapping(value ="/createarticle", method = RequestMethod.GET)
    public String renderCreateArticle(Model model){
        if(!model.containsAttribute("article")){
            model.addAttribute("article", new Articles());
            model.addAttribute("isNew", true);


        }
        List<Categories> categories = (List<Categories>)categoriesRepository.findAll();
        List<String> onlyNames = new ArrayList<>();
        categories.forEach((category) -> onlyNames.add(category.getCategory()));
        model.addAttribute("categories", onlyNames);
        //ModelAndView modelAndView = new ModelAndView();

        //modelAndView.setViewName("createarticle");
        //mnv.addObject(mnv.getModel().get("article"));
        return "createarticle";
        //modelAndView.addObject("isNew", true);

        //modelAndView.addObject("categories", onlyNames);


    }

    @RequestMapping(value ="/createarticle1", method = RequestMethod.POST)
    public String createArticle(@ModelAttribute("article") @Validated Articles articles, BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes, Model model){
//        if(bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.article", bindingResult);
//            redirectAttributes.addFlashAttribute("article", articles);
//            redirectAttributes.addFlashAttribute("isNew", true);
//            List<Categories> categories = (List<Categories>)categoriesRepository.findAll();
//            List<String> onlyNames = new ArrayList<>();
//            categories.forEach((category) -> onlyNames.add(category.getCategory()));
//            redirectAttributes.addFlashAttribute("categories", onlyNames);
//            //modelAndView.setViewName("redirect:createarticle");
//            return "redirect:createarticle";
//        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User object = (User) authentication.getPrincipal();
        String login = object.getUsername();
        Users user = usersRepository.findByLogin(login);
        articles.setAuthor(user);
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        articles.setCategories(categoriesRepository.findByCategory(articles.getStringCategory()));
        System.out.println(articles.getStringCategory());

        articles.setPublishdate(sqlDate);
        articles.setArticleBody(utilStuff.replaceTags(articles.getArticleBody()));
        articlesRepository.save(articles);


        return "redirect:article" + articles.getArticleId();


    }

    @RequestMapping(value ="/editarticle{id}", method = RequestMethod.GET)
    public ModelAndView renderEditArticle(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Articles articles = articlesRepository.findByArticleId(id);
        if(!articles.getAuthor().getLogin().equals(user.getUsername())) {
            modelAndView.setViewName("redirect:forbidden");
            return modelAndView;
        }
        articles.setArticleBody(utilStuff.replaceBackToTags(articles.getArticleBody()));
        articles.setStringCategory(articles.getCategories().getCategory());
        List<Categories> categories = (List<Categories>)categoriesRepository.findAll();
        List<String> onlyNames = new ArrayList<>();
        categories.forEach((category) -> onlyNames.add(category.getCategory()));
        setEditableArticleId(id);
        modelAndView.setViewName("createarticle");
        modelAndView.addObject("article", articles);
        modelAndView.addObject("isNew", false);
        modelAndView.addObject("categories", onlyNames);
        return modelAndView;
    }

    @RequestMapping(value ="/editarticle", method = RequestMethod.POST)
    public ModelAndView editArticle(@ModelAttribute("article") @Validated Articles articles, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("createarticle");
            return modelAndView;
        }
        Articles dbarticle = articlesRepository.findByArticleId(getEditableArticleId());
        dbarticle.setArticleBody(utilStuff.replaceTags(articles.getArticleBody()));
        dbarticle.setTitle(articles.getTitle());
        //articlesRepository.updateArticle(articles.getArticleBody(), articles.getTitle(), articles.getArticleId());
        articlesRepository.save(dbarticle);


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

    @RequestMapping(value = "/deletearticle", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Object> deleteArticle(@RequestParam String id){

        long lid = Long.valueOf(id);
        Articles article = articlesRepository.findByArticleId(lid);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users activeUser = usersRepository.findByLogin(user.getUsername());
        if(article == null || (!activeUser.getIsadmin() && !activeUser.equals(article.getAuthor()))) {
//            ResponseEntity entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sheiiit");
        }
        dao.deleteArticle(lid);
        return ResponseEntity.status(HttpStatus.OK).body("success");

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
        ex.printStackTrace();
        return modelAndView;
    }

}
