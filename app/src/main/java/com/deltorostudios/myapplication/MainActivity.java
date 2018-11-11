package com.deltorostudios.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;



/**
 *      This program is a testing ground for a spinner drop down menu
 *  that can be added to or removed by the user.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ArrayList<String> list;
    private int categoryPosition;
    private EditText editTextCategory;
    ArrayAdapter<String> dataAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get view references for spinner menu, etc.
        spinner = findViewById(R.id.spinner);
        editTextCategory = findViewById(R.id.editTextCategory);

        // Make a list and add "Exercise" to it
        list = new ArrayList<String>();
        list.add("Exercise");

        // Make an ArrayAdapter to populate the Spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }


    // Button that shows a toast to the user showing off the category
    public void BtnSubmit(View view) {
        Toast.makeText(MainActivity.this,
                "Category: " + String.valueOf(spinner.getSelectedItem()),
                Toast.LENGTH_SHORT).show();
    }

    // Button that add a category from the EditText
    public void BtnAddToList(View view) {

        // Make a string and give it the value of the EditText
        String localCategory = editTextCategory.getText().toString();

        // Prevent duplicate categories, blank categories or adds a category
        if (list.contains(localCategory)) {
            Toast.makeText(MainActivity.this, "That category exists already!", Toast.LENGTH_SHORT).show();
        }
        else if (localCategory.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter a category!", Toast.LENGTH_SHORT).show();
        } else {
            list.add(localCategory);
        }

        dataAdapter.notifyDataSetChanged();
    }

    // Button that removes the currently selected category
    public void BtnRemoveFromList(View view) {

        // Get the value that is currently in the spinner
        final String currentCategory = spinner.getSelectedItem().toString();


        // Make an alert dialog
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setTitle("Remove category?");
        dialog.setMessage("Are you sure you want to remove the " + currentCategory + "category?");
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Remove the category that is currently in
                list.remove(currentCategory);
                // Notify the adapter that the data set has changed which updates the UI
                dataAdapter.notifyDataSetChanged();
            }
        });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialog.show();



    }

    // Currently unused
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // TODO: Something on Item selected
    }

    // Currently unused
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // TODO: Something when no item is selected
    }

}





