package cn.edu.just.demo.controller;

import cn.edu.just.demo.model.Bluetooth;
import cn.edu.just.demo.service.BluetoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
public class BluetoothController {
    @Autowired
    private BluetoothService bluetoothService;

    @RequestMapping("/getBluetooth")
    public String getWorker(HttpServletRequest request){
        String list=bluetoothService.getBluetooth(request);
        return  list;
    }

    @RequestMapping("/insertBluetooth")
    public String insertWorker(HttpServletRequest request,String data){
        System.out.println("---"+request+data);
        com.alibaba.fastjson.JSONObject jsonObject=(com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(data);
        Bluetooth bluetooth=new Bluetooth(null,jsonObject.getString("date"),jsonObject.getString("school"),jsonObject.getString("bluetooth_brand"),jsonObject.getInteger("amount"));
        String result=bluetoothService.insertBluetooth(request, bluetooth);
        return result;
    }

    @RequestMapping("/updateBluetooth")
    public int updateWorker(HttpServletRequest request,String data){
        System.out.println("---"+request+data);
        com.alibaba.fastjson.JSONObject jsonObject=(com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(data);
        Bluetooth bluetooth=new Bluetooth(jsonObject.getInteger("id"),jsonObject.getString("date"),jsonObject.getString("school"),jsonObject.getString("bluetooth_brand"),jsonObject.getInteger("amount"));
        bluetoothService.updateBluetooth(request,bluetooth);
        return 1;
    }
    @RequestMapping("/deleteBluetooth")
    public int deleteWorker(HttpServletRequest request,Integer bluetooth_id){
        System.out.println("---"+request+bluetooth_id);
        bluetoothService.deleteBluetooth(request,bluetooth_id);
        return 0;
    }
}
