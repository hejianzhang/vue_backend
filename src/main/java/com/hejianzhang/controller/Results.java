package com.hejianzhang.controller;

/**
 * Created by hejianzhang on 2017/7/24.
 */
import com.hejianzhang.thread.MessageHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hejianzhang.dao.testcasesMapper;
import com.hejianzhang.dao.testsceneMapper;
import com.hejianzhang.dao.userMapper;
import com.hejianzhang.dao.exResultsMapper;
import com.hejianzhang.model.exResults;
import com.hejianzhang.model.testcases;
import com.hejianzhang.model.testscene;
import com.hejianzhang.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/exResults")
public class Results {
    private final static ThreadLocal threadLocal = new ThreadLocal();
    @Autowired
    private testcasesMapper testcases;
    @Autowired
    private testsceneMapper testscene;
    @Autowired
    private exResultsMapper  exResultsMapper;
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "add", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public void add(@RequestBody String code) {
        String ids=(String)JSONObject.parseObject(code).get("ids");
        String time=(String)JSONObject.parseObject(code).get("time");
        String[] id=ids.split(",");
        Map<String,testcases> ss=new LinkedHashMap<String,testcases>();
        for(int i=0;i<id.length;i++){
            JSONObject m=new JSONObject();
            com.hejianzhang.model.exResults e=new com.hejianzhang.model.exResults();
            e.setUpdatetime(time);
            e.setSceneid(id[i]);
            testscene ts=testscene.selectByPrimaryKey(Integer.parseInt(id[i]));
            String[] testdata=ts.getTestsuitedata().split(",");
            Long[] s=new Long[testdata.length];
            for(int j=0;j<testdata.length;j++){
                s[j]=(long)(Integer.parseInt(testdata[j]));
            }
            List<testcases> list=testcases.selectMulIds(s);
            List<testcases> selectCases=new ArrayList<testcases>();
            for(int cc=0;cc<testdata.length;cc++){
                for(int j=0;j<list.size();j++) {
                    if (s[cc].toString().equals(list.get(j).getId().toString())) {
                        selectCases.add(list.get(j));
                    }
                }
            }
            List<JSONObject> testcasesArray = new ArrayList<JSONObject>();

            int tt=0;
            for(testcases l:selectCases){
                JSONObject t=new JSONObject();
//                JSONObject j=new JSONObject();
//                j.put("result","no running");
//                j.put("message","null");
                t.put("name",l.getCasedesc());
                t.put("testdata","no running");
                testcasesArray.add(t);
                String zz=time+','+ts.getId()+','+tt+','+l.getId();

                tt++;
                ss.put(zz,testcases.selectByPrimaryKey(l.getId()));

            }
            m.put("name",ts.getTestsuite());
            m.put("data",testcasesArray);
            e.setCasestatus(m.toJSONString());

            exResultsMapper.insert(e);
        }
       for(String s:ss.keySet()) {
           Map<String,testcases> ss1=new LinkedHashMap<String,testcases>();
           ss1.put(s,ss.get(s));
           try {
               Thread.sleep(1000);
           }catch(Exception e){
               e.printStackTrace();
           }
           MessageHandler handler = (MessageHandler)threadLocal.get();
           if(handler == null)
           {
               MessageHandler messageHandler=new MessageHandler();
               threadLocal.set(messageHandler);
               handler = (MessageHandler)threadLocal.get();
           }
           handler.handleMessage(ss1);
       }
//        messageHandler.init();



//        int m = testcases.insert(order);

    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "getAll", produces = "text/html;charset=UTF-8")
    public String  getAll() {
          List<exResults> list=exResultsMapper.selectAll();

        Map<String,List<JSONObject> > map=new LinkedHashMap<String, List<JSONObject>>();
        for(exResults l: list){
            if(map.get(l.getUpdatetime())==null){
                JSONObject j=new JSONObject();
                j.put(l.getSceneid(),JSON.parseObject(l.getCasestatus()));
                List<JSONObject> array=new ArrayList<JSONObject>();
                array.add(j);
                map.put(l.getUpdatetime(),array);
            }else{
                JSONObject j=new JSONObject();
                j.put(l.getSceneid(),JSON.parseObject(l.getCasestatus()));
                List<JSONObject> array=map.get(l.getUpdatetime());
                array.add(j);
                map.put(l.getUpdatetime(),array);
            }
        }
        JSONArray jsonArray=new JSONArray();

        for (String  key : map.keySet()) {
            int total=0;
            int fail=0;
            int success=0;
            JSONObject j=new JSONObject();

            for(int tt=map.get(key).size()-1;tt>=0;tt--){
                JSONObject js=map.get(key).get(tt);
//            }
//            for( JSONObject js:map.get(key)){
                int mintotal=0;
                int minfail=0;
                int minsuccess=0;
                for(String i:js.keySet()){
                    if(JSON.toJSONString(js.get(i)).indexOf("testdata") != -1){
                        String[] str1=JSON.toJSONString(js.get(i)).split("testdata");
                        total=total+str1.length-1;
                        mintotal=mintotal+str1.length-1;
                    }
                    if(JSON.toJSONString(js.get(i)).indexOf("success") != -1){
                        String[] str2=JSON.toJSONString(js.get(i)).split("success");
                        success=success+str2.length-1;
                        minsuccess=minsuccess+str2.length-1;
                    }
                    JSONObject mm=(JSONObject)(js.get(i));
                    for(String s:mm.keySet()){
                        if(s.equals("name")){
                            mm.put("name",mm.get("name")+"  总数  "+mintotal+"    失败  "+(mintotal-minsuccess));
                        }
                    }

                }
            }
            fail=total-success;
            List<JSONObject> list11=new ArrayList<JSONObject>();
            for(int tt=map.get(key).size()-1;tt>=0;tt--) {
                list11.add(map.get(key).get(tt));
            }
            j.put(key+"          总数  "+total+"     失败  "+fail,list11);
            jsonArray.add(j);
        }
//        List<JSONObject> array=new ArrayList<JSONObject>();
//           for(exResults l: list){
//               if(c.get(l.getUpdatetime())==null){
//                   JSONObject j=new JSONObject();
//                   j.put(l.getSceneid(),JSON.parseObject(l.getCasestatus()));
//
//               }
//               JSONObject j=new JSONObject();
//               j.put(l.getSceneid(),JSON.parseObject(l.getCasestatus()));
//               array.add(j);
//           }
        JSONArray jsonArray1=new JSONArray();
        int length=jsonArray.size();
        if(length>10){
            length=10;
        }
        for(int i=0;i<length;i++){
            jsonArray1.add(jsonArray.get(i));
        }
        return jsonArray1.toString();

    }
}
