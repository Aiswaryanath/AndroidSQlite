package com.student.admin.sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
EditText ed1,ed2;
    Button b,b1,b2;
    String getname,getemail,gete,newvalue,getid;
    databasehelper Databasehelper;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        builder=new AlertDialog.Builder(this);
        builder.setTitle("confirm");
        builder.setMessage("Are you sure you want to delete?");

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean status=Databasehelper.DeleteData(getid);
                if (status==true)
                {
                    Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"not deleted",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(),"YES CLICKED",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"NO CLICKED",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });
        ed1 = (EditText) findViewById(R.id.name);
        ed2 = (EditText) findViewById(R.id.email);
        Databasehelper = new databasehelper(this);
        Databasehelper.getWritableDatabase();
        b = (Button) findViewById(R.id.search);
        b1 = (Button) findViewById(R.id.update);
        b2 = (Button) findViewById(R.id.delete);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getname = ed1.getText().toString();
                Log.d("name", getname);
                gete = ed2.getText().toString();

                Cursor cursor = Databasehelper.SearchData(getname);
                if (cursor.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No Name Found", Toast.LENGTH_LONG).show();
                } else {
                    while (cursor.moveToNext()) {
                        getemail = cursor.getString(2);
                        Toast.makeText(getApplicationContext(), getemail, Toast.LENGTH_LONG).show();
                        ed2.setText(getemail);
                        getid = cursor.getString(0);
                        Toast.makeText(getApplicationContext(), getid, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newvalue = ed2.getText().toString();
                boolean status = Databasehelper.UpdateData(getid, newvalue);
                if (status == true) {
                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error in Updations", Toast.LENGTH_LONG).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }
    }

