package cn.edu.just.demo.dao;

import cn.edu.just.demo.model.Calendar;
import cn.edu.just.demo.utils.MyMapper;

import java.util.List;

public interface CalendarMapper extends MyMapper<Calendar> {
    public String getsceneNameByDateAndSchool(String date,String school);
    public String getBackNameByDate(String date);
    public String getStudentPredictByDateAndSchool (String date,String school);
    public List<Calendar> getAllByDate(String date);
    public int getIdBySchoolAndDate(String school,String date);
    public List<Calendar> getInOrder();
}