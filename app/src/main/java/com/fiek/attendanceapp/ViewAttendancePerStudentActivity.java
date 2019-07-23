package com.fiek.attendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewAttendancePerStudentActivity extends AppCompatActivity {


    ArrayList<Attendance> attendanceBeanList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;
    DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_per_student);


        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> attendanceList = new ArrayList<String>();
        attendanceList.add("Present Count Per Student");
        attendanceBeanList=((ApplicationContext)ViewAttendancePerStudentActivity.this.getApplicationContext()).getAttendanceBeanList();


        for(Attendance attendanceBean : attendanceBeanList)
        {
            String users = "";

            DBAdapter dbAdapter = new DBAdapter(ViewAttendancePerStudentActivity.this);
            Student studentBean =dbAdapter.getStudentById(attendanceBean.getAttendance_student_id());
            users = attendanceBean.getAttendance_student_id()+".     "+studentBean.getStudent_firstname()+","+studentBean.getStudent_lastname()+"                  "+attendanceBean.getAttendance_session_id();
            attendanceList.add(users);
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list_per_student, R.id.labelAttendancePerStudent, attendanceList);
        listView.setAdapter( listAdapter );

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
