package opsy.controllers;

import opsy.data.ArticlesRepository;
import opsy.data.CommentsRepository;
import opsy.data.UsersRepository;
import opsy.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @RequestMapping(value = "/ajaxTest", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody String ajaxTest(@RequestParam String body){
        Comments comment = new Comments();
        comment.setBody(body);
        dao.insertComment(comment);
        return body;
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
    public ModelAndView article(@PathVariable("id") int id, HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("fullarticle");
        Articles article = articlesRepository.findByArticleId(id);
        modelAndView.addObject("article", article);
        List<Comments> list = commentsRepository.findAllByArticle(id);
        modelAndView.addObject("comments", list);
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
    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public ModelAndView registrationRender(){
        ModelAndView modelAndView = new ModelAndView();
        UserDTO userDTO = new UserDTO();
        modelAndView.addObject("user", userDTO);
        modelAndView.setViewName("registration");
        return modelAndView;
    }


    /**
     * Обрабатывает пришедшую форму регистрации, выполняет базовую проверку
     * Проверку стоит логически вынести в какие-то методы валидации
     * Если требования соблюдены, то происходит запись пользователя в базу
     */
    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
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
}
