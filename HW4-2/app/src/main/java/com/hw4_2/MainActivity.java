package com.hw4_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtComPlay, mTxtResult;
    private Button mBtnScissors, mBtnStone, mBtnPaper;
    private ArrayList<String> resources = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtComPlay = (TextView) findViewById(R.id.txtComPlay);
        mTxtResult = (TextView) findViewById(R.id.txtResult);
        mBtnScissors = (Button) findViewById(R.id.btnScissors);
        mBtnStone = (Button) findViewById(R.id.btnStone);
        mBtnPaper = (Button) findViewById(R.id.btnPaper);

        resources.add(getString(R.string.play_scissors));//0
        resources.add(getString(R.string.play_stone));//1
        resources.add(getString(R.string.play_paper));//2

        mBtnScissors.setOnClickListener(btnScissorsOnClick);
        mBtnStone.setOnClickListener(btnStoneOnClick);
        mBtnPaper.setOnClickListener(btnPaperOnClick);


    }

    private View.OnClickListener btnScissorsOnClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            // 出剪刀
            GameResult result = new GameResult();
            result.playGame(1);

            // 電腦的選擇
            String strComChoice[] = {getString(R.string.play_scissors), getString(R.string.play_stone), getString(R.string.play_paper)};
            mTxtComPlay.setText(strComChoice[result.getComChoice() - 1]);

            // 顯示結果
            String strResults[] = {getString(R.string.player_lose), getString(R.string.player_win), getString(R.string.player_draw)};
            mTxtResult.setText(getString(R.string.result) + strResults[result.getResult()]);
        }
    };

    private View.OnClickListener btnStoneOnClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            // 玩家出石頭
            GameResult game = new GameResult();
            game.playGame(2);

            // 顯示電腦的選擇
            String strComChoice[] = {getString(R.string.play_scissors),getString(R.string.play_stone),getString(R.string.play_paper)};
            mTxtComPlay.setText(strComChoice[game.getComChoice() - 1]);

            // 顯示結果
            String strResults[] = {getString(R.string.player_lose),getString(R.string.player_win),getString(R.string.player_draw)};
            mTxtResult.setText(getString(R.string.result) + strResults[game.getResult()]);
        }
    };

    private View.OnClickListener btnPaperOnClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            // 玩家出布
            GameResult game = new GameResult();
            game.playGame(3);

            // 顯示電腦的選擇
            String strComChoice[] = {getString(R.string.play_scissors), getString(R.string.play_stone), getString(R.string.play_paper)};
            mTxtComPlay.setText(strComChoice[game.getComChoice() - 1]);

            // 顯示結果
            String strResults[] = {getString(R.string.player_lose), getString(R.string.player_win), getString(R.string.player_draw)};
            mTxtResult.setText(getString(R.string.result) + strResults[game.getResult()]);
        }
    };
}