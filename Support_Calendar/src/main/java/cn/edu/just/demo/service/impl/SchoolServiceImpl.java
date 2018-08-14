package cn.edu.just.demo.service.impl;

import cn.edu.just.demo.dao.CalendarMapper;
import cn.edu.just.demo.dao.SchoolMapper;
import cn.edu.just.demo.dao.WorkerMapper;
import cn.edu.just.demo.model.School;
import cn.edu.just.demo.service.SchoolService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private CalendarMapper calendarMapper;
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private SchoolMapper schoolMapper;


    @Override
    public String insertSchool(HttpServletRequest request, School school) {
        String callback = request.getParameter("callback");
        schoolMapper.insert(school);
        String result=String.valueOf(schoolMapper.getIdByName(school.getName()));
        System.out.println("insert_result_ID"+result);
        String json="{'result':"+result+"}";
        String a=callback+"("+json+")";
        System.out.println(a);
        return a;
    }

    @Override
    public String getSchoolName(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        List<String> schools=schoolMapper.getSchoolName();
        List<String> persons=workerMapper.getWorkerName();
        JSONArray SchooljsonArray=new JSONArray();
        for(int i=0;i<schools.size();i++){
            String school=schools.get(i);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",i);
            jsonObject.put("text",school);
            SchooljsonArray.add(jsonObject);
        }
        JSONArray WorkerjsonArray=new JSONArray();
        for(int i=1;i<persons.size()+1;i++){
            String worker=persons.get(i-1);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",i);
            jsonObject.put("text",worker);
            WorkerjsonArray.add(jsonObject);
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("schools",SchooljsonArray.toString());
        jsonObject.put("persons",WorkerjsonArray.toString());
        String json=jsonObject.toString();
        String result=callback+"("+json+")";
        System.out.println(result);
        return result;
    }

    @Override
    public int deleteSchool(HttpServletRequest request, int school_id) {
        System.out.println(school_id);
        schoolMapper.deleteByPrimaryKey(school_id);
        return 0;
    }

    @Override
    public int updateSchool(HttpServletRequest request, School school) {
        int result=schoolMapper.updateByPrimaryKey(school);
        return 0;
    }

    @Override
    public String getSchool(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        List<School> list=schoolMapper.selectAll();
        JSONArray json = new JSONArray();
        for(School school : list){
            JSONObject jo = new JSONObject();
            try {
                jo.put("id", String.valueOf(school.getId()));
                jo.put("name", school.getName());
                jo.put("schoolalias", school.getSchoolAlias());
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
