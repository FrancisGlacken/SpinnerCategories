package com.deltorostudios.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private Button btnSubmit, btnAddToList1, btnaddtoList2;
    private List<String> list;
    private EditText editTextCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnAddToList1 = (Button) findViewById(R.id.btnAddToList1);
        btnaddtoList2 = (Button) findViewById(R.id.btnAddToList2);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);
        list = new ArrayList<String>();

        list.add("Exercise");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

        addItemsOnSpinner2();
        setBtnSubmit();

        btnaddtoList2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String category = editTextCategory.getText().toString();

                list.add(category);


            }
        });

        dataAdapter.notifyDataSetChanged();





    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {



    }



    // get the selected dropdown list value
    public void setBtnSubmit() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "OnClickListener : " +
                    "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
                    "\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



    /* // Will add an item to list 1 in theory
    public void setBtnAddToList1() {

        btnAddToList1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    } */

    /*
    // OnItemSelected/CustomOnItemSelectedListener
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    } */




}
