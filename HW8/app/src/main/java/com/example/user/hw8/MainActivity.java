package com.example.user.hw8;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<CharSequence> array;
    private Spinner spinner;
    private DatePicker datePicker;
    private CalendarView calendarView;
    private EditText datetext, moneytext;
    private Button inputbtn, recordbtn;
    private Intent intent;
    private ArrayList<String> data;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        datePicker = (DatePicker) findViewById(R.id.datapicker);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        datetext = (EditText) findViewById(R.id.datetext);
        moneytext = (EditText) findViewById(R.id.moneytext);
        inputbtn = (Button) findViewById(R.id.inputbtn);
        recordbtn = (Button) findViewById(R.id.recordbtn);


        inputbtn.setOnClickListener(BtnClick);
        recordbtn.setOnClickListener(BtnClick);
        //datePicker.setOnDateChangedListener(DateChangeListener);
        calendarView.setOnDateChangeListener(CalenderChangeListener);
        array = ArrayAdapter.createFromResource(this,R.array.item,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(array);
        data = new ArrayList<>();
        intent = new Intent();
        intent.setClass(MainActivity.this,dataActivity.class);

        String result = String.valueOf(datePicker.getYear()) + "/"  + String.valueOf(datePicker.getMonth() + 1) + "/" + String.valueOf(datePicker.getDayOfMonth());
        datetext.setText(result);
    }

    public DatePicker.OnDateChangedListener DateChangeListener = new DatePicker.OnDateChangedListener()
    {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int day)
        {
            String result = String.valueOf(year) + "/"  + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(day);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,monthOfYear,day);
            calendarView.setDate(calendar.getTimeInMillis());
            datetext.setText(result);
        }
    };


    public CalendarView.OnDateChangeListener CalenderChangeListener = new CalendarView.OnDateChangeListener()
    {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day)
        {
            String result = String.valueOf(year) + "/"  + String.valueOf(month + 1) + "/" + String.valueOf(day);
            datetext.setText(result);
            datePicker.updateDate(year,month,day);
        }
    };


    public View.OnClickListener BtnClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.inputbtn)
            {
                if(moneytext.length() == 0)
                {
                    Toast.makeText(MainActivity.this, "金額是空的", Toast.LENGTH_SHORT).show();
                    return;
                }

                String money = moneytext.getText().toString();
                String item = spinner.getSelectedItem().toString();
                String date = datetext.getText().toString();
                String result = "項目" + String.valueOf(count++) + "  " + date + "  " +item + "  " + money;
                data.add(result);
                Toast.makeText(MainActivity.this,money, Toast.LENGTH_SHORT).show();

            }
            else if (v.getId() == R.id.recordbtn)
            {
                intent.putStringArrayListExtra("data",data);
                startActivity(intent);
            }
        }
    };




}
