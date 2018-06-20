package com.example.user.hw11;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "Debug";
    public static ContentResolver resolve;

    private ViewPager viewpage;

    private AddContact addContact;
    private SearchContact searchContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionPageAdapter mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        viewpage = findViewById(R.id.viewPager);
        viewpage.setAdapter(mSectionPageAdapter);

        TabLayout mTableLayout = findViewById(R.id.tabLayout);
        mTableLayout.setupWithViewPager(viewpage);
        mTableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                InputMethodManager inputMethodManager = ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE));
                try {
                    if(inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(MainActivity.this.getCurrentFocus()).getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                catch (Exception e){}

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        addContact = new AddContact();
        searchContact = new SearchContact();
        resolve = getContentResolver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu, menu);
        android.widget.SearchView searchView = (android.widget.SearchView) menu.findItem(R.id.itemSearch).getActionView();
        searchView.setOnQueryTextListener(searchView_OnQuery);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        if (item.getItemId() == R.id.add_new_contact) {
            if(viewpage.getCurrentItem() !=0){
                viewpage.setCurrentItem(0);
                return true;
            }
            ContentValues newData = addContact.getContentValues();
            resolve.insert(Content.CONTENT_URI, newData);
            searchContact.updateListData();
            Toast.makeText(this, "加入成功", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId() == R.id.deleteData){
            resolve.delete(Content.CONTENT_URI,null, null);
            searchContact.setListHighlight();
            searchContact.updateListData();
            Toast.makeText(this, "已刪除", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private final SearchView.OnQueryTextListener searchView_OnQuery = new SearchView.OnQueryTextListener() {
        @SuppressLint("Recycle")
        @Override
        public boolean onQueryTextSubmit(String query) {
            Cursor cursor = null;
            if (!query.equals("")) {
                cursor = resolve.query(Content.CONTENT_URI,
                        new String[]{"_id", "name", "phoneNumber", "phoneType"},"name=" + "\"" + query + "\"",null, null);
            }

            if (cursor == null)
                return false;

            if (cursor.getCount() == 0) {
                Toast.makeText(MainActivity.this, "找不到資料", Toast.LENGTH_LONG).show();
                searchContact.setListHighlight();
            }
            else {
                ArrayList<Integer> dataList = new ArrayList<>();
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    dataList.add(cursor.getInt(0));
                    cursor.moveToNext();
                }
                searchContact.setListHighlight(dataList);
                if(viewpage.getCurrentItem() !=1){
                    viewpage.setCurrentItem(1);
                }
            }

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) { return false; }
    };

    public class SectionPageAdapter extends FragmentPagerAdapter {
        SectionPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = addContact;
                    break;
                case 1:
                    fragment = searchContact;
                    break;
            }
            return fragment;
        }


        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "AddNewContact";
                case 1:
                    return "SearchContact";
                default:
                    return null;
            }
        }

    }
}