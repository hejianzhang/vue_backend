package com.hejianzhang.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.hejianzhang.model.env;
import com.hejianzhang.dao.envMapper;
import java.util.List;

@Controller
@RequestMapping("/env")
public class Env {
    @Autowired
    public envMapper env;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "add", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String addeditEnv(@RequestBody String request){
        env order = JSON.parseObject(request, env.class);
        int m=0;
        if(order.getId()!=null){
            m = env.updateByPrimaryKey(order);

        }else{
            m = env.insert(order);

        }
        if(m==1){
            return "200";
        }else{
            return "400";
        }

    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "all", produces = "text/html;charset=UTF-8")
    public String all(){

        List<env> envs = env.selectAll();
        JSONArray Array = new JSONArray();
        for(env e: envs){
            JSONObject m = new JSONObject();
            m.put("id",e.getId());
            m.put("envname",e.getEnvname());
            m.put("tag",e.getTag());
            m.put("envdesc",e.getEnvdesc());
            m.put("param",e.getParam());
            m.put("ip",e.getIp());
            m.put("envdomain",e.getEnvdomain());
            Array.add(m);
        }

        JSONObject all = new JSONObject();
        all.put("data", Array);
        return all.toString();
    }


}
