package cn.edu.just.demo.service;

import cn.edu.just.demo.model.Bluetooth;

import javax.servlet.http.HttpServletRequest;

public interface BluetoothService {
    public String insertBluetooth(HttpServletRequest request, Bluetooth bluetooth);
    public int deleteBluetooth(HttpServletRequest request,int bluetooth_id);
    public int updateBluetooth(HttpServletRequest request,Bluetooth bluetooth);
    public String getBluetooth(HttpServletRequest request);
}
