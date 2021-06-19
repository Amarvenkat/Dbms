package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;

public class
MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btnview;
    Button btnadd;
    Button btndelete;
    EditText editTextdelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        btnadd = (Button) findViewById(R.id.btnadd);
        btnview = (Button) findViewById(R.id.btnview);
        btndelete = (Button) findViewById(R.id.btndelete);
        editTextdelete = (EditText) findViewById(R.id.editTextdelete);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Add.class);
                startActivity(intent);
            }
        });
        viewAll();
        DeleteData();

    }
    public void viewAll() {
        btnview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Train no:"+ res.getString(0)+"\n");
                            buffer.append("Name: "+ res.getString(1)+"\n");
                            buffer.append("From: "+ res.getString(2)+"\n");
                            buffer.append("To: "+ res.getString(3)+"\n");
                            buffer.append("Date: "+ res.getString(4)+"\n");
                            buffer.append("Time: "+ res.getString(5)+"\n");
                            buffer.append("Class: "+ res.getString(6)+"\n\n");
                            buffer.append("Seat no: "+ res.getString(7)+"\n");
                            }

                        // Show all data
                        showMessage("Ticket",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void DeleteData() {
        btndelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextdelete.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this,"Ticket Cancelled",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Ticket not Cancelled",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
