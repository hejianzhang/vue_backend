package com.hejianzhang.controller;

/**
 * Created by hejianzhang on 2017/7/22.
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hejianzhang.dao.testcasesMapper;
import com.hejianzhang.dao.testsceneMapper;
import com.hejianzhang.dao.userMapper;
import com.hejianzhang.model.testcases;
import com.hejianzhang.model.testscene;
import com.hejianzhang.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Administrator on 2017-02-27.
 */
@Controller
@RequestMapping("/testscene")
public class Testscene {
    @Autowired
    private userMapper user;
    @Autowired
    private testcasesMapper testcases;
    @Autowired
    private testsceneMapper testscene;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectAll", produces = "text/html;charset=UTF-8")
    public String selectAll() {
        List<testscene> allsecne=testscene.selectAll();
        JSONArray testsceneArray = new JSONArray();
        for(testscene s1:allsecne){
            JSONObject m = new JSONObject();
            m.put("id", s1.getId());
            m.put("TestSuite", s1.getTestsuite());
            m.put("TestSuiteDesc", s1.getTestsuitedesc());
            m.put("TestSuiteData", s1.getTestsuitedata());
            testsceneArray.add(m);
        }
        JSONObject all = new JSONObject();
        all.put("name", "场景用例集合");
        all.put("spi", "tradespi");
        all.put("system", "NTSStock");
        all.put("index", "1");
        all.put("data",testsceneArray );
        return all.toString();

//        return "hello";
    }

//    @CrossOrigin(origins = "*", maxAge = 3600)
//    @ResponseBody
//    @RequestMapping(value = "selectAll", produces = "text/html;charset=UTF-8")
//    public String selectAll() {
//        JSONArray testcasesArray = new JSONArray();
//        List<testcases> allcases = testcases.selectAll();
//        for (testcases s1 : allcases) {
//            JSONObject i=new JSONObject();
//            JSONObject j=new JSONObject();
//            if(s1.getCaseinput().contains(":")) {
//                i = JSON.parseObject(s1.getCaseinput());
//            }
//            if(s1.getCaseexpectresult().contains(":")) {
//                j = JSON.parseObject(s1.getCaseexpectresult());
//            }
//            JSONObject m = new JSONObject();
//            m.put("id", s1.getId());
//            m.put("name", s1.getCasename());
//            m.put("desc", s1.getCasedesc());
//            m.put("input", i);
//            m.put("expectResult", j);
//            testcasesArray.add(m);
//
//        }
//        JSONObject all = new JSONObject();
//        all.put("password", "123456");
//        all.put("userID", "0301");
//        all.put("brokerID", "2016");
//        all.put("spi", "tradespi");
//        all.put("system", "NTSStock");
//        all.put("testdata", testcasesArray);
////        testcases t=new testcases();
////        t.setCasename("aa");
////        t.setCasedesc("23");
////        t.setCaseinput("555");
////        t.setCaseexpectresult("444");
////        int i=testcases.insert(t);
//        return all.toString();
//
////        return "hello";
//    }
//
@CrossOrigin(origins = "*", maxAge = 3600)
@ResponseBody
@RequestMapping(value = "add", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
public String add(@RequestBody String code) {
    int i = 1;
    int j = 1;
    testscene order = JSON.parseObject(code, testscene.class);
    int m = testscene.insert(order);
    if (m == 1) {
        return "200修改成功";
    } else {
        return "400修改失败";

    }

}
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "delete", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String delete(@RequestBody String code) {
        int m=0;
        int id=  Integer.parseInt(code.substring(0,code.length()-1));
        m = testscene.deleteByPrimaryKey(id);
        if (m == 1) {
            return "200删除场景成功";
        } else {
            return "400删除场景失败";

        }
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String update(@RequestBody String code) {
        testscene order = JSON.parseObject(code, testscene.class);
        int m = testscene.updateByPrimaryKey(order);
        if (m == 1) {
            return "200修改成功";
        } else {
            return "400修改失败";

        }
    }
}