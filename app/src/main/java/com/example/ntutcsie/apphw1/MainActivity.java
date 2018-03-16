package com.example.ntutcsie.apphw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etSex;
    private EditText etAge;
    private Button btnCheck;
    private TextView Suggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        etSex = findViewById(R.id.edtSex);
        etAge = findViewById(R.id.edtAge);
        btnCheck = findViewById(R.id.btnok);
        Suggestion = findViewById(R.id.suggestion);

        btnCheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnok:
                String sex = etSex.getText().toString().trim().toLowerCase();
                int age = Integer.valueOf(etAge.getText().toString().trim());
                if (sex.equals("male")) {
                    if (age > 35) {
                        Suggestion.setText("find couple");
                    } else if (age <= 35 && age >= 30) {
                        Suggestion.setText("get married");
                    } else {
                        Suggestion.setText("not hurry");
                    }
                } else if (sex.equals("female")) {
                    if (age > 32) {
                        Suggestion.setText("find couple");
                    } else if (age <= 32 && age >= 28) {
                        Suggestion.setText("get married");
                    } else {
                        Suggestion.setText("not hurry");
                    }
                }
        }
    }

}
