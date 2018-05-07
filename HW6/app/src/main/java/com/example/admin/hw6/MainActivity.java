package com.example.admin.hw6;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 骰子的圖片陣列
    public int[] diceImg = new int[]{
            R.drawable.dice01, R.drawable.dice02, R.drawable.dice03,
            R.drawable.dice04, R.drawable.dice05, R.drawable.dice06
    };
    // 宣告有哪些元件
    private Button btnstart, btnresult;
    private ImageView mImgDice;
    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0, miCountPlayerWin = 0, miCountComWin = 0, miCountDraw = 0;
    // 本次作業重點
    private Intent intent = new Intent();
    private Bundle bundle = new Bundle();
    // 一點點的防呆 檢查動畫播完沒
    private Boolean isDiceRolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 幫元件找ID
        btnstart = (Button) findViewById(R.id.btnstart);
        btnresult = (Button) findViewById(R.id.btnresult);
        mImgDice = (ImageView) findViewById(R.id.imgDice);
        // 本次作業重點 設定物件擁有者                     // 要啟動的 Activity class
        intent.setClass(MainActivity.this, GameResultFragment.class);
        //  設置按鍵監聽
        btnstart.setOnClickListener(btnOnclick);
        btnresult.setOnClickListener(btnOnclick);
    }
    //處理輸贏、平手 用 Toast 顯示
    public void resultDice() {
        int num = (int) (Math.random() * 6);
        mImgDice.setImageDrawable(getResources().getDrawable(diceImg[num]));
        miCountSet += 1;
        String result;
        switch (num / 2) {
            case 0: // win
                result = getString(R.string.player_win);
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                miCountPlayerWin += 1;
                break;
            case 1: // draw
                result = getString(R.string.player_draw);
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                miCountDraw += 1;
                break;
            case 2: // lose
                result = getString(R.string.player_lose);
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                miCountComWin += 1;
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
                    //從動畫資源中取得動畫檔，設定給ImageView物件，然後開始播放
                    Resources res = getResources();
                    final AnimationDrawable animDraw = (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
                    mImgDice.setImageDrawable(animDraw);
                    animDraw.start(); // 啟動動畫
                    Handler handler = new Handler(); // 延遲
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animDraw.stop();
                            resultDice();
                            isDiceRolling = false;
                        }
                    }, 2000);
                    break;
                case R.id.btnresult:
                    // 先把要送出的 Data 與 key 綁起來，依序存進 bundle 裡，再把 bundle 存入 intent ，準備送出 Intent 物件要求
                    bundle.putInt("miCountSet",miCountSet);
                    bundle.putInt("miCountPlayerWin",miCountPlayerWin);
                    bundle.putInt("miCountComWin",miCountComWin);
                    bundle.putInt("miCountDraw",miCountDraw);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
            }
        }
    };
}

