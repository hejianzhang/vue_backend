package com.hejianzhang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hejianzhang.dao.regresscasesMapper;
import com.hejianzhang.model.regresscases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/regresscases")
public class RegressCases {
    @Autowired
    private regresscasesMapper regresscases;
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "regressselectMulIds", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String selectMulIds(@RequestBody String code) {
        String s=(String)JSONObject.parseObject(code).get("ids");
        if(s.equals("")){
            return new JSONArray().toString();
        }
        int length=s.split(",").length;
        Long[] ids=new Long[length];
        for(int i=0;i<length;i++){
            ids[i]=(long)(Integer.parseInt(s.split(",")[i]));
        }
        List<regresscases> selectCase= regresscases.selectMulIds(ids);
        List<com.hejianzhang.model.regresscases> selectCases=new ArrayList<com.hejianzhang.model.regresscases>();
        for(int i=0;i<length;i++){
            for(int j=0;j<selectCase.size();j++) {
                if (ids[i].toString().equals(selectCase.get(j).getId().toString())) {
                    selectCases.add(selectCase.get(j));
                }
            }
        }
        JSONArray testcasesArray = new JSONArray();
        for (com.hejianzhang.model.regresscases s1 : selectCases) {
            JSONObject m = new JSONObject();
            m.put("id", s1.getId());
            m.put("name", s1.getCasename());
            m.put("desc", s1.getCasedesc());
            m.put("url", s1.getUrl());
            m.put("method",s1.getMethod());
            m.put("input", s1.getCaseinput());
            m.put("expectResult", s1.getCaseexpectresult());
            testcasesArray.add(m);

        }
        return testcasesArray.toString();

    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectAll", produces = "text/html;charset=UTF-8")
    public String selectAll() {
        JSONArray testcasesArray = new JSONArray();
        List<regresscases> allcases = regresscases.selectAll();
        for (com.hejianzhang.model.regresscases s1 : allcases) {
            JSONObject m = new JSONObject();
            m.put("id", s1.getId());
            m.put("name", s1.getCasename());
            m.put("desc", s1.getCasedesc());
            m.put("method",s1.getMethod());
            m.put("url", s1.getUrl());
            m.put("input", s1.getCaseinput());
            m.put("expectResult", s1.getCaseexpectresult());
            testcasesArray.add(m);

        }
        JSONObject all = new JSONObject();
        all.put("testdata", testcasesArray);
        return all.toString();

    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectbyId", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public void selectbyId(@RequestBody String code) {
        regresscases order = JSON.parseObject(code,regresscases.class);
        if(regresscases.selectByPrimaryKey(order.getId())==null){
            regresscases.insert(order);
        }else{
            regresscases.updateByPrimaryKey(order);
        }


    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "add", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public void add(@RequestBody String code) {
        regresscases order = JSON.parseObject(code,regresscases.class);
        if(regresscases.selectByPrimaryKey(order.getId())==null){
            regresscases.insert(order);
        }else{
            regresscases.updateByPrimaryKey(order);
        }


    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "delete", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String delete(@RequestBody String code) {
        int m=0;
        int id=  Integer.parseInt(code.substring(0,code.length()-1));
        m = regresscases.deleteByPrimaryKey(id);
        if (m == 1) {
            return "200É¾³ý³É¹¦";
        } else {
            return "400É¾³ýÊ§°Ü";

        }
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String update(@RequestBody String code) {
       regresscases order = JSON.parseObject(code, regresscases.class);
        int m = regresscases.updateByPrimaryKey(order);
        if (m == 1) {
            return "200ÐÞ¸Ä³É¹¦";
        } else {
            return "400ÐÞ¸ÄÊ§°Ü";

        }
    }
}
