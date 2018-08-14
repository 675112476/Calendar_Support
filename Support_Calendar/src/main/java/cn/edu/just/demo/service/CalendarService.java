package cn.edu.just.demo.service;

import cn.edu.just.demo.model.Calendar;

import javax.servlet.http.HttpServletRequest;

public interface CalendarService {
    public String get_Calendar(HttpServletRequest request);
    public String get_Calendar();//页面简单展示前四个
    public String get_calendar(HttpServletRequest request);
    public String getCalendarByDate(HttpServletRequest request,String date);
    public String getMarkCalendar(HttpServletRequest request);//h5页面调用
    public String insert_Calendar(HttpServletRequest request, Calendar calendar);
    public int deleteCalendar(HttpServletRequest request, int calendar_id);
    public int updateCalendar(HttpServletRequest request, Calendar school);

}
