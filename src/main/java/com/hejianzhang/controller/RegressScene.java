package com.hejianzhang.controller;

/**
 * Created by hejianzhang on 2017/7/22.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hejianzhang.dao.regressceneMapper;
import com.hejianzhang.model.regresscene;
import com.hejianzhang.dao.regresscasesMapper;
import com.hejianzhang.dao.testsceneMapper;
import com.hejianzhang.dao.testcasesMapper;
import com.hejianzhang.model.testscene;
import com.hejianzhang.model.testcases;
import com.hejianzhang.model.regresscases;
import com.hejianzhang.dao.regressMapper;
import com.hejianzhang.model.regress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2017-02-27.
 */
@Controller
@RequestMapping("/regressscene")
public class RegressScene {
    @Autowired
    private regressceneMapper regresscene;
    @Autowired
    private testsceneMapper testscene;
    @Autowired
    private testcasesMapper testcases;

    @Autowired
    private regressMapper regress;

    @Autowired
    private regresscasesMapper regresscases;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectAll", produces = "text/html;charset=UTF-8")
    public String selectAll() {
        List<regresscene> allsecne = regresscene.selectAll();
        JSONArray testsceneArray = new JSONArray();
        for (com.hejianzhang.model.regresscene s1 : allsecne) {
            JSONObject m = new JSONObject();
            m.put("id", s1.getId());
            m.put("TestSuite", s1.getTestsuite());
            m.put("TestSuiteDesc", s1.getTestsuitedesc());
            m.put("TestSuiteData", s1.getTestsuitedata());
            m.put("Env", s1.getEnv());
            m.put("Createby", s1.getCreateby());
            m.put("Regressid", s1.getRegressid());
            testsceneArray.add(m);
        }
        JSONObject all = new JSONObject();
        all.put("data", testsceneArray);
        return all.toString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "add", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String add(@RequestBody String code) {
        regresscene order = JSON.parseObject(code, regresscene.class);
        int m = regresscene.insert(order);
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
        int m = 0;
        int id = Integer.parseInt(code.substring(0, code.length() - 1));
        m = regresscene.deleteByPrimaryKey(id);
        if (m == 1) {
            return "200删除场景成功";
        } else {
            return "400删除场景失败";

        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "addlist", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String addlist(@RequestBody String code) {
        try {
            JSONObject jsonArray = JSONObject.parseObject(code);
            regress regress1 = new regress();
            regress1.setVersion(jsonArray.get("version").toString());
            regress1.setDes(jsonArray.get("desc").toString());
            regress1.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            Integer m = regress.insert(regress1);
            String[] ids = jsonArray.get("ids").toString().split(",");
            for (String id : ids) {
                testscene scene = testscene.selectByPrimaryKey(Integer.parseInt(id));
                regresscene regresscene1 = new regresscene();
                regresscene1.setCreateby(scene.getCreateby());
                regresscene1.setEnv(scene.getEnv());
                regresscene1.setTestsuite(scene.getTestsuite());
                regresscene1.setTestsuitedesc(scene.getTestsuitedesc());
                regresscene1.setRegressid(regress1.getId().toString());
                regresscene1.setTestsuitedata(scene.getTestsuitedata());
                regresscene.insert(regresscene1);
                String[] caseids = scene.getTestsuitedata().split(",");
                StringBuffer regressids = new StringBuffer();
                for (String caseid : caseids) {
                    testcases casedetail = testcases.selectByPrimaryKey(Integer.parseInt(caseid));
                    if (casedetail != null) {
                        regresscases regresscases1 = new regresscases();
                        regresscases1.setCasename(casedetail.getCasename());
                        regresscases1.setCasedesc(casedetail.getCasedesc());
                        regresscases1.setCaseexpectresult(casedetail.getCaseexpectresult());
                        regresscases1.setCaseinput(casedetail.getCaseinput());
                        regresscases1.setUrl(casedetail.getUrl());
                        regresscases1.setMethod(casedetail.getMethod());
                        regresscases1.setRegressuiteId(regresscene1.getId().toString());
                        regresscases.insert(regresscases1);
                        regressids.append(regresscases1.getId()).append(",");
                    }
                }
                regresscene1.setTestsuitedata(regressids.toString());
                regresscene.updateByPrimaryKey(regresscene1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "400修改失败";
        }
        return "200修改成功";
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectById", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String selectById(@RequestBody String code) {
        List<regresscene> regresscenedetail = regresscene.selectByregressid(Integer.parseInt(JSONObject.parseObject(code).get("id").toString()));
//        if(regresscenedetail!=null) {
//            JSONArray testsceneArray = new JSONArray();
//            JSONObject m = new JSONObject();
//            m.put("id", regresscenedetail.getId());
//            m.put("TestSuite", regresscenedetail.getTestsuite());
//            m.put("TestSuiteDesc", regresscenedetail.getTestsuitedesc());
//            m.put("TestSuiteData", regresscenedetail.getTestsuitedata());
//            m.put("Env", regresscenedetail.getEnv());
//            m.put("Createby", regresscenedetail.getCreateby());
//            m.put("Regressid", regresscenedetail.getRegressid());
//            testsceneArray.add(m);
//
//            JSONObject all = new JSONObject();
//            all.put("data", testsceneArray);
//            return all.toString();
//        }
        return new JSONObject().toString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String update(@RequestBody String code) {
        regresscene order = JSON.parseObject(code, regresscene.class);
        int m = regresscene.updateByPrimaryKey(order);
        if (m == 1) {
            return "200修改成功";
        } else {
            return "400修改失败";

        }
    }
}