package com.fiek.attendanceapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewStudentByBranchYear extends AppCompatActivity {


    ArrayList<Student> studentBeanList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;
    String branch;
    String year;


    DBAdapter dbAdapter = new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_by_branch_year);


        listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> studentList = new ArrayList<String>();
        studentList.add("First Name   |    Last Name");
        branch = getIntent().getExtras().getString("branch");
        year = getIntent().getExtras().getString("year");

        studentBeanList = dbAdapter.getAllStudentByBranchYear(branch, year);
        for (Student studentBean : studentBeanList) {
            String users = studentBean.getStudent_firstname() + ", " + studentBean.getStudent_lastname();

            studentList.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_student_list, R.id.label, studentList);
        listView.setAdapter(listAdapter);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewStudentByBranchYear.this);

                alertDialogBuilder.setTitle(getTitle() + "decision");
                alertDialogBuilder.setMessage("Are you sure?");

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        studentList.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        dbAdapter.deleteStudent(studentBeanList.get(position).getStudent_id());
                        studentBeanList = dbAdapter.getAllStudentByBranchYear(branch, year);

                        for (Student studentBean : studentBeanList) {
                            String users = " FirstName: " + studentBean.getStudent_firstname() + "\nLastname:" + studentBean.getStudent_lastname();
                            studentList.add(users);
                            Log.d("users: ", users);

                        }
                    }

                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();


                return false;
            }
        });
    }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

    }


