package com.fiek.attendanceapp;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationContext extends Application {
    private Faculty faculty;
    private AttendanceSession attendanceSession;
    private Admin admin;
    private ArrayList<AttendanceSession> attendanceSessions;
    private ArrayList<Student> studentList;
    private ArrayList<Attendance> attendanceList;


    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    public Faculty getFacultyBean() {
        return faculty;
    }
    public void setFacultyBean(Faculty faculty) {
        this.faculty = faculty;
    }
    public AttendanceSession getAttendanceSession() {
        return attendanceSession;
    }
    public void setAttendanceSessionBean(AttendanceSession attendanceSession) {
        this.attendanceSession = attendanceSession;
    }
    public ArrayList<AttendanceSession> getAttendanceSessionsList(){
        return attendanceSessions;
    }
    public ArrayList<Student> getStudentBeanList() {
        return studentList;
    }
    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    public ArrayList<Attendance> getAttendanceBeanList() {
        return attendanceList;
    }
    public void setAttendanceBeanList(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }



}
