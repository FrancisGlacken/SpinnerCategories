package com.deltorostudios.myapplication;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;



/**
 *      This program is a testing ground for a spinner drop down menu
 *      that can be added to or removed by the user. The spinner menu
 *      items will persist past app termination.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ArrayList<String> list = new ArrayList<>();
    private EditText editTextCategory;
    private ArrayAdapter<String> dataAdapter;

    private ArrayList<String> list2 = new ArrayList<>();
    private Spinner spinner2;
    private EditText editTextCategory2;
    private ArrayAdapter<String> dataAdapter2;
    private String key = "greenKey";


    /**
     * onCreate Method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get view references for spinner menu, etc.
        spinner = findViewById(R.id.spinner);
        editTextCategory = findViewById(R.id.editTextCategory);

        // Place Exercise in the spinner on first start // then get list from shared preferences
        getSpinnerData();


        // Make an ArrayAdapter to populate the Spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);



    } // End onCreate





    /**
     * onDestroy method, where we save the list by calling the saveArrayList method
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveArrayList(list, key);
    } // End onDestroy






    /**
     * saveArrayList method, for converting our ArrayList into a json object for storage in shared preferences
     * @param list
     * @param key
     */
    /*
    TO MAKE A SECOND ARRAY LIST, MAKE ANOTHER LIST/KEY VARIABLE TO HOLD IT TEMPORARILY BEFORE
    CHANGING IT TO THE RELEVANT LIST VARIABLE
     */
   public void saveArrayList(ArrayList<String> list, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }








    /**
     * getArrayList method, for getting our json object back from shared preferences and converting it
     * @param key
     * @return
     */
    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }






    /**
     * autoInsertCategoryOnce method, for adding "Exercise" to the list on startup. If this method
     *                                  is not called, the app will crash because the list is null
     *
     */
    public void getSpinnerData() {
        list = new ArrayList<>();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {

            //This code will only execute on the first run
            list.add("Exercise");


            // Save the boolean needed to terminate this if statement permanently
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.apply();
        } else {

            // Get list from shared preferences
            list = getArrayList(key);
        }
    }






    /**
     * BtnSubmit button, for making a toast containing the name of the current category
     * @param view
     */
    // Button that shows a toast to the user showing off the category
    public void BtnSubmit(View view) {
        Toast.makeText(MainActivity.this,
                "Category: " + String.valueOf(spinner.getSelectedItem()),
                Toast.LENGTH_SHORT).show();
    }





    /**
     * BtnAddToList button, for adding categories to the list
     * @param view
     */
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
            // Add the category
            list.add(localCategory);

            // Change the spinner to the new category // getting the position of the new category
            spinner.setSelection(dataAdapter.getPosition(localCategory));
        }

        // Tell the adapter to update the arrayList
        dataAdapter.notifyDataSetChanged();
    }





    /**
     * BtnRemoveFromList button, for removing categories from the list
     * @param view
     */
    // Button that removes the currently selected category
    public void BtnRemoveFromList(View view) {

        // Get the value that is currently in the spinner
        final String currentCategory = spinner.getSelectedItem().toString();


        // Make an alert dialog
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setTitle("Remove category?");
        dialog.setMessage("Are you sure you want tso remove the " + currentCategory + " category?");
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                if (!list.contains(null)) {
                    // Remove the category that is currently in
                    list.remove(currentCategory);
                    // Notify the adapter that the data set has changed which updates the UI
                    dataAdapter.notifyDataSetChanged();
                }
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







