package cn.edu.just.demo.dao;

import cn.edu.just.demo.model.Daynum;
import cn.edu.just.demo.utils.MyMapper;

public interface DaynumMapper extends MyMapper<Daynum> {
    public Daynum getNumByDate(String date);
    public int getIdByDate(String date);
}