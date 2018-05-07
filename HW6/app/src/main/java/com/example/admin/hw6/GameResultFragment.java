package com.example.admin.hw6;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameResultFragment extends AppCompatActivity {

    // 宣告有哪些元件
    private Button btnReturn;
    private EditText edtCountSet, edtCountPlayerWin, edtCountComWin, edtCountDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game_result);
        // 幫元件找ID
        btnReturn = (Button) findViewById(R.id.btnreturn);
        btnReturn.setOnClickListener(btnOnReturn);
        edtCountSet = (EditText) findViewById(R.id.edtCountSet);
        edtCountPlayerWin = (EditText) findViewById(R.id.edtCountPlayerWin);
        edtCountComWin = (EditText) findViewById(R.id.edtCountComWin);
        edtCountDraw = (EditText) findViewById(R.id.edtCountDraw);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            edtCountSet.setText(String.valueOf(bundle.getInt("miCountSet")));
            edtCountPlayerWin.setText(String.valueOf(bundle.getInt("miCountPlayerWin")));
            edtCountComWin.setText(String.valueOf(bundle.getInt("miCountComWin")));
            edtCountDraw.setText(String.valueOf(bundle.getInt("miCountDraw")));
        } else {
            Toast.makeText(this, "資料不能為空！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private View.OnClickListener btnOnReturn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //返回
            finish();
        }
    };
}
