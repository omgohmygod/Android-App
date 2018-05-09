package com.example.admin.hw6;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameResultFragment extends AppCompatActivity {


    private Button btnReturn;
    private EditText countgame, countplayer, countcom, countdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game_result);
        btnReturn = (Button) findViewById(R.id.btnreturn);
        btnReturn.setOnClickListener(btnOnReturn);
        countgame = (EditText) findViewById(R.id.edtCountSet);
        countplayer = (EditText) findViewById(R.id.edtCountPlayerWin);
        countcom = (EditText) findViewById(R.id.edtCountComWin);
        countdraw = (EditText) findViewById(R.id.edtCountDraw);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            countgame.setText(String.valueOf(bundle.getInt("miCountSet")));
            countplayer.setText(String.valueOf(bundle.getInt("miCountPlayerWin")));
            countcom.setText(String.valueOf(bundle.getInt("miCountComWin")));
            countdraw.setText(String.valueOf(bundle.getInt("miCountDraw")));
        } else {
            Toast.makeText(this, "Data is empty！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private View.OnClickListener btnOnReturn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
