package cn.edu.just.demo.dao;

import cn.edu.just.demo.model.Oldstudent;
import cn.edu.just.demo.utils.MyMapper;

import java.util.List;

public interface OldstudentMapper extends MyMapper<Oldstudent> {
    public List<String> getSchoolByDate(String date);
    public int getIdByDateSchool(String date,String school);
}