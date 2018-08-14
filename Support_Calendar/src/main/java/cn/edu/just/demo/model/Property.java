package cn.edu.just.demo.model;

import javax.persistence.*;

public class Property {
    @Id
    private Integer id;

    private String date;

    private Integer property;

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
     * @return property
     */
    public Integer getProperty() {
        return property;
    }

    /**
     * @param property
     */
    public void setProperty(Integer property) {
        this.property = property;
    }
}