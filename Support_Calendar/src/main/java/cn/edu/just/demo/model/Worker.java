package cn.edu.just.demo.model;

import javax.persistence.*;

public class Worker {
    @Id
    private Integer id;

    private String name;

    private String phone;

    @Column(name = "work_num")
    private String workNum;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return work_num
     */
    public String getWorkNum() {
        return workNum;
    }

    /**
     * @param workNum
     */
    public void setWorkNum(String workNum) {
        this.workNum = workNum == null ? null : workNum.trim();
    }

    public Worker(Integer id, String name, String phone, String workNum) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.workNum = workNum;
    }

    public Worker() {
    }
}