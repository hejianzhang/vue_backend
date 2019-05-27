package com.hejianzhang.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hejianzhang.model.*;
import com.hejianzhang.thread.CaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.hejianzhang.dao.regresscasesResultMapper;
import com.hejianzhang.DTO.regressceneDTO;
import com.hejianzhang.DTO.zeroUnitDTO;
import com.hejianzhang.dao.envMapper;
import com.hejianzhang.dao.buildVersionMapper;

import java.text.SimpleDateFormat;
import com.hejianzhang.DTO.processDetailDTO;
import com.hejianzhang.dao.regressMapper;
import com.hejianzhang.model.regresscene;
import com.hejianzhang.dao.regressceneMapper;
import java.util.*;
import java.util.LinkedHashSet;
@Controller
@RequestMapping("/testCaseResult")
public class TestCaseResult {
    private final static ThreadLocal threadLocal = new ThreadLocal();
    @Autowired
    public regresscasesResultMapper regresscasesResult;
    @Autowired
    public buildVersionMapper  buildVersion;

    @Autowired
    public  envMapper environment;

    @Autowired
    public  regressMapper regress;

    @Autowired
    public  regressceneMapper regresscene;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "excuteCases", produces = "text/html;charset=UTF-8",method = RequestMethod.POST)
    public void excuteCases(@RequestBody String code){
        CaseHandler handler = (CaseHandler) threadLocal.get();
        if(handler == null)
        {
            CaseHandler messageHandler=new CaseHandler();
            threadLocal.set(messageHandler);
            handler = (CaseHandler) threadLocal.get();
        }
        JSONObject jsonArray = JSONObject.parseObject(code);

        List<regressceneDTO> regressceneDTOs = JSON.parseArray(jsonArray.get("regresscenes").toString(),regressceneDTO.class);
        buildVersion bv = buildVersion.selectByPrimaryKey(1);
        Integer buildnum = bv.getBuildversion();
        for(regressceneDTO regressceneDTO:regressceneDTOs){
            zeroUnitDTO zeroUnitDTO = new zeroUnitDTO();
            Map<String,String> param = new HashMap<>();
            List<regresscases> cases = regressceneDTO.getTestsuitedata();
            env e =environment.selectByName(regressceneDTO.getEnv());
            JSONObject jsonObj = JSONObject.parseObject(e.getParam());
            Set<String> keys = jsonObj.keySet();
            for(String key:keys){
                param.put(key,jsonObj.get(key).toString());
            }
            zeroUnitDTO.setEnvMap(param);
            zeroUnitDTO.setSceneName(regressceneDTO.getTestsuitedesc());
            List<regresscasesResult> regresscasesResults = new ArrayList<regresscasesResult>();
            for(regresscases re:cases){
                regresscasesResult casesResult = new regresscasesResult();
                casesResult.setBuildversion(buildnum);
                casesResult.setCasedesc(re.getCasedesc());
                casesResult.setCaseexpectresult(re.getCaseexpectresult());
                casesResult.setCaseinput(re.getCaseinput());
                casesResult.setMethod(re.getMethod());
                casesResult.setUrl(re.getUrl());
                casesResult.setRegressuiteid(re.getRegressuiteId());
                casesResult.setCasename(re.getCasename());
                casesResult.setResult("no running");
                casesResult.setRegressuiteid(re.getRegressuiteId());
                casesResult.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                regresscasesResult.insert(casesResult);
                regresscasesResults.add(casesResult);
            }
            zeroUnitDTO.setRecaseResults(regresscasesResults);
            handler.handleMessage(zeroUnitDTO);

        }

    }




    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectResult", produces = "text/html;charset=UTF-8",method = RequestMethod.POST)
    public String selectResult(@RequestBody String code) {
        List<regresscasesResult> res = regresscasesResult.selectBybuildVersion(Integer.parseInt(JSONObject.parseObject(code).get("id").toString()));
        JSONArray regressArray = new JSONArray();
        int totaltime= res.size();;
        int failtimes=0;
        for (regresscasesResult s1 : res) {
            JSONObject m = new JSONObject();
            m.put("updateTime",s1.getUpdatetime());
            m.put("result",s1.getResult());
            if(!s1.getResult().equals("success")){
                failtimes++;
            }
            regressArray.add(m);
        }
        JSONObject all = new JSONObject();
        all.put("data", regressArray);
        all.put("totaltime", totaltime);
        all.put("failtimes", failtimes);
        return all.toString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "showResult", produces = "text/html;charset=UTF-8",method = RequestMethod.POST)
    public String showResult(@RequestBody String code) {
        List<processDetailDTO> res = regresscasesResult.showDetail(Integer.parseInt(JSONObject.parseObject(code).get("id").toString()));
        int reslength = res.size();
        int i=0;
        JSONArray  jsonArray = new JSONArray();
        LinkedHashSet<String> key = new LinkedHashSet<>();
        Map<String,List<String>> map= new HashMap<>();
        Map<String,String> resultmap= new HashMap<>();
        for(processDetailDTO dt:res){
            key.add(dt.testsuite);
            if(map.get(dt.testsuite)!=null){
                map.get(dt.testsuite).add(dt.result);
            }else{
                List<String> list= new ArrayList<>();
                list.add(dt.result);
                map.put(dt.testsuite,list);
            }
        }
        for(String keyword:map.keySet()){
            String flag="";
           for(String s:map.get(keyword)){
               if(s.contains("success")){
                   flag= "success";

               }else if(s.contains("Assertion")){
                   flag= "fail";
                   break;
               }else{
                   flag= "processing";
               }
           }
            resultmap.put(keyword,flag);
        }
        for(String s: key){
            JSONObject m = new JSONObject();
            m.put("key",s);
            m.put("result",resultmap.get(s));
            JSONArray js= new JSONArray();
            for(String casereulst:map.get(s)){
                JSONObject caseob = new JSONObject();
                caseob.put("casereult",casereulst);
                caseob.put("casename",res.get(i).getCasename());
                caseob.put("updatetime",res.get(i).getUpdateTime());
                i++;
                js.add(caseob);
            }
            m.put("case",js);
            jsonArray.add(m);
        }
        JSONObject all = new JSONObject();
        all.put("data", jsonArray);



//        JSONArray regressArray = new JSONArray();
//        int totaltime= res.size();;
//        int failtimes=0;
//        for (regresscasesResult s1 : res) {
//            JSONObject m = new JSONObject();
//            m.put("updateTime",s1.getUpdatetime());
//            m.put("result",s1.getResult());
//            if(!s1.getResult().equals("success")){
//                failtimes++;
//            }
//            regressArray.add(m);
//        }
//        JSONObject all = new JSONObject();
//        all.put("data", regressArray);
//        all.put("totaltime", totaltime);
//        all.put("failtimes", failtimes);
        return all.toString();
    }

}
