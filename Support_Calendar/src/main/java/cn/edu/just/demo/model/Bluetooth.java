package cn.edu.just.demo.model;

import javax.persistence.*;

public class Bluetooth {
    @Id
    private Integer id;

    private String date;

    private String school;

    @Column(name = "bluetooth_brand")
    private String bluetoothBrand;

    private Integer amount;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
     * @return school
     */
    public String getSchool() {
        return school;
    }

    /**
     * @param school
     */
    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    /**
     * @return bluetooth_brand
     */
    public String getBluetoothBrand() {
        return bluetoothBrand;
    }

    /**
     * @param bluetoothBrand
     */
    public void setBluetoothBrand(String bluetoothBrand) {
        this.bluetoothBrand = bluetoothBrand == null ? null : bluetoothBrand.trim();
    }

    /**
     * @return amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Bluetooth(Integer id, String date, String school, String bluetoothBrand, Integer amount) {
        this.id = id;
        this.date = date;
        this.school = school;
        this.bluetoothBrand = bluetoothBrand;
        this.amount = amount;
    }

    public Bluetooth() {
    }
}