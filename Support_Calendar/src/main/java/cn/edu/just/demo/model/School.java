package cn.edu.just.demo.model;

import javax.persistence.*;

public class School {
    @Id
    private Integer id;

    private String name;

    @Column(name = "school_alias")
    private String schoolAlias;

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
     * @return school_alias
     */
    public String getSchoolAlias() {
        return schoolAlias;
    }

    /**
     * @param schoolAlias
     */
    public void setSchoolAlias(String schoolAlias) {
        this.schoolAlias = schoolAlias == null ? null : schoolAlias.trim();
    }

    public School(Integer id, String name, String schoolAlias) {
        this.id = id;
        this.name = name;
        this.schoolAlias = schoolAlias;
    }

    public School() {
    }
}