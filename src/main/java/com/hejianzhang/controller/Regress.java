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
import com.hejianzhang.dao.regresscasesResultMapper;
import com.hejianzhang.model.regresscasesResult;
import com.hejianzhang.DTO.regressceneDTO;
import com.hejianzhang.model.regresscases;
import com.hejianzhang.dao.regressMapper;
import com.hejianzhang.model.regress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.*;


/**
 * Created by Administrator on 2017-02-27.
 */
@Controller
@RequestMapping("/regress")
public class Regress {
    @Autowired
    private regressMapper regress;
    @Autowired
    private regressceneMapper regresscene;
    @Autowired
    private regresscasesMapper regresscases;
    @Autowired
    private  regresscasesResultMapper  regresscasesResult;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectAll", produces = "text/html;charset=UTF-8")
    public String selectAll() throws Exception{
        List<regress> allsecne = regress.selectAll();
        JSONArray regressArray = new JSONArray();
        for (regress s1 : allsecne) {
            JSONObject m = new JSONObject();
            m.put("id", s1.getId());
            m.put("Version", s1.getVersion());
            m.put("Desc", s1.getDes());
            m.put("time", s1.getCreatetime());
            List<regressceneDTO> regressceneDTOS= new ArrayList<regressceneDTO>();
            List<regresscene> regresscenes=regresscene.selectByregressid(s1.getId());
//            List<regresscasesResult>  regresscasesResults=regresscasesResult.selectByRegressId(new Integer(regresscenes.get(0).getId()));
//            Set<Integer> set= new HashSet<Integer>();
//            for(regresscasesResult re: regresscasesResults){
//                set.add(re.getBuildversion());
//            }
            List<Integer> set = regress.buildVersionByregressid(s1.getId());
            for(regresscene re:regresscenes){
                regressceneDTO regressceneDTO = new regressceneDTO();
                List<regresscases>  regrecases= regresscases.selectbyRegressuiteid(re.getId());
                regressceneDTO.setCreateby(re.getCreateby());
                regressceneDTO.setEnv(re.getEnv());
                regressceneDTO.setId(re.getId());
                regressceneDTO.setRegressid(re.getRegressid());
                regressceneDTO.setTestsuite(re.getTestsuite());
                regressceneDTO.setTestsuitedesc(re.getTestsuitedesc());
//                BeanUtils.copyProperties(regressceneDTO, re);
                regressceneDTO.setTestsuitedata(regrecases);
                regressceneDTOS.add(regressceneDTO);
            }
            m.put("regresscenes", regressceneDTOS);
            m.put("buildVersion", set.toArray());
            regressArray.add(m);
        }
        JSONObject all = new JSONObject();
        all.put("data", regressArray);
        return all.toString();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "selectById", produces = "text/html;charset=UTF-8",method= RequestMethod.POST)
    public String selectById(@RequestBody String code) {
        regress s1 = regress.selectByPrimaryKey(Integer.parseInt(JSONObject.parseObject(code).get("id").toString()));
        JSONArray regressArray = new JSONArray();
            JSONObject m = new JSONObject();
            m.put("id", s1.getId());
            m.put("Version", s1.getVersion());
            m.put("Desc", s1.getDes());
            m.put("time", s1.getCreatetime());
            List<regressceneDTO> regressceneDTOS= new ArrayList<regressceneDTO>();
            List<regresscene> regresscenes=regresscene.selectByregressid(s1.getId());
//            List<regresscasesResult>  regresscasesResults=regresscasesResult.selectByRegressId(new Integer(regresscenes.get(0).getId()));
//            Set<Integer> set= new HashSet<Integer>();
//            for(regresscasesResult re: regresscasesResults){
//                set.add(re.getBuildversion());
//            }
            List<Integer> set = regress.buildVersionByregressid(s1.getId());
            for(regresscene re:regresscenes){
                regressceneDTO regressceneDTO = new regressceneDTO();
                List<regresscases>  regrecases= regresscases.selectbyRegressuiteid(re.getId());
                regressceneDTO.setCreateby(re.getCreateby());
                regressceneDTO.setEnv(re.getEnv());
                regressceneDTO.setId(re.getId());
                regressceneDTO.setRegressid(re.getRegressid());
                regressceneDTO.setTestsuite(re.getTestsuite());
                regressceneDTO.setTestsuitedesc(re.getTestsuitedesc());
                regressceneDTO.setTestsuitedata(regrecases);
                regressceneDTOS.add(regressceneDTO);
            m.put("regresscenes", regressceneDTOS);
            m.put("buildVersion", set.toArray());
        }
        regressArray.add(m);
        JSONObject all = new JSONObject();
        all.put("data", regressArray);
        return all.toString();
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "add", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String add(@RequestBody String code) {
        regress order = JSON.parseObject(code, regress.class);
        int m = regress.insert(order);
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
        m = regress.deleteByPrimaryKey(id);
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
        regress order = JSON.parseObject(code, regress.class);
        int m = regress.updateByPrimaryKey(order);
        if (m == 1) {
            return "200修改成功";
        } else {
            return "400修改失败";

        }
    }
}