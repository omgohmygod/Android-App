package com.example.user.hw10;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 2018/6/16.
 */

public class SearchContact extends Fragment {

    private ListView listView;
    private Highlight<String> dataAdapter;

    public SearchContact() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.searchcontact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView) getView().findViewById(R.id.data);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.clearChoices();

        dataAdapter = new Highlight<>(this.getContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(dataAdapter);

        Cursor cursor = MainActivity.sdb.rawQuery("select * from " + MainActivity.DB_TABLE,null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String data = "Name: " + cursor.getString(1) + "\n" + "PhoneNumber: " + cursor.getString(2) + "\n" + "PhoneType: " + cursor.getString(3);
                dataAdapter.add(data);
                cursor.moveToNext();
            }
            cursor.close();
        }
        dataAdapter.notifyDataSetChanged();
    }

    // 新增資到清單中
    public void addDataToList(String data) {
        dataAdapter.add(data);
        dataAdapter.notifyDataSetChanged();
        listView.clearChoices();
        listView.requestLayout();
    }


    public void setListHighlight() {
        dataAdapter.setHighlightList();
    }

    public void setListHighlight(ArrayList<String> list) {
        ArrayList<Integer> indexList = new ArrayList<>();
        int length = dataAdapter.getCount();

        for (int i = 0; i < length; i++) {
            if (list.contains(dataAdapter.getItem(i))) {
                indexList.add(i);
            }
        }

        dataAdapter.setHighlightList(indexList);
    }
}
class Highlight<T> extends ArrayAdapter<T> {

    private ArrayList<Integer> index;

    public Highlight(Context context, int resource) {
        super(context, resource);
        index = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View renderer = super.getView(position, convertView, parent);
        if (index.contains(position)) {
            renderer.setBackgroundResource(R.color.selected);
        } else {
            renderer.setBackgroundResource(R.color.normal);
        }
        return renderer;
    }

    public void setHighlightList() {
        index.clear();
    }

    public void setHighlightList(ArrayList<Integer> newIndexList) {
        index = newIndexList;
    }
}