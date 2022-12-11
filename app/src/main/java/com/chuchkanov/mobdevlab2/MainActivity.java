package com.chuchkanov.mobdevlab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                return true;
            case R.id.menu_item_author:
                Intent intent = new Intent(this, AuthorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            case R.id.menu_item_help:
                Intent intent1 = new Intent(this, HelpActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent1);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void chooseOption(View view){
        RadioGroup rg = (RadioGroup)findViewById(R.id.option_select_rbtng);
        int selected = rg.getCheckedRadioButtonId();
        if(selected==R.id.db_rbtn){
            Intent intent1 = new Intent(this, DatabaseActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent1);
        }else if(selected==R.id.phonebook_rbtn){
            Intent intent2 = new Intent(this, PhoneBookActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent2);
        }
        else if(selected==R.id.aphine_rbtn){
            Intent intent = new Intent(this, AffineActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }else if(selected==R.id.cesar_rbtn){
            Intent intent = new Intent(this, CesarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }


    }
}