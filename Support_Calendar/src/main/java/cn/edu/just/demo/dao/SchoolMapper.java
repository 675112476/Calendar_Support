package cn.edu.just.demo.dao;

import cn.edu.just.demo.model.School;
import cn.edu.just.demo.utils.MyMapper;

import java.util.List;

public interface SchoolMapper extends MyMapper<School> {
    public List<School> getSchoolByDate(String date);
    public int getIdByName(String name);
    public List<String> getSchoolName();
    public String getSchoolAlias(String name);
}