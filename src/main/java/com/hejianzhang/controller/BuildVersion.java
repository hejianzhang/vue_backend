package com.hejianzhang.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.hejianzhang.model.buildVersion;
import com.hejianzhang.dao.buildVersionMapper;

@Controller
@RequestMapping("/build")
public class BuildVersion {

    @Autowired
    public buildVersionMapper buildVersion;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "getBuildNum", produces = "text/html;charset=UTF-8")
    public String getBuildNum(){

        buildVersion buildnum  = buildVersion.selectByPrimaryKey(new Integer(1));
        JSONObject all = new JSONObject();
        all.put("buildNum", buildnum.getBuildversion());
        return all.toString();
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "updateBuildNum", produces = "text/html;charset=UTF-8",method = RequestMethod.POST)
    public void updateBuildNum(@RequestBody String code){
        int id=  Integer.parseInt(code.substring(0,code.length()-1));
        buildVersion bv=new buildVersion();
        bv.setBuildversion(id);
        bv.setId(1);
        buildVersion.updateByPrimaryKey(bv);
    }

}
