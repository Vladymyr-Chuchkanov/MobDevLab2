package com.chuchkanov.mobdevlab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CesarActivity extends AppCompatActivity {

    private final static String PARAMS_FILE="params_cesar.txt";
    private final static String TEXT_FILE="result_cesar.txt";
    Activity act;
    private static ArrayList<String> arrSymbols= new ArrayList<String>() {
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesar);
        arrSymbols= new ArrayList<String>() {
        };
        arrSymbols.add("a");
        arrSymbols.add("b");
        arrSymbols.add("c");
        arrSymbols.add("d");
        arrSymbols.add("e");
        arrSymbols.add("f");
        arrSymbols.add("g");
        arrSymbols.add("h");
        arrSymbols.add("i");
        arrSymbols.add("j");
        arrSymbols.add("k");
        arrSymbols.add("l");
        arrSymbols.add("m");
        arrSymbols.add("n");
        arrSymbols.add("o");
        arrSymbols.add("p");
        arrSymbols.add("q");
        arrSymbols.add("r");
        arrSymbols.add("s");
        arrSymbols.add("t");
        arrSymbols.add("u");
        arrSymbols.add("v");
        arrSymbols.add("w");
        arrSymbols.add("x");
        arrSymbols.add("y");
        arrSymbols.add("z");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        act = this;
        EditText et1 = (EditText) findViewById(R.id.cesar_key_inpt);

        View.OnFocusChangeListener temp1 = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(act);
            }
        };
        et1.setOnFocusChangeListener(temp1);
        EditText et2 = (EditText) findViewById(R.id.cesar_text_inpt);
        View.OnFocusChangeListener temp2 = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(act);
            }
        };
        et2.setOnFocusChangeListener(temp2);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.menu_item_main:
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent1);
                return true;
            case R.id.menu_item_author:
                Intent intent2 = new Intent(this, AuthorActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent2);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void uploadCesarData(View view){
        hideKeyboard(act);
        FileInputStream fin = null;
        TextView textView = findViewById(R.id.result_text);
        EditText editKey = (EditText) findViewById(R.id.cesar_key_inpt);
        EditText editText = (EditText) findViewById(R.id.cesar_text_inpt);
        try {
            fin = openFileInput(PARAMS_FILE);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            if(text.length()<1){
                return;
            }
            String keyStr = text.split("\n")[0];
            String textStr = text.replace(keyStr+"\n","");
            editKey.setText(keyStr);
            editText.setText(textStr);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void uploadCesarResult(View view){
        hideKeyboard(act);
        FileInputStream fin = null;
        TextView textView = findViewById(R.id.result_text);
        try {
            fin = openFileInput(TEXT_FILE);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            textView.setText(text);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void saveCesarData(View view){
        hideKeyboard(act);
        FileOutputStream fos = null;
        String editKey = ((EditText) findViewById(R.id.cesar_key_inpt)).getText().toString();
        String editText = ((EditText) findViewById(R.id.cesar_text_inpt)).getText().toString();
        String textView = ((TextView)findViewById(R.id.result_text)).getText().toString();
        try {

            fos = openFileOutput(TEXT_FILE, MODE_PRIVATE);
            fos.write(textView.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        try {

            fos = openFileOutput(PARAMS_FILE, MODE_PRIVATE);
            fos.write((editKey+"\n").getBytes());
            fos.write(editText.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public String cipherTextCesar(String text, int key){
        String result = "";
        for(int i =0;i<text.length();i++){
            char temp = text.charAt(i);
            String temp2 = Character.toString(Character.toLowerCase(temp));
            int index = arrSymbols.indexOf(temp2);
            index+=key;
            while(index<0){
                index+= arrSymbols.size();
            }

            index%=arrSymbols.size();
            if(Character.isUpperCase(temp)) {
                result+= arrSymbols.get(index).toUpperCase();
            }
            else{
                result+= arrSymbols.get(index);
            }
        }
        return result;
    }

    public void cipherCesar(View view){
        hideKeyboard(act);
        String key0 = ((EditText)findViewById(R.id.cesar_key_inpt)).getText().toString();
        if(!key0.matches("[-]?[1-9][0-9]*")){
            Toast.makeText(this, "Введіть ціле число як ключ!", Toast.LENGTH_SHORT).show();
            return;
        }
        int key = Integer.parseInt(key0);
        String text = ((EditText)findViewById(R.id.cesar_text_inpt)).getText().toString();
        if(!text.matches("[a-zA-Z]*")){
            Toast.makeText(this, "Введіть a-zA-Z символи як текст!", Toast.LENGTH_SHORT).show();
            return;
        }
        String ciphered = cipherTextCesar(text,key);
        TextView v = (TextView) findViewById(R.id.result_text);
        v.setText(ciphered);

    }

    public void copyTop(View view){
        hideKeyboard(act);
        EditText et = (EditText) findViewById(R.id.cesar_text_inpt);
        TextView tv = (TextView) findViewById(R.id.result_text);
        String tvtxt = tv.getText().toString();
        et.setText(tvtxt);

    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void decipherCesar(View view){
        hideKeyboard(act);
        String key0 = ((EditText)findViewById(R.id.cesar_key_inpt)).getText().toString();
        if(!key0.matches("[-]?[1-9][0-9]*")){
            Toast.makeText(this, "Введіть ціле число як ключ!", Toast.LENGTH_SHORT).show();
            return;
        }
        int key = Integer.parseInt(key0);
        String text = ((EditText)findViewById(R.id.cesar_text_inpt)).getText().toString();
        if(!text.matches("[a-zA-Z]*")){
            Toast.makeText(this, "Введіть a-zA-Z символи як текст!", Toast.LENGTH_SHORT).show();
            return;
        }
        String ciphered = cipherTextCesar(text,-key);
        TextView v = (TextView) findViewById(R.id.result_text);
        v.setText(ciphered);
    }
}