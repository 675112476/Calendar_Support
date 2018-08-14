package cn.edu.just.demo.controller;

import cn.edu.just.demo.dao.CalendarMapper;
import cn.edu.just.demo.dao.SchoolMapper;
import cn.edu.just.demo.dao.WorkerMapper;
import cn.edu.just.demo.model.Calendar;
import cn.edu.just.demo.model.Worker;
import cn.edu.just.demo.service.CalendarService;
import cn.edu.just.demo.service.CommonService;
import cn.edu.just.demo.service.WorkerService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.dynamic.annotation.Param;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CalendarController {


    @Autowired
    private CalendarService calendarService;
    @Autowired
    private SchoolMapper schoolMapper;
    @RequestMapping("/getcalendar")
    public String getcalendar(HttpServletRequest request){
        return calendarService.get_calendar(request);
    }
    @RequestMapping("/getCalendar")
    public String getCalendar(HttpServletRequest request){
        return calendarService.get_Calendar(request);
    }
    @RequestMapping("/wapgetCalendar")
    public String wapgetCalendar(HttpServletRequest request,String date){return calendarService.getMarkCalendar(request);}
    @RequestMapping("/updateCalendar")
    public int updateCalendar(HttpServletRequest request,String data){
        JSONObject jsonObject= (JSONObject) JSON.parse(data);
        Calendar calendar=new Calendar(jsonObject.getInteger("id"),jsonObject.getString("date"),jsonObject.getString("school"),jsonObject.getString("back_person"),jsonObject.getString("scene_person"),jsonObject.getString("student_predict"),"new");
        return calendarService.updateCalendar(request,calendar);
    }
    @RequestMapping("/insertCalendar")
    public String insertCalendar(HttpServletRequest request,String data){
        System.out.println("---"+request+data);
        com.alibaba.fastjson.JSONObject jsonObject=(com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(data);
        Calendar calendar=new Calendar(null,jsonObject.getString("date"),jsonObject.getString("school"),jsonObject.getString("back_person"),jsonObject.getString("scene_person"),jsonObject.getString("student_predict"),"new");
        String result=calendarService.insert_Calendar(request, calendar);
        return result;
    }
    @RequestMapping("/deleteCalendar")
    public int deleteCalendar(HttpServletRequest request,int calendar_id){
        System.out.println("delete"+calendar_id);
        return calendarService.deleteCalendar(request,calendar_id);
    }
    @RequestMapping("/insertCalendarByForm")
    public int insertCalendarByForm(HttpServletRequest request,@RequestParam(value = "date") String date,@RequestParam(value = "school") String school,
    @RequestParam(value = "student_predict") String student_predict,@RequestParam(value = "scene_person") String scene_person,@RequestParam(value = "back_person") String back_person)
    {
        System.out.println("---"+date+school+back_person+scene_person+student_predict);
        school=schoolMapper.getSchoolAlias(school);
        System.out.println("---"+date+school+back_person+scene_person+student_predict);
        Calendar calendar=new Calendar(null,date,school,back_person,scene_person,student_predict,"new");
        calendarService.insert_Calendar(request,calendar);
        return 0;
    }
}