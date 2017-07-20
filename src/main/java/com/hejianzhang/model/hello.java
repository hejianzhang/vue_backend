package com.hejianzhang.model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Administrator on 2017-02-27.
 */
@Controller
@RequestMapping("/mvc")
public class hello {
    @RequestMapping("hello")
    public String mytest(){

        return "hello";
    }

}
