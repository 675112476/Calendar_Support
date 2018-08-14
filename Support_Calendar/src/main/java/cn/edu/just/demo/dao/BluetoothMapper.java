package cn.edu.just.demo.dao;

import cn.edu.just.demo.model.Bluetooth;
import cn.edu.just.demo.utils.MyMapper;

import java.util.List;

public interface BluetoothMapper extends MyMapper<Bluetooth> {
    public List<Bluetooth> getByDateSchool(String date,String school);
    public int getByDateSchoolBrand(String date,String school,String bluetooth_brand);
}