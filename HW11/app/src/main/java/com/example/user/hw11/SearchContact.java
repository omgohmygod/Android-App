package com.example.user.hw11;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class SearchContact extends Fragment {
    private Highligh<String> dataAdapter;
    public SearchContact() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.searchcontact, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        assert view != null;
        ListView lsvContactList = view.findViewById(R.id.data);
        lsvContactList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lsvContactList.clearChoices();
        dataAdapter = new Highligh<>(this.getContext(), android.R.layout.simple_list_item_1);
        lsvContactList.setAdapter(dataAdapter);

        Cursor cursor = MainActivity.resolve.query(Content.CONTENT_URI, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                dataAdapter.addData(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                cursor.moveToNext();
            }
            cursor.close();
        }
        dataAdapter.notifyDataSetChanged();
    }

    public void updateListData() {
        Cursor cursor = MainActivity.resolve.query(Content.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            dataAdapter.clearAllData();
            while (!cursor.isAfterLast()) {
                dataAdapter.addData(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                cursor.moveToNext();
            }
            cursor.close();
            dataAdapter.notifyDataSetChanged();
            dataAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(getContext(), "無法更新", Toast.LENGTH_LONG).show();
        }
    }

    public void setListHighlight() {
        dataAdapter.setHighlightList();
    }

    public void setListHighlight(ArrayList<Integer> list) {
        dataAdapter.setHighlightList(list);
    }
}
