package com.hejianzhang.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import com.hejianzhang.dao.userMapper;
import com.hejianzhang.dao.testcasesMapper;
import  com.alibaba.fastjson.*;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017-02-27.
 */
@Controller
@RequestMapping("/mvc")
public class hello {
    @Autowired
    private userMapper user;
    @Autowired
    private testcasesMapper testcases;
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value="hello",produces = "text/html;charset=UTF-8")
    public String mytest(){
        List<user> s= user.selectAll();
        testcases s1= testcases.selectByPrimaryKey(1);
        JSONObject i=JSON.parseObject(s1.getCaseinput());
        JSONObject j=JSON.parseObject(s1.getCaseexpectresult());
        JSONObject m=new JSONObject();
        m.put("id",s1.getId());
        m.put("name",s1.getCasename());
        m.put("desc",s1.getCasedesc());
        m.put("input",i);
        m.put("expectresult",j);
        System.out.println("aaaaaaaaaaaaaaaaaaaa"+s1.getCasename());
//        testcases t=new testcases();
//        t.setCasename("aa");
//        t.setCasedesc("23");
//        t.setCaseinput("555");
//        t.setCaseexpectresult("444");
//        int i=testcases.insert(t);
         return  m.toString();

//        return "hello";
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value="add",method= RequestMethod.POST)
    public void add(@RequestBody String code) {
       int i=1;
       int j=1;
        testcases order =  JSON.parseObject(code,testcases.class);
        int m=testcases.insert(order);

    }
}
