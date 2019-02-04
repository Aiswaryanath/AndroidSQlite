package com.student.admin.sqlite;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmptyActivity extends AppCompatActivity {
EditText ed1,ed2;
    String getname,getemailid;
    Button b,b1;
    databasehelper  Databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        ed1 = (EditText) findViewById(R.id.name);
        ed2 = (EditText) findViewById(R.id.emailid);
        Databasehelper = new databasehelper(this);
        Databasehelper.getWritableDatabase();
        b = (Button) findViewById(R.id.submit);
        b1=(Button)findViewById(R.id.search) ;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getname = ed1.getText().toString();
                getemailid = ed2.getText().toString();
                Log.d("name", getname);
                Log.d("emailid", getemailid);
                boolean status=Databasehelper.insertData(getname,getemailid);
                if (status==true)
                {
                    Toast.makeText(getApplicationContext(),"successfully inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(i);

            }
        });
    }}

    /**
     * Created by Administrator on 1/29/2019.
     */
