package com.chuchkanov.mobdevlab2;

import static com.chuchkanov.mobdevlab2.CesarActivity.hideKeyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AffineActivity extends AppCompatActivity {
    private final static String PARAMS_FILE="params_affine.txt";
    private final static String TEXT_FILE="result_affine.txt";
    Activity act;
    private static ArrayList<String> arrSymbols= new ArrayList<String>() {
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affine);
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
        act = this;
        EditText et1 = (EditText) findViewById(R.id.affine_key_inpt);

        View.OnFocusChangeListener temp1 = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(act);
            }
        };
        et1.setOnFocusChangeListener(temp1);
        EditText et2 = (EditText) findViewById(R.id.affine_text_inpt);
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
            case R.id.menu_item_help:
                Intent intent = new Intent(this, HelpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    public void uploadAffineData(View view){
        hideKeyboard(act);
        FileInputStream fin = null;
        TextView textView = findViewById(R.id.result_text);
        EditText editKey = (EditText) findViewById(R.id.affine_key_inpt);
        EditText editText = (EditText) findViewById(R.id.affine_text_inpt);
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

    public void uploadAffineResult(View view){
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

    public int gcd(int a, int b) {
        int c;
        while (b > 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return Math.abs(a);
    }

    public static int Evklid (int a, int n) {
        if(a<0){
            a*=-1;
        }
        int s1 = 1, s2 = 0;
        int t1 = 0, t2 = 1;
        int m =n;
        while(n != 0) {
            int quotient = a / n;
            int r = a % n;
            a = n;
            n = r;
            int tempS = s1 - s2 * quotient;
            s1 = s2;
            s2 = tempS;
            int tempR = t1 - t2 * quotient;
            t1 = t2;
            t2 = tempR;
        }
        if(s1 <0){
            s1=s1+m;
        }
        return s1;
    }

    public void saveAffineData(View view){
        hideKeyboard(act);
        FileOutputStream fos = null;
        String editKey = ((EditText) findViewById(R.id.affine_key_inpt)).getText().toString();
        String editText = ((EditText) findViewById(R.id.affine_text_inpt)).getText().toString();
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

    public String cipherTextAffine(String text, int key1, int key2){
        hideKeyboard(act);
        String result = "";
        for(int i =0;i<text.length();i++){
            char temp = text.charAt(i);
            String temp2 = Character.toString(Character.toLowerCase(temp));
            int index = arrSymbols.indexOf(temp2);
            index=key1*index+key2;
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

    public String decipherTextAffine(String text, int key1, int key2){
        String result = "";
        for(int i =0;i<text.length();i++){
            char temp = text.charAt(i);
            String temp2 = Character.toString(Character.toLowerCase(temp));
            int index = arrSymbols.indexOf(temp2);
            int m = arrSymbols.size();
            int newindex = (Evklid(key1,m)*(index-key2))%(m);
            if(newindex<0){
                newindex+=m;
            }
            if(Character.isUpperCase(temp)) {
                result+= arrSymbols.get(newindex).toUpperCase();
            }
            else{
                result+= arrSymbols.get(newindex);
            }
        }
        return result;
    }

    public void cipherAffine(View view){
        String key0 = ((EditText)findViewById(R.id.affine_key_inpt)).getText().toString();
        if(!key0.matches("[ ]?[1-9][0-9]*[ ]?[,]{1}[ ]?[1-9][0-9]*[ ]?")){
            Toast.makeText(this, "Введіть два цілих числа a,b >0 як ключ!", Toast.LENGTH_SHORT).show();
            return;
        }
        int key1 = Integer.parseInt(key0.split(",")[0].trim());
        int key2 = Integer.parseInt(key0.split(",")[1].trim());
        if( gcd(key1,arrSymbols.size())!=1){
            Toast.makeText(this, "a має бути взаємно простим з "+Integer.toString(arrSymbols.size()), Toast.LENGTH_SHORT).show();
            return;
        }
        String text = ((EditText)findViewById(R.id.affine_text_inpt)).getText().toString();
        if(!text.matches("[a-zA-Z]*")){
            Toast.makeText(this, "Введіть a-zA-Z символи як текст!", Toast.LENGTH_SHORT).show();
            return;
        }
        String ciphered = cipherTextAffine(text,key1,key2);
        TextView v = (TextView) findViewById(R.id.result_text);
        v.setText(ciphered);

    }

    public void copyTopAffine(View view){
        hideKeyboard(act);
        EditText et = (EditText) findViewById(R.id.affine_text_inpt);
        TextView tv = (TextView) findViewById(R.id.result_text);
        String tvtxt = tv.getText().toString();
        et.setText(tvtxt);

    }

    public void decipherAffine(View view){
        String key0 = ((EditText)findViewById(R.id.affine_key_inpt)).getText().toString();
        if(!key0.matches("[ ]?[1-9][0-9]*[ ]?[,]{1}[ ]?[1-9][0-9]*[ ]?")){
            Toast.makeText(this, "Введіть два цілих числа a,b >0 як ключ!", Toast.LENGTH_SHORT).show();
            return;
        }
        int key1 = Integer.parseInt(key0.split(",")[0].trim());
        int key2 = Integer.parseInt(key0.split(",")[1].trim());
        if( gcd(key1,arrSymbols.size())!=1){
            Toast.makeText(this, "a має бути взаємно простим з "+Integer.toString(arrSymbols.size()), Toast.LENGTH_SHORT).show();
            return;
        }
        String text = ((EditText)findViewById(R.id.affine_text_inpt)).getText().toString();
        if(!text.matches("[a-zA-Z]*")){
            Toast.makeText(this, "Введіть a-zA-Z символи як текст!", Toast.LENGTH_SHORT).show();
            return;
        }
        String deciphered = decipherTextAffine(text,key1,key2);
        TextView v = (TextView) findViewById(R.id.result_text);
        v.setText(deciphered);
    }

    public void buildGraph(View view){
        hideKeyboard(act);
        int[] num=new int[arrSymbols.size()];
        for(int i =0;i<arrSymbols.size();i++){
            num[i]=0;
        }
        String text = ((TextView)findViewById(R.id.result_text)).getText().toString();
        for(int i =0;i<text.length();i++) {
            char temp = text.charAt(i);
            String temp2 = Character.toString(Character.toLowerCase(temp));
            int index = arrSymbols.indexOf(temp2);
            num[index]++;
        }
        Intent intent = new Intent(this,GraphActivity.class);
        intent.putExtra("arr",num);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}