package com.manager.domain;

public class Course {

    private Integer id;
    private String name;

    private Branch branch;
    private Faculty faculty;

    private Boolean gradeVisible;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Boolean getGradeVisible() {
        return gradeVisible;
    }

    public void setGradeVisible(Boolean gradeVisible) {
        this.gradeVisible = gradeVisible;
    }

    public String toString(){
        return "Id: "+getId()+"; Name: "+getName()+"; Faculty: "+getFaculty().getName()+"; Branch: "+getBranch().getName()+"; Grade Visible: "+getGradeVisible();
    }

}
