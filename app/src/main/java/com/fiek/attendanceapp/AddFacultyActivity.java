package com.fiek.attendanceapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddFacultyActivity extends AppCompatActivity {


    Button registerButton;
    EditText textFirstName;
    EditText textLastName;
    EditText textemail;
    EditText textcontact;
    EditText textaddress;
    EditText textusername;
    EditText textpassword;
    RadioGroup radiogroup;
    RadioButton radiobutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);


        textFirstName=(EditText)findViewById(R.id.editTextFirstName);
        textLastName=(EditText)findViewById(R.id.editTextLastName);
        textcontact=(EditText)findViewById(R.id.editTextPhone);
        textaddress=(EditText)findViewById(R.id.editTextaddr);
        textusername=(EditText)findViewById(R.id.editTextUserName);
        textpassword=(EditText)findViewById(R.id.editTextPassword);
        registerButton=(Button)findViewById(R.id.RegisterButton);
        radiogroup = (RadioGroup)findViewById(R.id.radiogroupGender);






        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String first_name = textFirstName.getText().toString();
                String last_name = textLastName.getText().toString();
                String phone_no = textcontact.getText().toString();
                String address = textaddress.getText().toString();
                String userName = textusername.getText().toString();
                String passWord = textpassword.getText().toString();
                int selectedID = radiogroup.getCheckedRadioButtonId();
                radiobutton = (RadioButton)findViewById(selectedID);

                if (TextUtils.isEmpty(first_name)) {
                    textFirstName.setError("please enter firstname");
                }
                else if (TextUtils.isEmpty(last_name)) {
                    textLastName.setError("please enter lastname");
                }
                else if(selectedID == -1){
                    AlertDialog.Builder alertDialog  = new AlertDialog.Builder(AddFacultyActivity.this);
                    alertDialog.setTitle(" ");
                    alertDialog.setMessage("Please select one Option");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                        }
                    });
                    alertDialog.show();

                }
                else if (TextUtils.isEmpty(phone_no)) {
                    textcontact.setError("please enter phoneno");
                }

                else if (TextUtils.isEmpty(address)) {
                    textaddress.setError("enter address");
                }
                else if (TextUtils.isEmpty(userName)) {
                    textcontact.setError("please enter username");
                }
                else if (TextUtils.isEmpty(passWord)) {
                    textaddress.setError("enter password");
                }
                else {

                    String gender = (String) radiobutton.getText();

                    Faculty faculty = new Faculty();
                    faculty.setFaculty_firstname(first_name);
                    faculty.setFaculty_lastname(last_name);
                    faculty.setGender(gender);
                    faculty.setFaculty_mobilenumber(phone_no);
                    faculty.setFaculty_address(address);
                    faculty.setFaculty_username(userName);
                    faculty.setFaculty_password(passWord);

                    DBAdapter dbAdapter = new DBAdapter(AddFacultyActivity.this);
                    dbAdapter.addFaculty(faculty);

                    Intent intent =new Intent(AddFacultyActivity.this,MenuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Faculty added successfully", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}
