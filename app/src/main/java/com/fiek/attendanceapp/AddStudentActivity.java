package com.fiek.attendanceapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {


    Button registerButton;
    EditText textFirstName;
    EditText textLastName;

    EditText textcontact;
    EditText textaddress;
    Spinner spinnerbranch, spinneryear;
    String userrole, branch, year;
    private String[] branchString = new String[] { "Computer Engineering", "Telecommunication", "ElectroEnergetics", "Electronics", "Automatics"};
    private String[] yearString = new String[]{"1st year", "2nd year", "3rd year"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        spinnerbranch = (Spinner) findViewById(R.id.spinnerdept);
        spinneryear = (Spinner) findViewById(R.id.spinneryear);
        textFirstName = (EditText) findViewById(R.id.editTextFirstName);
        textLastName = (EditText) findViewById(R.id.editTextLastName);
        textcontact = (EditText) findViewById(R.id.editTextPhone);
        textaddress = (EditText) findViewById(R.id.editTextaddr);
        registerButton = (Button) findViewById(R.id.RegisterButton);


        spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                branch = (String) spinnerbranch.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, branchString);
        adapter_branch
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(adapter_branch);


        ///......................spinner2

        spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                year = (String) spinneryear.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearString);
        adapter_year
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(adapter_year);


        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //......................................validation
                String first_name = textFirstName.getText().toString();
                String last_name = textLastName.getText().toString();
                String phone_no = textcontact.getText().toString();
                String address = textaddress.getText().toString();

                if (TextUtils.isEmpty(first_name)) {
                    textFirstName.setError("please enter firstname");
                } else if (TextUtils.isEmpty(last_name)) {
                    textLastName.setError("please enter lastname");
                } else if (TextUtils.isEmpty(phone_no)) {
                    textcontact.setError("please enter phoneNo");
                } else if (TextUtils.isEmpty(address)) {
                    textaddress.setError("enter address");
                } else {

                    Student student = new Student();

                    student.setStudent_firstname(first_name);
                    student.setStudent_lastname(last_name);
                    student.setStudent_mobilenumber(phone_no);
                    student.setStudent_address(address);
                    student.setStudent_department(branch);
                    student.setStudent_class(year);

                    DBAdapter dbAdapter = new DBAdapter(AddStudentActivity.this);
                    dbAdapter.addStudent(student);

                    Intent intent = new Intent(AddStudentActivity.this, MenuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Student Added Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}


