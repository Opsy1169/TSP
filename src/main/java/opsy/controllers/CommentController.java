package opsy.controllers;

import opsy.data.ArticlesRepository;
import opsy.data.CommentsRepository;
import opsy.entities.Articles;
import opsy.entities.Comments;
import opsy.entities.DAO;
import opsy.entities.aComments;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView test(HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("home");
//        List<Comments> comments =  dao.getAllComments();
        List<aComments> comments =  commentsRepository.findAll();
        modelAndView.addObject("comments", comments);
//        Model model = (Model) modelAndView.getModel();
        String s = "heyhey another param";
        modelAndView.addObject("string", s);
//        model.addAttribute("string", s);

        return modelAndView;
    }

    @RequestMapping(value = "/ajaxTest", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody String ajaxTest(@RequestParam String body){
        Comments comment = new Comments();
        comment.setBody(body);
        dao.insertComment(comment);
        return body;
    }

    @RequestMapping(value="/articlelist", method = RequestMethod.GET)
    public ModelAndView articlesList(HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("articles");
        List<Articles> articles = dao.getArticlesRepresentation();
        modelAndView.addObject("articles", articles);

        return modelAndView;
    }

    @RequestMapping(value="/article{id}", method = RequestMethod.GET)
    public ModelAndView article(@PathVariable("id") int id, HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("fullarticle");
        Articles article = articlesRepository.findByArticleId(id);
        modelAndView.addObject("article", article);
        List<aComments> list = commentsRepository.findAllByArticle(id);
        modelAndView.addObject("comments", list);
        System.out.println("for commit");
        return modelAndView;
    }
}
