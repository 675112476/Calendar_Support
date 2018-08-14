package cn.edu.just.demo.controller;

import cn.edu.just.demo.model.Oldstudent;
import cn.edu.just.demo.service.CalendarService;
import cn.edu.just.demo.service.OldstudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OldstudentController {


    @Autowired
    private OldstudentService oldstudentService;
    @RequestMapping("/getOldstudentByDate")
    public String getCalendar(HttpServletRequest request,String date){
        return oldstudentService.getSchoolByDate(request,date);
    }
    @RequestMapping("/getOldstudent")
    public String getWorker(HttpServletRequest request){
        String list=oldstudentService.getOldstudent(request);
        return  list;
    }

    @RequestMapping("/insertOldstudent")
    public String insertWorker(HttpServletRequest request,String data){
        System.out.println("---"+request+data);
        com.alibaba.fastjson.JSONObject jsonObject=(com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(data);
        Oldstudent oldstudent=new Oldstudent(null,jsonObject.getString("date"),jsonObject.getString("school"));
        String result=oldstudentService.insertOldstudent(request, oldstudent);
        return result;
    }

    @RequestMapping("/updateOldstudent")
    public int updateWorker(HttpServletRequest request,String data){
        System.out.println("---"+request+data);
        com.alibaba.fastjson.JSONObject jsonObject=(com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(data);
        Oldstudent oldstudent=new Oldstudent(jsonObject.getInteger("id"),jsonObject.getString("date"),jsonObject.getString("school"));
        oldstudentService.updateOldstudent(request,oldstudent);
        return 1;
    }
    @RequestMapping("/deleteOldstudent")
    public int deleteWorker(HttpServletRequest request,Integer oldstudent_id){
        System.out.println("---"+request+oldstudent_id);
        oldstudentService.deleteOldstudent(request,oldstudent_id);
        return 0;
    }

}
