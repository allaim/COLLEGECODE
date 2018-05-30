package com.manager.domain;

public class Enrolment {

    private Integer id;
    private Student student;
    private Course course;

    private Double atendance;
    private Double assignment;
    private Double exame;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getAtendance() {
        return atendance;
    }

    public void setAtendance(Double atendance) {
        this.atendance = atendance;
    }

    public Double getAssignment() {
        return assignment;
    }

    public void setAssignment(Double assignment) {
        this.assignment = assignment;
    }

    public Double getExame() {
        return exame;
    }

    public void setExame(Double exame) {
        this.exame = exame;
    }

    public String toString(){
        return "Id: "+getId()
                +"; Name: "+getStudent().getName()
                +"; Course: "+getCourse().getName()
                +"; Exame Grade: "+getExame()
                +"; Attendance: "+getAtendance()
                +"; Assignment Grade: "+getAssignment();
    }

}
