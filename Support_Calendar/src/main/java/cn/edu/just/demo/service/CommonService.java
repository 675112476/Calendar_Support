package cn.edu.just.demo.service;

import cn.edu.just.demo.model.Calendar;
import cn.edu.just.demo.model.School;
import cn.edu.just.demo.model.Worker;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CommonService {

    public String pre_Details(String date);
    public String pre_Details(HttpServletRequest request,String date);
    public String to_Details(HttpServletRequest request,String date);
    public String after_Details(HttpServletRequest request,String date);
    public String login(HttpServletRequest request,String username,String password);
}
