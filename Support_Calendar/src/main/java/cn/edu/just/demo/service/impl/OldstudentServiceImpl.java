package cn.edu.just.demo.service.impl;

import cn.edu.just.demo.dao.OldstudentMapper;
import cn.edu.just.demo.model.Oldstudent;
import cn.edu.just.demo.service.OldstudentService;
import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Service
public class OldstudentServiceImpl implements OldstudentService {
    @Autowired
    OldstudentMapper oldstudentMapper;
    @Override
    public String getSchoolByDate(String date) {
        List<String> list=oldstudentMapper.getSchoolByDate(date);
        Collections.reverse(list);
        JSONObject jo = new JSONObject();
        list.size();
        String[] result=new String[4];
        if(list.size()==1){
            result[0]=list.get(0);
            result[1]="";
            result[2]="";
            result[3]="";
        }
        if(list.size()==2){
            result[0]=list.get(0);
            result[1]=list.get(1);
            result[2]="";
            result[3]="";
        }
        if(list.size()==3){
            result[0]=list.get(0);
            result[1]=list.get(1);
            result[2]=list.get(2);
            result[3]="";
        }
        if(list.size()>3){
            result[0]=list.get(0);
            result[1]=list.get(1);
            result[2]=list.get(2);
            result[3]=list.get(3);
        }
        try {
            jo.put("oldstudent", result);
            //jo.put("studentpredict",calendar_div.getStudentPredict());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  jo.toString();
    }

    @Override
    public String getSchoolByDate(HttpServletRequest request, String date) {
        String callback = request.getParameter("callback");
        String result=callback+"("+getSchoolByDate(date)+")";
        //System.out.println(date+result);
        return  result;
    }
    @Override
    public String insertOldstudent(HttpServletRequest request, Oldstudent oldstudent) {
        //返回对应的主键
        System.out.println(oldstudent.toString());
        String callback = request.getParameter("callback");
        oldstudentMapper.insert(oldstudent);
        int id=oldstudentMapper.getIdByDateSchool(oldstudent.getDate(),oldstudent.getSchool());
        System.out.println("insert_result_ID"+id);
        String json="{'result':"+id+"}";
        String a=callback+"("+json+")";
        System.out.println(a);
        return a;
    }

    @Override
    public int deleteOldstudent(HttpServletRequest request, int oldstudent_id) {
        System.out.println(oldstudent_id);
        oldstudentMapper.deleteByPrimaryKey(oldstudent_id);
        return 0;
    }

    @Override
    public int updateOldstudent(HttpServletRequest request, Oldstudent oldstudent) {
        int result=oldstudentMapper.updateByPrimaryKey(oldstudent);
        return 0;
    }

    @Override
    public String getOldstudent(HttpServletRequest request){
        String callback = request.getParameter("callback");
        List<Oldstudent> list=oldstudentMapper.selectAll();
        com.alibaba.fastjson.JSONArray json = new JSONArray();
        for(Oldstudent oldstudent : list){
            com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
            try {
                jo.put("id", String.valueOf(oldstudent.getId()));
                jo.put("date", oldstudent.getDate());
                jo.put("school", oldstudent.getSchool());
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
