package cn.edu.just.demo.service;


import cn.edu.just.demo.model.Oldstudent;

import javax.servlet.http.HttpServletRequest;

public interface OldstudentService {
    public String getSchoolByDate(String date);
    public String getSchoolByDate(HttpServletRequest request,String date);
    public String insertOldstudent(HttpServletRequest request, Oldstudent oldstudent);
    public int deleteOldstudent(HttpServletRequest request,int oldstudent_id);
    public int updateOldstudent(HttpServletRequest request, Oldstudent oldstudent);
    public String getOldstudent(HttpServletRequest request);
}
