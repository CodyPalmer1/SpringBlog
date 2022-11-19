package com.example.springblog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class PostController {

    @RequestMapping(method= RequestMethod.GET, path= "/posts")
    @ResponseBody
    public String index(){
        return "posts index page";
    }

    @RequestMapping(method= RequestMethod.GET, path= "/posts/{id}")
    @ResponseBody
    public String view(){
        return "view an individual post";
    }

    @RequestMapping(method= RequestMethod.GET, path= "/posts/create")
    @ResponseBody
    public String create(){
        return "view the form for creating a post";
    }

    @RequestMapping(method= RequestMethod.POST, path= "/posts/create")
    @ResponseBody
    public String postCreate(){
        return "create a new post";
    }
}
