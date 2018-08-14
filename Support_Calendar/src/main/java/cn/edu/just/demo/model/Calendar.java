package cn.edu.just.demo.model;

import javax.persistence.*;

public class Calendar {
    @Id
    private Integer id;

    private String date;

    private String school;

    @Column(name = "back_person")
    private String backPerson;

    @Column(name = "scene_person")
    private String scenePerson;

    @Column(name = "student_predict")
    private String studentPredict;

    @Column(name = "new_old")
    private String newOld;

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
     * @return back_person
     */
    public String getBackPerson() {
        return backPerson;
    }

    /**
     * @param backPerson
     */
    public void setBackPerson(String backPerson) {
        this.backPerson = backPerson == null ? null : backPerson.trim();
    }

    /**
     * @return scene_person
     */
    public String getScenePerson() {
        return scenePerson;
    }

    /**
     * @param scenePerson
     */
    public void setScenePerson(String scenePerson) {
        this.scenePerson = scenePerson == null ? null : scenePerson.trim();
    }

    /**
     * @return student_predict
     */
    public String getStudentPredict() {
        return studentPredict;
    }

    /**
     * @param studentPredict
     */
    public void setStudentPredict(String studentPredict) {
        this.studentPredict = studentPredict == null ? null : studentPredict.trim();
    }

    /**
     * @return new_old
     */
    public String getNewOld() {
        return newOld;
    }

    /**
     * @param newOld
     */
    public void setNewOld(String newOld) {
        this.newOld = newOld == null ? null : newOld.trim();
    }

    public Calendar(Integer id, String date, String school, String backPerson, String scenePerson, String studentPredict, String newOld) {
        this.id = id;
        this.date = date;
        this.school = school;
        this.backPerson = backPerson;
        this.scenePerson = scenePerson;
        this.studentPredict = studentPredict;
        this.newOld = newOld;
    }

    public Calendar() {
    }
}