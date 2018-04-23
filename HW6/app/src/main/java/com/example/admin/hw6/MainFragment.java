package com.example.admin.hw6;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    public enum GameResultType {
        TYPE_1, TYPE_2, HIDE
    }

    // 所屬的 Activity 必須實作以下介面中的callback方法
    public interface CallbackInterface {
        void updateGameResult(int iCountSet, int iCountPlayerWin, int iCountComWin, int iCountDraw);

        void enableGameResult(GameResultType type);
    }

    private CallbackInterface mCallback;

    public int[] diceImg = new int[]{
            R.drawable.dice01, R.drawable.dice02, R.drawable.dice03,
            R.drawable.dice04, R.drawable.dice05, R.drawable.dice06
    };

    private ImageButton mImgDice;
    private TextView mTxtResult;

    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0, miCountPlayerWin = 0, miCountComWin = 0, miCountDraw = 0;

    private Button mBtnShowResult1, mBtnShowResult2, mBtnHiddenResult;

    private boolean mbShowResult = false;

    public boolean isDiceRolling = false;

    public MainFragment() {
        // Required empty public constructor

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (CallbackInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "must implement GameFragment.CallbackInterface.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 必須先呼叫getView()取得程式畫面物件，然後才能呼叫它的
        // findViewById()取得介面物件
        mTxtResult = (TextView) getView().findViewById(R.id.txtResult);
        mImgDice = (ImageButton) getView().findViewById(R.id.imgDice);

        // 以下介面元件是在另一個Fragment中，因此必須呼叫所屬的Activity
        // 才能取得這些介面元件
/*
        mEdtCountSet = (EditText) getActivity().findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText) getActivity().findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText) getActivity().findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText) getActivity().findViewById(R.id.edtCountDraw);
*/

        mImgDice.setOnClickListener(imgDiceOnClick);

        mBtnShowResult1 = (Button) getView().findViewById(R.id.btnShowResult1);
        mBtnShowResult2 = (Button) getView().findViewById(R.id.btnShowResult2);
        mBtnHiddenResult = (Button) getView().findViewById(R.id.btnHiddenResult);

        mBtnShowResult1.setOnClickListener(btnShowResult1OnClick);
        mBtnShowResult2.setOnClickListener(btnShowResult2OnClick);
        mBtnHiddenResult.setOnClickListener(btnHiddenResultOnClick);


    }

    public void resultDice() {
        int num = (int) (Math.random() * 6);
        mImgDice.setImageDrawable(getResources().getDrawable(diceImg[num]));
        miCountSet += 1;
        String result;
        switch (num / 2) {
            case 0:
                result = getString(R.string.result) + getString(R.string.player_win);
                mTxtResult.setText(result);
                miCountPlayerWin += 1;
                break;
            case 1:
                result = getString(R.string.result) + getString(R.string.player_draw);
                mTxtResult.setText(result);
                miCountDraw += 1;
                break;
            case 2:
                result = getString(R.string.result) + getString(R.string.player_lose);
                mTxtResult.setText(result);
                miCountComWin += 1;
                break;
        }

        mCallback.updateGameResult(miCountSet, miCountPlayerWin, miCountComWin, miCountDraw);
    }

    private View.OnClickListener imgDiceOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            //先檢查骰子是否還再動
            if (MainFragment.this.isDiceRolling) return;
            MainFragment.this.isDiceRolling = true;

            //從動畫資源中取得動畫檔，設定給ImgeButton物件，然後開始播放
            Resources res = getResources();
            final AnimationDrawable animDraw = (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
            mImgDice.setImageDrawable(animDraw);
            animDraw.start();
            //計時
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    animDraw.stop();
                    MainFragment.this.resultDice();
                    MainFragment.this.isDiceRolling = false;
                }
            }, 2000);
        }
    };

    private View.OnClickListener btnShowResult1OnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.TYPE_1);
        }
    };

    private View.OnClickListener btnShowResult2OnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.TYPE_2);
        }
    };

    private View.OnClickListener btnHiddenResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.HIDE);
        }
    };

    public void UpdateResult() {
        mCallback.updateGameResult(miCountSet, miCountPlayerWin, miCountComWin, miCountDraw);
    }
}
