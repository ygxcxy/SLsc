package cn.scj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {


    @RequestMapping("/")
    public String index(){
        return "forward:user/to/login";
    }
}
