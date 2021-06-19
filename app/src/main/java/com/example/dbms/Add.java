package com.example.dbms;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.Throwable;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.app.AlertDialog;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Add extends AppCompatActivity {
    DatabaseHelper myDb;
    private static final String TAG ="Add";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    EditText  name,depature,arrival,date,time,class_preferences,seat_no;
    Button btnAddData;
    TextView editdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myDb = new DatabaseHelper(this);
        btnAddData = (Button) findViewById(R.id.submit);
        name = (EditText) findViewById(R.id.name);
        depature= (EditText) findViewById(R.id.from);
        editdate = (TextView) findViewById(R.id.date);
        arrival = (EditText) findViewById(R.id.to);
        time = (EditText) findViewById(R.id.time);
        class_preferences = (EditText) findViewById(R.id.class_preferences) ;
        seat_no =(EditText) findViewById(R.id.seat_no);
        AddData();
        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Add.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG,"onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year);
                String  date = day + "/" + month + "/" + year;
                editdate.setText(date);

            }
        };

    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(name.getText().toString(),
                                arrival.getText().toString(),
                                depature.getText(). toString(),
                                editdate.getText().toString(),
                                time.getText().toString(),
                                class_preferences.getText().toString(),
                                seat_no.getText().toString());


                        if(isInserted == true)
                            Toast.makeText(Add.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Add.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                        //writeMessage();

                    }
                }


        );

    }


}
