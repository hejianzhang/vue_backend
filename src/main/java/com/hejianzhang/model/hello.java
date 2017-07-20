package com.hejianzhang.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hejianzhang.dao.userMapper;


/**
 * Created by Administrator on 2017-02-27.
 */
@Controller
@RequestMapping("/mvc")
public class hello {
    @Autowired
    private userMapper user;
    @RequestMapping("hello")
    public String mytest(){
        user s= user.selectByPrimaryKey("123","234","456");
        return "hello";
    }

}
