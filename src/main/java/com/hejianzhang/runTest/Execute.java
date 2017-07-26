package com.hejianzhang.runTest;

/**
 * Created by Administrator on 2017-07-25.
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hejianzhang.controller.SpringContextUtil;
import com.hejianzhang.dao.exResultsMapper;
import com.hejianzhang.model.exResults;
import com.hejianzhang.model.testcases;
import org.junit.runner.JUnitCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Execute {
    //@Autowired
    //private static com.hejianzhang.dao.exResultsMapper exResultsMapper;
    public static void main(String[] args) {
        run(JunitDemo.class,null,null);
    }

    public static void run(Class classes,String s, Map<String,testcases> map) {
        List<String> list=new ArrayList<String>();
            for(String key:map.keySet()){
                if(map.get(key).getCasename().equals(s)){
                    list.add(key);
                }
            }
            JUnitCore runner = new JUnitCore();
            ExecutionListener listener = new ExecutionListener();
            runner.addListener(listener);
            runner.run(classes);
            MyResultRecorder recorder = listener.recorder;
           for(int i=0;i<list.size();i++){
           String message= recorder.getList().get(i).getError_msg();
           String result= recorder.getList().get(i).getResult().toString();
           String[] filter=list.get(i).split(",");
           com.hejianzhang.dao.exResultsMapper maper =   SpringContextUtil.getBean("exResultsMapper");

            exResults results=maper.selectBytimeandSceneid(filter[0],filter[1]);
            results.getId();
             String casestatus= results.getCasestatus();
             JSONObject all=JSONObject.parseObject(casestatus);
            JSONArray jsonArray=(JSONArray) all.get("data");
            JSONObject jsonObject=(JSONObject)jsonArray.get(Integer.parseInt(filter[2]));
//            JSONObject jsonObject1=(JSONObject)jsonObject.get("testdata");
               String sedmessage;
               if(result.equals("true")){
                   sedmessage="success";
               }else{
                   sedmessage=message;
               }
               jsonObject.put("testdata",sedmessage);
               all.put("data",jsonArray);

           results.setCasestatus(JSONObject.toJSONString(all));
               maper.updateByPrimaryKey(results);
           }
            System.out.println(list);
            System.out.println(recorder);

        }
    }

