package com.example.user.hw8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by user on 2018/5/26.
 */

public class dataActivity extends AppCompatActivity
{
    private Intent intent;
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        listview = (ListView) findViewById(R.id.listview);
        intent = getIntent();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(intent.getStringArrayListExtra("data"));
        listview.setAdapter(arrayAdapter);
    }
}
