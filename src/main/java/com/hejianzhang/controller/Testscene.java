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
import com.hejianzhang.model.testscene;
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
            m.put("Env",s1.getEnv());
            m.put("Createby",s1.getCreateby());
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


@CrossOrigin(origins = "*", maxAge = 3600)
@ResponseBody
@RequestMapping(value = "add", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
public String add(@RequestBody String code) {
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