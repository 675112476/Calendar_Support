package cn.edu.just.demo.service.impl;

import cn.edu.just.demo.dao.BluetoothMapper;
import cn.edu.just.demo.model.Bluetooth;
import cn.edu.just.demo.service.BluetoothService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class BluetoothServiceImpl implements BluetoothService {

    @Autowired
    BluetoothMapper bluetoothMapper;
    @Override
    public String insertBluetooth(HttpServletRequest request, Bluetooth bluetooth) {
        //返回对应的主键
        System.out.println(bluetooth.toString());
        String callback = request.getParameter("callback");
        bluetoothMapper.insert(bluetooth);
        int id=bluetoothMapper.getByDateSchoolBrand(bluetooth.getDate(),bluetooth.getSchool(),bluetooth.getBluetoothBrand());
        System.out.println("insert_result_ID"+id);
        String json="{'result':"+id+"}";
        String a=callback+"("+json+")";
        System.out.println(a);
        return a;
    }

    @Override
    public int deleteBluetooth(HttpServletRequest request, int bluetooth_id) {
        System.out.println(bluetooth_id);
        bluetoothMapper.deleteByPrimaryKey(bluetooth_id);
        return 0;
    }

    @Override
    public int updateBluetooth(HttpServletRequest request, Bluetooth bluetooth) {
        int result=bluetoothMapper.updateByPrimaryKey(bluetooth);
        return 0;
    }

    @Override
    public String getBluetooth(HttpServletRequest request){
    String callback = request.getParameter("callback");
    List<Bluetooth> list=bluetoothMapper.selectAll();
    JSONArray json = new JSONArray();
        for(Bluetooth bluetooth : list){
        JSONObject jo = new JSONObject();
        try {
            jo.put("id", String.valueOf(bluetooth.getId()));
            jo.put("date", bluetooth.getDate());
            jo.put("school", bluetooth.getSchool());
            jo.put("bluetooth_brand",bluetooth.getBluetoothBrand());
            jo.put("amount",bluetooth.getAmount());
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
