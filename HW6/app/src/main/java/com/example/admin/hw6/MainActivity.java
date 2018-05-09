package com.example.admin.hw6;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int[] diceImg = new int[]{
            R.drawable.dice01, R.drawable.dice02, R.drawable.dice03,
            R.drawable.dice04, R.drawable.dice05, R.drawable.dice06
    };

    private Button btnstart, btnresult;
    private ImageView mImgDice;
    private int countgame = 0, countplayer = 0, countcom = 0, countdraw = 0;
    private Intent intent = new Intent();
    private Bundle bundle = new Bundle();
    private Boolean isDiceRolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnstart = (Button) findViewById(R.id.btnstart);
        btnresult = (Button) findViewById(R.id.btnresult);
        mImgDice = (ImageView) findViewById(R.id.imgDice);
        intent.setClass(MainActivity.this, GameResultFragment.class);
        btnstart.setOnClickListener(btnOnclick);
        btnresult.setOnClickListener(btnOnclick);
    }

    public void dice() {
        int num = (int) (Math.random() * 6);
        mImgDice.setImageDrawable(getResources().getDrawable(diceImg[num]));
        countgame += 1;
        String result;
        switch (num / 2) {
            case 0:
                result = getString(R.string.player_win);
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                countplayer += 1;
                break;
            case 1:
                result = getString(R.string.player_draw);
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                countdraw += 1;
                break;
            case 2:
                result = getString(R.string.player_lose);
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                countcom += 1;
                break;
        }
    }

    private View.OnClickListener btnOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnstart:
                    if (isDiceRolling) return;
                    isDiceRolling = true;

                    Resources res = getResources();
                    final AnimationDrawable animDraw = (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
                    mImgDice.setImageDrawable(animDraw);
                    animDraw.start();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animDraw.stop();
                            dice();
                            isDiceRolling = false;
                        }
                    }, 2000);
                    break;

                case R.id.btnresult:
                    bundle.putInt("miCountSet",countgame);
                    bundle.putInt("miCountPlayerWin",countplayer);
                    bundle.putInt("miCountComWin",countcom);
                    bundle.putInt("miCountDraw", countdraw);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
            }
        }
    };
}

