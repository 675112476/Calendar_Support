package cn.edu.just.demo.dao;

import cn.edu.just.demo.model.Worker;
import cn.edu.just.demo.utils.MyMapper;

import java.util.List;

public interface WorkerMapper extends MyMapper<Worker> {
    public Worker getByName(String name);
    public int getIdByNum(String work_num);
    public String getPhoneByName(String name);
    public List<String> getWorkerName();
}