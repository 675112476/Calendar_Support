package cn.edu.just.demo.model;

import javax.persistence.*;

public class Oldstudent {
    @Id
    private Integer id;

    private String date;

    private String school;

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

    public Oldstudent(Integer id, String date, String school) {
        this.id = id;
        this.date = date;
        this.school = school;
    }

    public Oldstudent() {
    }
}