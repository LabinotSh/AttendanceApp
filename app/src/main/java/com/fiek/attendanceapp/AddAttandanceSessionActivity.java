package com.fiek.attendanceapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddAttandanceSessionActivity extends AppCompatActivity {
    private ImageButton date;
    private Calendar cal;
    private int day;
    private int month;
    private int dyear;
    private EditText dateEditText;
    private EditText subjectText;
    private EditText deptText;
    Button submit;
    Button viewAttendance;
    Button viewTotalAttendance;
    Button logout;
    Spinner spinnerbranch,spinneryear,spinnerSubject;
    String branch = "cse";
    String year = "SE";
    String subject = "SC";

    private String[] branchString = new String[] { "Computer Engineering", "Telecommunication", "ElectroEnergetics", "Electronics", "Automatics"};
    private String[] yearString = new String[] {"1st year","2nd year","3rd year"};


    private String[] subjectFinal = new String[]{"Databases", "Math","Software Engineering","Java Programming","Android Programming"};
    AttendanceSession attendanceSessionBean;

    DBAdapter dbAdapter = new DBAdapter(this);
    Faculty facultySub;
    private String sub;
    private String dept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attandance_session);

        spinnerbranch=(Spinner)findViewById(R.id.spinner1);
        spinneryear=(Spinner)findViewById(R.id.spinneryear);
        spinnerSubject=(Spinner)findViewById(R.id.spinnerSE);
        logout = (Button)findViewById(R.id.btnLogout);

        ///spinner per branch
        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, branchString);
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(adapter_branch);
        spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                branch =(String) spinnerbranch.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });




        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearString);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(adapter_year);
        spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                year =(String) spinneryear.getSelectedItem();
                Toast.makeText(getApplicationContext(), "year:"+year, Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(AddAttandanceSessionActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });




 //spinner per subject -----
        ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjectFinal);
        adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapter_subject);
        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                subject =(String) spinnerSubject.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        date = (ImageButton) findViewById(R.id.DateImageButton);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        dyear = cal.get(Calendar.YEAR);
        dateEditText = (EditText) findViewById(R.id.DateEditText);
        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog(0);

            }
        });

        submit=(Button)findViewById(R.id.buttonsubmit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                    AttendanceSession attendanceSessionBean = new AttendanceSession();
                    Faculty bean = ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();


                    attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                    attendanceSessionBean.setAttendance_session_department(dept);
                    attendanceSessionBean.setAttendance_session_class(year);
                    attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
                    attendanceSessionBean.setAttendance_session_subject(sub);

                    DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
                    int sessionId = dbAdapter.addAttendanceSession(attendanceSessionBean);

                    ArrayList<Student> studentBeanList = dbAdapter.getAllStudentByBranchYear(branch, year);
                    ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).setStudentList(studentBeanList);


                    Intent intent = new Intent(AddAttandanceSessionActivity.this, AddAttendanceActivity.class);
                    intent.putExtra("sessionId", sessionId);
                    startActivity(intent);
                }

        });

        viewAttendance=(Button)findViewById(R.id.viewAttendancebutton);
        viewAttendance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                 ApplicationContext context;

                AttendanceSession attendanceSessionBean = new AttendanceSession();
                Faculty bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

                int bean1= bean.getFaculty_id();

                attendanceSessionBean.setAttendance_session_faculty_id(bean1);
                attendanceSessionBean.setAttendance_session_department(dept);
                attendanceSessionBean.setAttendance_session_class(year);
                attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
                attendanceSessionBean.setAttendance_session_subject(sub);

                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);

                ArrayList<Attendance> attendanceBeanList = dbAdapter.getAttendanceBySessionID(attendanceSessionBean);
                ((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

                Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendanceByFacultyActivity.class);
                startActivity(intent);

            }
        });

        viewTotalAttendance=(Button)findViewById(R.id.viewTotalAttendanceButton);
        viewTotalAttendance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AttendanceSession attendanceSessionBean = new AttendanceSession();
                Faculty bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(dept);
                attendanceSessionBean.setAttendance_session_class(year);
                attendanceSessionBean.setAttendance_session_subject(sub);

                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);

                ArrayList<Attendance> attendanceBeanList = dbAdapter.getTotalAttendanceBySessionID(attendanceSessionBean);
                ((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

                Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendanceByFacultyActivity.class);
                startActivity(intent);

            }
        });





    }


    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, dyear, month, day);
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };

}
