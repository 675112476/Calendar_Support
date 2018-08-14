package cn.edu.just.demo.service;

import cn.edu.just.demo.model.School;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SchoolService {

    public String insertSchool(HttpServletRequest request, School school);
    public String getSchoolName(HttpServletRequest request);
    public int deleteSchool(HttpServletRequest request, int school_id);
    public int updateSchool(HttpServletRequest request, School school);
    public String getSchool(HttpServletRequest request);

}
