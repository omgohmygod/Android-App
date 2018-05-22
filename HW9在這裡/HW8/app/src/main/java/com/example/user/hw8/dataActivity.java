package com.example.user.hw8;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class dataActivity extends AppCompatActivity
{
    private Intent intent;
    private ListView listview;

    private static final int   menumusic = Menu.FIRST,
            menuplaymusic = Menu.FIRST + 1,
            menustopmusic = Menu.FIRST + 2,
            menuabout = Menu.FIRST + 3,
            menuexit = Menu.FIRST + 4;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu(0, menumusic, 0, "背景音樂").setIcon(android.R.drawable.ic_media_ff);
        subMenu.add(0, menuplaymusic, 0, "播放背景音樂");
        subMenu.add(0, menustopmusic, 1,"停止播放");
        menu.add(0, menuabout, 1,"關於這程式").setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0, menuexit, 2,"結束").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case menuplaymusic:
                Intent it = new Intent(dataActivity.this, MediaPlayService.class);
                startService(it);
                return true;

            case menustopmusic:
                it = new Intent(dataActivity.this,MediaPlayService.class);
                stopService(it);
                return true;
            case menuabout:
                new AlertDialog.Builder(dataActivity.this)
                        .setTitle("關於這個程式")
                        .setMessage("選單範例程式")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener()
                                {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                        .show();
                return true;

            case menuexit:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
