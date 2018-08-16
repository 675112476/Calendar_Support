package cn.edu.just.demo.service.impl;

import cn.edu.just.demo.dao.*;
import cn.edu.just.demo.model.Bluetooth;
import cn.edu.just.demo.model.Daynum;
import cn.edu.just.demo.model.School;
import cn.edu.just.demo.service.CommonService;
import cn.edu.just.demo.utils.HttpClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CalendarMapper calendarMapper;
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private DaynumMapper daynumMapper;
    @Autowired
    private BluetoothMapper bluetoothMapper;

    @Override
    public String pre_Details(String date) {
        List<School> schools=schoolMapper.getSchoolByDate(date);

        Daynum daynums=daynumMapper.getNumByDate(date);
        //System.out.println(daynums.getTotalNum());
        //System.out.println(daynums.getXxbNum());
        JSONArray array=new JSONArray();
        int tar=0;//次数标识
        for(School school:schools){
            String scene_name=calendarMapper.getsceneNameByDateAndSchool(date,school.getSchoolAlias());
            String scene_phone="";
            if(scene_name.length()>0){
                scene_phone=workerMapper.getByName(scene_name).getPhone();
            }

            String student_predict=calendarMapper.getStudentPredictByDateAndSchool(date,school.getSchoolAlias());
            List<Bluetooth> bluetooths=bluetoothMapper.getByDateSchool(date,school.getSchoolAlias());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("school",school.getName());
            jsonObject.put("student_predict",student_predict);
            jsonObject.put("scene_person",scene_name);
            jsonObject.put("scene_phone",scene_phone);
            jsonObject.put("scene_phone",scene_phone);
            JSONArray jsonArray=new JSONArray();
            for(Bluetooth bluetooth:bluetooths)
            {
                JSONObject jsonObject1=new JSONObject();
                jsonObject1.put("tooth_brand",bluetooth.getBluetoothBrand());
                jsonObject1.put("tooth_amount",bluetooth.getAmount());
                jsonArray.add(jsonObject1);
            }
            jsonObject.put("bluetooth",jsonArray);

            array.add(jsonObject);
            tar++;
        }
        //System.out.println(tar);
        for(int i=0;i<4-tar;i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("school","");
            jsonObject.put("student_predict","");
            jsonObject.put("scene_person","");
            jsonObject.put("scene_phone","");
            jsonObject.put("bluetooth","");
            array.add(jsonObject);
        }
        String back_name=calendarMapper.getBackNameByDate(date);
        String back_phone=workerMapper.getByName(back_name).getPhone();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("day_num",daynums.getTotalNum());
        jsonObject.put("xxb_num",daynums.getXxbNum());
        jsonObject.put("far_person",back_name);
        jsonObject.put("far_phone",back_phone);
        jsonObject.put("1","当日校园发展量");
        jsonObject.put("2","当日校园行销宝");
        array.add(jsonObject);

        System.out.println("array"+array.toString());
        return array.toString();
    }

    @Override
    public String pre_Details(HttpServletRequest request, String date) {
        String callback = request.getParameter("callback");
        //System.out.println("---"+callback);
        //System.out.println("---"+date);
        String result=pre_Details(date);
        JSONObject jo = new JSONObject();
        jo.put("result",result);
        return callback+"("+jo.toString()+")";
    }

    @Override
    public String to_Details(HttpServletRequest request, String date) {
        String callback = request.getParameter("callback");
        String deatils="";
        JSONArray array=new JSONArray();
        List<School> schools=schoolMapper.getSchoolByDate(date);
        int tar=0;
        for(School school:schools){

            String scene_name=calendarMapper.getsceneNameByDateAndSchool(date,school.getSchoolAlias());
            String scene_phone="";
            System.out.println(scene_name.getClass().toString());
            if(scene_name.length()>0){
                scene_phone=workerMapper.getByName(scene_name).getPhone();
            }
            String student_predict=calendarMapper.getStudentPredictByDateAndSchool(date,school.getSchoolAlias());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("school",school.getName());
            jsonObject.put("student_predict",student_predict);
            jsonObject.put("scene_person",scene_name);
            jsonObject.put("scene_phone",scene_phone);
            JSONArray jsonArray=new JSONArray();
            List<Bluetooth> bluetooths=bluetoothMapper.getByDateSchool(date,school.getSchoolAlias());
            for(Bluetooth bluetooth:bluetooths)
            {
                JSONObject jsonObject1=new JSONObject();
                jsonObject1.put("tooth_brand",bluetooth.getBluetoothBrand());
                jsonObject1.put("tooth_amount",bluetooth.getAmount());
                jsonArray.add(jsonObject1);
            }
            jsonObject.put("bluetooth",jsonArray);
            array.add(jsonObject);
            tar++;
        }
        System.out.println(tar);
        for(int i=0;i<4-tar;i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("school","");
            jsonObject.put("student_predict","");
            jsonObject.put("scene_person","");
            jsonObject.put("scene_phone","");
            jsonObject.put("bluetooth","");
            array.add(jsonObject);
        }

        String easy_date=date.replace("-","");
        //deatils+="<br/>";
        //http://132.230.108.106:5000/xxb/getCalData?srcSys=zhangxu&authPwd=campusxxb&calDate=20180712
        String string="http://132.230.108.93:5000/xxb/getCalData?srcSys=zhangxu&authPwd=campusxxb&calDate="+easy_date;
        String result=HttpClient.doGet(string);
        System.out.println(result);
        JSONObject jsonObject= (JSONObject) JSON.parse(result);
        int result_code=jsonObject.getInteger("result_code");
        JSONObject result_msg=jsonObject.getJSONObject("result_msg");
        JSONObject today_result=result_msg.getJSONObject("today_result");
        int total_num=today_result.getInteger("total_num");
        int xxb_num=today_result.getInteger("xxb_num");

        String back_name=calendarMapper.getBackNameByDate(date);
        String back_phone=workerMapper.getByName(back_name).getPhone();
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("day_num",total_num);
        jsonObject1.put("xxb_num",xxb_num);
        jsonObject1.put("far_person",back_name);
        jsonObject1.put("far_phone",back_phone);
        jsonObject1.put("1","今日校园发展量");
        jsonObject1.put("2","今日校园行销宝");
        array.add(jsonObject1);

        JSONObject json = new JSONObject();
        System.out.println(array.toString());
        json.put("result",array.toString());
        return callback+"("+json.toString()+")";
    }

    @Override
    public String after_Details(HttpServletRequest request, String date) {
        String callback = request.getParameter("callback");
        String deatils="";
        System.out.println("----"+date);
        List<School> schools=schoolMapper.getSchoolByDate(date);
        JSONArray array=new JSONArray();
        int tar=0;
        for(School school:schools){
            System.out.println("--------------------"+date+school.getSchoolAlias());
            String scene_name=calendarMapper.getsceneNameByDateAndSchool(date,school.getSchoolAlias());
            System.out.println("scene_name："+scene_name);
            String scene_phone="";
            System.out.println(scene_name.getClass().toString());
            if(scene_name.length()>0){
                scene_phone=workerMapper.getByName(scene_name).getPhone();
            }
            System.out.println("scene_phone："+scene_phone);
            String student_predict=calendarMapper.getStudentPredictByDateAndSchool(date,school.getSchoolAlias());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("school",school.getName());
            jsonObject.put("student_predict",student_predict);
            jsonObject.put("scene_person",scene_name);
            jsonObject.put("scene_phone",scene_phone);
            JSONArray jsonArray=new JSONArray();
            List<Bluetooth> bluetooths=bluetoothMapper.getByDateSchool(date,school.getSchoolAlias());
            for(Bluetooth bluetooth:bluetooths)
            {
                JSONObject jsonObject1=new JSONObject();
                jsonObject1.put("tooth_brand",bluetooth.getBluetoothBrand());
                jsonObject1.put("tooth_amount",bluetooth.getAmount());
                jsonArray.add(jsonObject1);
            }
            jsonObject.put("bluetooth",jsonArray);
            array.add(jsonObject);
            tar++;
        }
        for(int i=0;i<4-tar;i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("school","");
            jsonObject.put("student_predict","");
            jsonObject.put("scene_person","");
            jsonObject.put("scene_phone","");
            jsonObject.put("bluetooth","");
            array.add(jsonObject);
        }
        String back_name=calendarMapper.getBackNameByDate(date);
        String back_phone=workerMapper.getByName(back_name).getPhone();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("day_num","");
        jsonObject.put("xxb_num","");
        jsonObject.put("far_person",back_name);
        jsonObject.put("far_phone",back_phone);
        jsonObject.put("1","");
        jsonObject.put("2","");
        array.add(jsonObject);
        JSONObject jo = new JSONObject();
        System.out.println(array.toString());
        jo.put("result",array.toString());
        return callback+"("+jo.toString()+")";
    }
    @Override
    public String login(HttpServletRequest request,String username,String password){
        String callback = request.getParameter("callback");
        int result=1;
        String message="";
        if(username.equals("fengtiancong.wx@jsoa.net")&&password.equals("Qwer~1234")){
            result=0;
            message="dianxin_logined";
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result",String.valueOf(result));
        jsonObject.put("password",message);
        //System.out.println(callback+"("+jsonObject.toString()+")");
        return callback+"("+jsonObject.toString()+")";
    }

}
