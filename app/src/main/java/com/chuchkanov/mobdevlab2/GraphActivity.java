package com.chuchkanov.mobdevlab2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphActivity extends AppCompatActivity {


    private static ArrayList<String> arrSymbols= new ArrayList<String>() {
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
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

        Bundle args = getIntent().getExtras();
        int[]num = args.getIntArray("arr").clone();
        int[]num2 = args.getIntArray("arr").clone();
        String legend="";
        Arrays.sort(num2);
        int []skip=new int[]{-1,-1,-1,-1,-1};
        DataPoint[] temp = new DataPoint[5];
        for(int i =0;i<5;i++){
            int ind1 = 0;


            for(int j =0;j<num.length;j++){
                if(num2[num2.length-1-i]==num[j]){
                    boolean flag=false;
                    for(int h =0;h<5;h++){
                        if(j==skip[h]){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        continue;
                    }
                    skip[i]=j;
                    ind1=j;
                    break;
                }
            }
            legend+=i+" - "+arrSymbols.get(ind1)+";  ";
            temp[i]=(new DataPoint(i,num2[num2.length-1-i]));

        }
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(temp);
        TextView tv1 = (TextView)findViewById(R.id.legend_tv);
        tv1.setText(legend);

        GraphView graph = (GraphView) findViewById(R.id.graph);


        graph.addSeries(series);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(50);

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
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
}