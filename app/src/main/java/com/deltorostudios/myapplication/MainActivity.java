package com.deltorostudios.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button btnSubmit, btnAddToList;
    private List<String> list;
    private EditText editTextCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get view references for spinner menu, etc.
        spinner = (Spinner) findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnAddToList = (Button) findViewById(R.id.btnAddToList);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);


        // Make a list and add "Exercise" to it
        list = new ArrayList<String>();
        list.add("Exercise");

        // Make an ArrayAdapter to populate the Spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        // onClickListener for Submit Toast button
        setBtnSubmit();


        // onClickListener for Add to List button
        btnAddToList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Make a string and give it the value of the EditText
                String category = editTextCategory.getText().toString();

                // Prevent duplicate categories, blank categories or adds a category
                if (list.contains(category)) {
                    Toast.makeText(MainActivity.this, "That category exists already!", Toast.LENGTH_SHORT).show();
                }
                else if (category.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter a category!", Toast.LENGTH_SHORT).show();
                } else {
                    list.add(category);
                }
            }
        });
        // Notify the adapter that the data set has changed which updates the UI
        dataAdapter.notifyDataSetChanged();
    }

    // get the selected dropdown list value
    public void setBtnSubmit() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Category: " + String.valueOf(spinner.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
