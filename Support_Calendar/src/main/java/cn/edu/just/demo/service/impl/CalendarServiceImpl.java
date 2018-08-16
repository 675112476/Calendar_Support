package cn.edu.just.demo.service.impl;

import cn.edu.just.demo.dao.BluetoothMapper;
import cn.edu.just.demo.dao.CalendarMapper;
import cn.edu.just.demo.dao.PropertyMapper;
import cn.edu.just.demo.model.Bluetooth;
import cn.edu.just.demo.model.Calendar;
import cn.edu.just.demo.service.CalendarService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private CalendarMapper calendarMapper;
    @Autowired
    private WorkerServiceImpl workerService;
    @Autowired
    private BluetoothMapper bluetoothMapper;
    @Override
    public String get_Calendar(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        String result=callback+"("+get_Calendar()+")";
        System.out.println(result);
        return  result;
    }

    @Override
    public String get_Calendar() {
        List<Calendar> list=calendarMapper.selectAll();
        Collections.reverse(list);
        System.out.println(list);
        //后面重弄优先级
        //List<Date_Property>date_propertyList=date_propertyMapper.selectAll();
        //System.out.println(date_propertyList);
        JSONArray json = new JSONArray();
//        for(Calendar calendar:list){
//            //System.out.println("--"+calendar.getScene_person()+calendar.getSchool()+calendar.getBack_person()+calendar.getDate()+calendar.getProperty());
//            calendar.setScenePerson(calendar.getSchool()+"："+calendar.getScenePerson());
//        }

        for(int i = list.size() - 1; i >= 0; i--){
            Calendar calendar_div=list.get(i);
            JSONObject jo = new JSONObject();
            for(int t = i - 1; t >= 0; t--){
                Calendar calendar1=list.get(t);
                if(calendar_div.getDate().equals(calendar1.getDate())){
                    List<Calendar> target=new ArrayList<Calendar>();
                    int tar_index=list.indexOf(calendar_div);
                    int index=list.indexOf(calendar1);
                    //System.out.println(list.size());
                    list.remove(calendar1);
                    //System.out.println(list.size());
                    //System.out.println("pre_calendar:"+calendar_div.getScenePerson());
                    calendar_div.setSchool(calendar_div.getSchool()+":"+calendar1.getSchool());
                    //System.out.println("aft_calendar:"+calendar_div.getScenePerson());
                    list.set(tar_index-1,calendar_div);
                    i--;
                }
            }
            String[] schools=calendar_div.getSchool().split(":");
            String[] result=new String[4];
            if(schools.length==1){
                result[0]=schools[0];
                result[1]="";
                result[2]="";
                result[3]="";
            }
            if(schools.length==2){
                result[0]=schools[0];
                result[1]=schools[1];
                result[2]="";
                result[3]="";
            }
            if(schools.length==3){
                result[0]=schools[0];
                result[1]=schools[1];
                result[2]=schools[2];
                result[3]="";
            }
            if(schools.length>3){
                result[0]=schools[0];
                result[1]=schools[1];
                result[2]=schools[2];
                result[3]=schools[3];
            }

            try {
                jo.put("date", calendar_div.getDate());
                jo.put("school", result);
                //jo.put("studentpredict",calendar_div.getStudentPredict());
            } catch (Exception e) {
                e.printStackTrace();
            }
            json.add(jo);
        }
        return  json.toString();
    }

    @Override
    public String get_calendar(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        List<Calendar> list=calendarMapper.getInOrder();
        JSONArray datas=new JSONArray();
        for(Calendar calendar:list){
            JSONObject data=new JSONObject();
            data.put("id",calendar.getId());
            data.put("date",calendar.getDate());
            data.put("school",calendar.getSchool());
            data.put("student_predict",calendar.getStudentPredict());
            data.put("back_person",calendar.getBackPerson());
            data.put("back_phone",workerService.getPhoneByName(calendar.getBackPerson()));
            data.put("scene_person",calendar.getScenePerson());
            data.put("scene_phone",workerService.getPhoneByName(calendar.getScenePerson()));
            datas.add(data);
        }
        String result=callback+"("+datas.toString()+")";
        System.out.println(result);
        return  result;
    }

    @Override
    public String insert_Calendar(HttpServletRequest request, Calendar calendar) {
        String callback = request.getParameter("callback");
        calendarMapper.insert(calendar);
        String result=String.valueOf(calendarMapper.getIdBySchoolAndDate(calendar.getSchool(),calendar.getDate()));
        System.out.println("insert_result_ID"+result);
        String json="{'result':"+result+"}";
        String a=callback+"("+json+")";
        System.out.println(a);
        return a;
    }

    @Override
    public int deleteCalendar(HttpServletRequest request, int calendar_id) {
        System.out.println(calendar_id);
        calendarMapper.deleteByPrimaryKey(calendar_id);
        return 0;
    }

    @Override
    public int updateCalendar(HttpServletRequest request, Calendar school) {
        System.out.println("--"+request+school.getSchool());
        int result=calendarMapper.updateByPrimaryKey(school);
        return result;
    }

    @Override
    public String getCalendarByDate(HttpServletRequest request, String date) {
        String callback = request.getParameter("callback");
        System.out.println(request+date);
        List<Calendar> list=calendarMapper.getAllByDate(date);
        JSONObject data=new JSONObject();
        for(int i = list.size() - 1; i >= 0; i--){
            Calendar calendar_div=list.get(i);
            data.put("date",calendar_div.getDate());
            JSONArray schools = new JSONArray();
            JSONObject jsonObject0=new JSONObject();
            jsonObject0.put("school",calendar_div.getSchool());
            jsonObject0.put("scene_person",calendar_div.getScenePerson());
            jsonObject0.put("telephone",workerService.getPhoneByName(calendar_div.getScenePerson()));
            schools.add(jsonObject0);
            System.out.println(jsonObject0.toString());
            for(int t = i - 1; t >= 0; t--){
                Calendar calendar1=list.get(t);
                if(calendar_div.getDate().equals(calendar1.getDate())){
                    List<Calendar> target=new ArrayList<Calendar>();
                    int tar_index=list.indexOf(calendar_div);
                    int index=list.indexOf(calendar1);
                    //System.out.println(list.size());
                    list.remove(calendar1);
                    JSONObject jsonObject1=new JSONObject();
                    jsonObject1.put("school",calendar1.getSchool());
                    jsonObject1.put("scene_person",calendar1.getScenePerson());
                    jsonObject1.put("scene_phone",workerService.getPhoneByName(calendar1.getScenePerson()));
                    schools.add(jsonObject1);
                    list.set(tar_index-1,calendar_div);
                    i--;
                }
            }
            data.put("schools",schools);
            data.put("back_person",calendar_div.getBackPerson());
            data.put("back_phone",workerService.getPhoneByName(calendar_div.getBackPerson()));
        }
        String result=callback+"("+data.toString()+")";
        return result;
    }

    @Override
    public String getMarkCalendar(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        List<Calendar> list=calendarMapper.selectAll();
        Collections.reverse(list);
        JSONObject data=new JSONObject();
        for(int i = list.size() - 1; i >= 0; i--){
            Calendar calendar_div=list.get(i);
            JSONArray datas = new JSONArray();
            JSONObject jsonObject0=new JSONObject();
            jsonObject0.put("school",calendar_div.getSchool());
            String date=calendar_div.getDate();
            JSONArray jsonArray=new JSONArray();
            List<Bluetooth> bluetooths=bluetoothMapper.getByDateSchool(date,calendar_div.getSchool());
            for(Bluetooth bluetooth:bluetooths)
            {
                JSONObject jsonObject1=new JSONObject();
                jsonObject1.put("tooth_brand",bluetooth.getBluetoothBrand());
                jsonObject1.put("tooth_amount",bluetooth.getAmount());
                jsonArray.add(jsonObject1);
            }
            jsonObject0.put("blue_tooth",jsonArray);
            jsonObject0.put("scene_person",calendar_div.getScenePerson());
            jsonObject0.put("student_predict",calendar_div.getStudentPredict());
            jsonObject0.put("scene_phone",workerService.getPhoneByName(calendar_div.getScenePerson()));
            datas.add(jsonObject0);
            for(int t = i - 1; t >= 0; t--){
                Calendar calendar1=list.get(t);
                if(calendar_div.getDate().equals(calendar1.getDate())){
                    List<Calendar> target=new ArrayList<Calendar>();
                    int tar_index=list.indexOf(calendar_div);
                    int index=list.indexOf(calendar1);
                    //System.out.println(list.size());
                    list.remove(calendar1);
                    JSONObject jsonObject1=new JSONObject();
                    jsonObject1.put("school",calendar1.getSchool());
                    jsonObject1.put("scene_person",calendar1.getScenePerson());
                    jsonObject1.put("student_predict",calendar1.getStudentPredict());
                    jsonObject1.put("scene_phone",workerService.getPhoneByName(calendar1.getScenePerson()));
                    JSONArray jsonArray1=new JSONArray();
                    List<Bluetooth> bluetooths1=bluetoothMapper.getByDateSchool(date,calendar1.getSchool());
                    for(Bluetooth bluetooth:bluetooths1)
                    {
                        JSONObject jsonObject2=new JSONObject();
                        jsonObject2.put("tooth_brand",bluetooth.getBluetoothBrand());
                        jsonObject2.put("tooth_amount",bluetooth.getAmount());
                        jsonArray1.add(jsonObject2);
                    }
                    jsonObject1.put("blue_tooth",jsonArray1);
                    datas.add(jsonObject1);
                    list.set(tar_index-1,calendar_div);
                    i--;
                }
            }
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("back_person",calendar_div.getBackPerson());
            jsonObject1.put("back_phone",workerService.getPhoneByName(calendar_div.getBackPerson()));
            datas.add(jsonObject1);
            String[] dates=date.split("-");
            date=dates[0]+"-"+Integer.valueOf(dates[1])+"-"+Integer.valueOf(dates[2]);
            data.put(date,datas);
        }
        String result=callback+"("+data.toString()+")";
        System.out.println(result);
        return  result;
    }

}
