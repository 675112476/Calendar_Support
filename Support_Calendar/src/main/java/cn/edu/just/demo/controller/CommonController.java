package cn.edu.just.demo.controller;

import cn.edu.just.demo.dao.CalendarMapper;
import cn.edu.just.demo.dao.WorkerMapper;
import cn.edu.just.demo.model.Calendar;
import cn.edu.just.demo.model.Worker;
import cn.edu.just.demo.service.CommonService;
import cn.edu.just.demo.service.PropertyService;
import cn.edu.just.demo.service.WorkerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class CommonController {


    @Autowired
    private CommonService commonService;
    @Autowired
    private PropertyService propertyService;
    @RequestMapping("/hello")
    public String hello() {

        return "hello";
    }
    @RequestMapping("/preDetails")
    public String getDetails(HttpServletRequest request, String date) {
        String list = commonService.pre_Details(request, date);
        System.out.println(list);
        return list;
    }

    @RequestMapping("/toDetails")
    public String toDetails(HttpServletRequest request, String date) {
        String list = commonService.to_Details(request, date);
        System.out.println(list);
        return list;
    }

    @RequestMapping("/afterDetails")
    public String afterDetails(HttpServletRequest request, String date) {
        String list = commonService.after_Details(request, date);
        System.out.println(list);
        return list;
    }

    @RequestMapping("/getProperty")
    public String getProperty(HttpServletRequest request) {
        String list = propertyService.getProperty(request);
        System.out.println(list);
        return list;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request,String username,String password){
        System.out.println(commonService.login(request,username,password));
        return commonService.login(request,username,password);
    }
    @RequestMapping("/manager")
    public void manager(HttpServletRequest request,HttpServletResponse response){
        //response.sendError(HttpServletResponse.SC_FORBIDDEN,"用户登录验证不正确");
        System.out.println(1);
    }
}