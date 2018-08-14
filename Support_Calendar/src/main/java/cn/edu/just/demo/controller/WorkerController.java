package cn.edu.just.demo.controller;

import cn.edu.just.demo.model.Worker;
import cn.edu.just.demo.service.CommonService;
import cn.edu.just.demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WorkerController {


    @Autowired
    private WorkerService workerService;

    @RequestMapping("/getWorker")
    public String getWorker(HttpServletRequest request){
        String list=workerService.getWorker(request);
        return  list;
    }

    @RequestMapping("/insertWorker")
    public String insertWorker(HttpServletRequest request,String data){
        System.out.println("---"+request+data);
        com.alibaba.fastjson.JSONObject jsonObject=(com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(data);
        Worker worker=new Worker(null,jsonObject.getString("name"),jsonObject.getString("phone"),jsonObject.getString("work_num"));
        String result=workerService.insertWorker(request, worker);
        return result;
    }

    @RequestMapping("/updateWorker")
    public int updateWorker(HttpServletRequest request,String data){
        System.out.println("---"+request+data);
        com.alibaba.fastjson.JSONObject jsonObject=(com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(data);
        Worker worker=new Worker(jsonObject.getInteger("id"),jsonObject.getString("name"),jsonObject.getString("phone"),jsonObject.getString("work_num"));
        workerService.updateWorker(request,worker);
        return 1;
    }
    @RequestMapping("/deleteWorker")
    public int deleteWorker(HttpServletRequest request,Integer worker_id){
        System.out.println("---"+request+worker_id);
        workerService.deleteWorker(request,worker_id);
         return 0;
    }
}
