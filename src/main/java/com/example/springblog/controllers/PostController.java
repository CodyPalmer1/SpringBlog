package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.models.User;
import com.example.springblog.repositories.PostRepository;
import com.example.springblog.repositories.UserRepository;
import com.example.springblog.Services.AdService;
import com.example.springblog.Services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private PostRepository postDao;

    private UserRepository userDao;

    private EmailService emailService;



    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model) {
        // Instantiate the list to hold all the Post objects
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("allPosts", allPosts);
        // return the template
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
//        Post singlePost = new Post(id, "First Post!", "This is the first time I've ever used Spring!");

        Post singlePost = postDao.getById(id);
        model.addAttribute("post", singlePost);
        return "posts/show";
    }

//    NOT USING FORM MODEL BINDING
//    @GetMapping("/posts/create")
//    public String showCreateForm() {
//        return "posts/create";
//    }

    //    USING FORM MODEL BINDING
    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

//    NOT USING FORM MODEL BINDING
//    @PostMapping("/posts/create")
//    public String submitPost(@RequestParam(name = "title")String title, @RequestParam(name = "description")String description) {
//        Post post = new Post();
//        post.setTitle(title);
//        post.setBody(description);
//        User user = userDao.getById(1L);
////        User user = userDao.findById(1L);
//        post.setUser(user);
//        postDao.save(post);
//        return "redirect:/posts";
//    }

    //    USING FORM MODEL BINDING
    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post){
        User user = userDao.getById(1L);
//        User user = userDao.getReferenceById(1L);
        post.setUser(user);
        postDao.save(post);

        emailService.prepareAndSend(post, "New Post Created!", "A new post has been created! Here is the title of your new post! Title: " + post.getTitle());

        return "redirect:/posts";
    }


    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getReferenceById(id));
        return "posts/editPost";
    }

    @PostMapping("/posts/edit")
    public String editPost(@ModelAttribute Post post){
        System.out.println(post.getId());
        User user = userDao.getById(1L);
        post.setUser(user);
//        post.setId(id);
        postDao.save(post);
        return "redirect:/posts";
    }
}