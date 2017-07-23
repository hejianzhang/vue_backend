package com.hejianzhang.controller;

/**
 * Created by hejianzhang on 2017/7/24.
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hejianzhang.dao.testcasesMapper;
import com.hejianzhang.dao.testsceneMapper;
import com.hejianzhang.dao.userMapper;
import com.hejianzhang.dao.exResultsMapper;
import com.hejianzhang.model.testcases;
import com.hejianzhang.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/exResults")
public class exResults {
    @Autowired
    private testcasesMapper testcases;
    @Autowired
    private testsceneMapper testscene;
    @Autowired
    private exResultsMapper  exResults;
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "add", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public void add(@RequestBody String code) {
        String s=(String)JSONObject.parseObject(code).get("ids");

//        int m = testcases.insert(order);

    }

}
