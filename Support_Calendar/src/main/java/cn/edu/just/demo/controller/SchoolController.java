package cn.edu.just.demo.controller;

import cn.edu.just.demo.model.School;
import cn.edu.just.demo.service.CommonService;
import cn.edu.just.demo.service.SchoolService;
import cn.edu.just.demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SchoolController {


    @Autowired
    private SchoolService schoolService;


    @RequestMapping("/getSchoolName")
    public String getSchoolName(HttpServletRequest request){
        String list=schoolService.getSchoolName(request);
        return list;
    }
    @RequestMapping("/getSchool")
    public String getSchool(HttpServletRequest request){
        String list=schoolService.getSchool(request);
        return list;
    }
    @RequestMapping("/insertSchool")
    public String insertSchool(HttpServletRequest request,String data){
        System.out.println("---"+request+data);
        com.alibaba.fastjson.JSONObject jsonObject=(com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(data);
        School school=new School(null,jsonObject.getString("name"),jsonObject.getString("school_alias"));
        String result=schoolService.insertSchool(request, school);
        return result;
    }

    @RequestMapping("/updateSchool")
    public int updateSchool(HttpServletRequest request,String data){
        System.out.println("---"+request+data);
        com.alibaba.fastjson.JSONObject jsonObject=(com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(data);
        School school=new School(jsonObject.getInteger("id"),jsonObject.getString("name"),jsonObject.getString("school_alias"));
        schoolService.updateSchool(request,school);
        return 1;
    }
    @RequestMapping("/deleteSchool")
    public int deleteSchool(HttpServletRequest request,Integer school_id){
        System.out.println("---"+request+school_id);
        schoolService.deleteSchool(request,school_id);
        return 0;
    }
}
