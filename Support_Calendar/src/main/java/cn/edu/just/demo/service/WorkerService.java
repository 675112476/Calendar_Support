package cn.edu.just.demo.service;

import cn.edu.just.demo.model.Worker;

import javax.servlet.http.HttpServletRequest;

public interface WorkerService {

    public String insertWorker(HttpServletRequest request,Worker worker);
    public int deleteWorker(HttpServletRequest request,int worker_id);
    public int updateWorker(HttpServletRequest request,Worker worker);
    public String getWorker(HttpServletRequest request);
    public int getIdByNum(String work_num);
    public String getPhoneByName(String name);
}
