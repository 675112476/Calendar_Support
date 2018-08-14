package cn.edu.just.demo.service.impl;

import cn.edu.just.demo.dao.WorkerMapper;
import cn.edu.just.demo.model.Worker;
import cn.edu.just.demo.service.WorkerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerMapper workerMapper;
    @Override
    public String insertWorker(HttpServletRequest request, Worker worker) {
        //返回对应的主键
        String callback = request.getParameter("callback");
        workerMapper.insert(worker);
        System.out.println(worker.getWorkNum());
        String result=String.valueOf(workerMapper.getIdByNum(worker.getWorkNum()));
        System.out.println("insert_result_ID"+result);
        String json="{'result':"+result+"}";
        String a=callback+"("+json+")";
        System.out.println(a);
        return a;
    }

    @Override
    public int updateWorker(HttpServletRequest request, Worker worker) {
        int result=workerMapper.updateByPrimaryKey(worker);
        return 0;
    }

    @Override
    public int getIdByNum(String work_num) {
        return workerMapper.getIdByNum(work_num);
    }

    @Override
    public String getPhoneByName(String name) {
        String phone=workerMapper.getPhoneByName(name);
        return phone;
    }

    @Override
    public int deleteWorker(HttpServletRequest request, int worker_id) {
        System.out.println(worker_id);
        workerMapper.deleteByPrimaryKey(worker_id);
        return 0;
    }
    @Override
    public String getWorker(HttpServletRequest request){
        String callback = request.getParameter("callback");
        List<Worker> list=workerMapper.selectAll();
        JSONArray json = new JSONArray();
        for(Worker worker : list){
            JSONObject jo = new JSONObject();
            try {
                jo.put("id", String.valueOf(worker.getId()));
                jo.put("name", worker.getName());
                jo.put("phone", worker.getPhone());
                jo.put("worknum",worker.getWorkNum());
            } catch (Exception e) {
                e.printStackTrace();
            }
            json.add(jo);
        }
        String result=callback+"("+json.toString()+")";
        //System.out.println(result);
        return  result;
    }
}
