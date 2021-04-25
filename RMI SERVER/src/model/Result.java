package model;

import java.io.Serializable;

public class Result implements Serializable {

    private int id, id_students, id_lecturers, id_subjects, diemGK, diemCK, diemTB;

    public Result() {
    }

    public Result(int id, int id_students, int id_lecturers, int id_subjects, int diemGK, int diemCK, int diemTB) {
        this.id = id;
        this.id_students = id_students;
        this.id_lecturers = id_lecturers;
        this.id_subjects = id_subjects;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemTB = diemTB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_students() {
        return id_students;
    }

    public void setId_students(int id_students) {
        this.id_students = id_students;
    }

    public int getId_lecturers() {
        return id_lecturers;
    }

    public void setId_lecturers(int id_lecturers) {
        this.id_lecturers = id_lecturers;
    }

    public int getId_subjects() {
        return id_subjects;
    }

    public void setId_subjects(int id_subjects) {
        this.id_subjects = id_subjects;
    }

    public int getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(int diemGK) {
        this.diemGK = diemGK;
    }

    public int getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(int diemCK) {
        this.diemCK = diemCK;
    }

    public int getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(int diemTB) {
        this.diemTB = diemTB;
    }
}
