package cn.edu.just.demo.model;

import javax.persistence.*;

public class Daynum {
    @Id
    private Integer id;

    private String date;

    /**
     * 当天发展总量
     */
    @Column(name = "total_num")
    private Integer totalNum;

    /**
     * 行销宝发展总量
     */
    @Column(name = "xxb_num")
    private Integer xxbNum;

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
     * 获取当天发展总量
     *
     * @return total_num - 当天发展总量
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * 设置当天发展总量
     *
     * @param totalNum 当天发展总量
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 获取行销宝发展总量
     *
     * @return xxb_num - 行销宝发展总量
     */
    public Integer getXxbNum() {
        return xxbNum;
    }

    /**
     * 设置行销宝发展总量
     *
     * @param xxbNum 行销宝发展总量
     */
    public void setXxbNum(Integer xxbNum) {
        this.xxbNum = xxbNum;
    }
}