package com.example.user.hw10;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by user on 2018/6/16.
 */

public class AddContact extends Fragment {

    private ArrayAdapter<CharSequence> arrayAdapter;
    private Spinner mSpinner;
    private EditText mEditTextName, mEditTextPhoneNumber;

    public AddContact() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addcontact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSpinner = (Spinner) getView().findViewById(R.id.spinner);
        mEditTextName = (EditText) getView().findViewById(R.id.editName);
        mEditTextPhoneNumber = (EditText) getView().findViewById(R.id.editPhone);
        // spinner setting
        arrayAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.phonetype,android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(arrayAdapter);
    }

    public ContentValues getContentValues() {
        ContentValues newData = new ContentValues();
        newData.put("name", mEditTextName.getText().toString());
        newData.put("phoneNumber", mEditTextPhoneNumber.getText().toString());
        newData.put("phoneType", mSpinner.getSelectedItem().toString());
        return newData;
    }
}