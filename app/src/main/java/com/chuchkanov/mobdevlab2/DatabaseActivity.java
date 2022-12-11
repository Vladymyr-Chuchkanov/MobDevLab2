package com.chuchkanov.mobdevlab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    ListView userList;
    final String LOG_TAG = "myLogs";
    TextView header;
    DBHelper databaseHelper;
    Cursor userCursor;
    Activity act;
    SimpleCursorAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        act = this;
        header = findViewById(R.id.header);
        userList = findViewById(R.id.list);
        EditText temp = (EditText) findViewById(R.id.max_population);
        temp.setText("1500000");
        databaseHelper = new DBHelper(getApplicationContext());
        calc_populatiuon();
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                    hideKeyboard(act);
                    generateMap(id);
                    }

            });

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


    public void calc_populatiuon(){
        TextView tv = (TextView) findViewById(R.id.textView6);
        db = databaseHelper.getReadableDatabase();
        userCursor =  db.rawQuery("select * from "+ DBHelper.TABLE +";",null);
        int calc = 0;
        int ind = 0;
        while(userCursor.moveToNext()){
            calc+=userCursor.getInt(2);
            ind++;
        }
        tv.setText("Середнє населення - "+Double.toString(calc/ind)+"; Oбластей - "+Integer.toString(ind));

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

    public void generateMap(long id){
        db = databaseHelper.getReadableDatabase();
        userCursor =  db.rawQuery("select * from "+ DBHelper.TABLE+ " WHERE "+DBHelper.COLUMN_ID+" = ?",new String[]{Long.toString(id)});
        String name = "";
        userCursor.moveToFirst();
        name = userCursor.getString(4);

        Intent intent = new Intent(act,MapsActivity.class);
        intent.putExtra("name",name);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void selectFromDB(View v) {


        db = databaseHelper.getReadableDatabase();
        String population = ((EditText)findViewById(R.id.max_population)).getText().toString();
        if(!population.matches("[1-9][0-9]*")){
            Toast.makeText(this, "Введіть число!", Toast.LENGTH_SHORT).show();
            return;
        }
        int popul = Integer.parseInt(population);

        userCursor =  db.rawQuery("select * from "+ DBHelper.TABLE+ " WHERE "+DBHelper.COLUMN_POPULATION+" < ?",new String[]{population});
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DBHelper.COLUMN_NAME,DBHelper.COLUMN_AREA};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        hideKeyboard(this);
        header.setText("Знайдено елементів: " +  userCursor.getCount());
        userList.setAdapter(userAdapter);

    }

    public void addRow(View v){
        hideKeyboard(this);
        String inpt1 = ((EditText) findViewById(R.id.district_name_inpt)).getText().toString();
        String inpt2 = ((EditText) findViewById(R.id.district_pop_inpt)).getText().toString();
        String inpt3 = ((EditText) findViewById(R.id.district_area_inpt)).getText().toString();
        String inpt4 = ((EditText) findViewById(R.id.capital_name_inpt)).getText().toString();
        if(!inpt3.matches("[1-9][0-9]*")){
            Toast.makeText(this, "Площа повинна бути числом!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!inpt2.matches("[1-9][0-9]*")){
            Toast.makeText(this, "Популяциія повинна бути числом!", Toast.LENGTH_SHORT).show();
            return;
        }
        db = databaseHelper.getReadableDatabase();
        String str = "INSERT OR IGNORE INTO districts ("+DBHelper.COLUMN_NAME+","+DBHelper.COLUMN_POPULATION+", "+DBHelper.COLUMN_AREA+","+DBHelper.COLUMN_CAPITAL+") VALUES (\'"+inpt1+"\',"+inpt2+","+inpt3+",\'"+inpt4+"\');";
        db.execSQL(str);
        calc_populatiuon();



    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        userCursor.close();
        db.close();
    }


}