package com.hejianzhang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hejianzhang.dao.testcasesMapper;
import com.hejianzhang.dao.userMapper;
import com.hejianzhang.model.testcases;
import com.hejianzhang.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017-02-27.
 */
@Controller
@RequestMapping("/mvc")
public class Testcase {
    @Autowired
    private userMapper user;
    @Autowired
    private testcasesMapper testcases;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "hello", produces = "text/html;charset=UTF-8")
    public String mytest() {
        List<user> s = user.selectAll();
        testcases s1 = testcases.selectByPrimaryKey(1);
        JSONObject i = JSON.parseObject(s1.getCaseinput());
        JSONObject j = JSON.parseObject(s1.getCaseexpectresult());
        JSONObject m = new JSONObject();
        m.put("id", s1.getId());
        m.put("name", s1.getCasename());
        m.put("desc", s1.getCasedesc());
        m.put("input", i);
        m.put("expectresult", j);
        System.out.println("aaaaaaaaaaaaaaaaaaaa" + s1.getCasename());
//        testcases t=new testcases();
//        t.setCasename("aa");
//        t.setCasedesc("23");
//        t.setCaseinput("555");
//        t.setCaseexpectresult("444");
//        int i=testcases.insert(t);
        return m.toString();

//        return "hello";
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectMulIds", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String selectMulIds(@RequestBody String code) {
//      String[] id=(String) (JSONObject.parseObject(code).get("ids"))
      String s=(String)JSONObject.parseObject(code).get("ids");
      if(s.equals("")){
          return null;
      }
      int length=s.split(",").length;
      Long[] ids=new Long[length];
      for(int i=0;i<length;i++){
          ids[i]=(long)(Integer.parseInt(s.split(",")[i]));
      }
      List<testcases> selectCase=testcases.selectMulIds(ids);
      List<testcases> selectCases=new ArrayList<testcases>();
      for(int i=0;i<length;i++){
          for(int j=0;j<selectCase.size();j++) {
              if (ids[i].toString().equals(selectCase.get(j).getId().toString())) {
                  selectCases.add(selectCase.get(j));
              }
          }
      }
        JSONArray testcasesArray = new JSONArray();
        for (testcases s1 : selectCases) {
            JSONObject i=new JSONObject();
            JSONObject j=new JSONObject();
            if(s1.getCaseinput().contains(":")) {
                i = JSON.parseObject(s1.getCaseinput());
            }
            if(s1.getCaseexpectresult().contains(":")) {
                j = JSON.parseObject(s1.getCaseexpectresult());
            }
            JSONObject m = new JSONObject();
            m.put("id", s1.getId());
            m.put("name", s1.getCasename());
            m.put("desc", s1.getCasedesc());
            m.put("input", i);
            m.put("expectResult", j);
            testcasesArray.add(m);

        }
        return testcasesArray.toString();
//        return "hello";
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectAll", produces = "text/html;charset=UTF-8")
    public String selectAll() {
        JSONArray testcasesArray = new JSONArray();
        List<testcases> allcases = testcases.selectAll();
        for (testcases s1 : allcases) {
            JSONObject i=new JSONObject();
            JSONObject j=new JSONObject();
            if(s1.getCaseinput().contains(":")) {
                i = JSON.parseObject(s1.getCaseinput());
            }
            if(s1.getCaseexpectresult().contains(":")) {
                j = JSON.parseObject(s1.getCaseexpectresult());
            }
            JSONObject m = new JSONObject();
            m.put("id", s1.getId());
            m.put("name", s1.getCasename());
            m.put("desc", s1.getCasedesc());
            m.put("input", i);
            m.put("expectResult", j);
            testcasesArray.add(m);

        }
        JSONObject all = new JSONObject();
        all.put("password", "123456");
        all.put("userID", "0301");
        all.put("brokerID", "2016");
        all.put("spi", "tradespi");
        all.put("system", "NTSStock");
        all.put("testdata", testcasesArray);
//        testcases t=new testcases();
//        t.setCasename("aa");
//        t.setCasedesc("23");
//        t.setCaseinput("555");
//        t.setCaseexpectresult("444");
//        int i=testcases.insert(t);
        return all.toString();

//        return "hello";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "add", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public void add(@RequestBody String code) {
        int i = 1;
        int j = 1;
        testcases order = JSON.parseObject(code, testcases.class);
        int m = testcases.insert(order);

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "delete", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String delete(@RequestBody String code) {
     int m=0;
      int id=  Integer.parseInt(code.substring(0,code.length()-1));
        m = testcases.deleteByPrimaryKey(id);
        if (m == 1) {
            return "200删除成功";
        } else {
            return "400删除失败";

        }
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String update(@RequestBody String code) {
        testcases order = JSON.parseObject(code, testcases.class);
        int m = testcases.updateByPrimaryKey(order);
        if (m == 1) {
            return "200修改成功";
        } else {
            return "400修改失败";

        }
    }
}
