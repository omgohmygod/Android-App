package npc.ntut.hw4_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //宣告變數
    private Spinner mSpnAge;
    private RadioGroup mRadGrp;
    private Button mBtnOk;
    private TextView mTxtSug;
    private TextView mTxtHob;
    private ArrayAdapter<CharSequence> arrAdapSpnMaleAge;
    private ArrayAdapter<CharSequence> arrAdapSpnFemaleAge;
    private ArrayList<CheckBox> hobbies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpnAge = (Spinner) findViewById(R.id.spnAge);
        arrAdapSpnMaleAge = ArrayAdapter.createFromResource(MainActivity.this, R.array.maleAgeRange, R.layout.support_simple_spinner_dropdown_item);
        arrAdapSpnFemaleAge = ArrayAdapter.createFromResource(MainActivity.this, R.array.femaleAgeRange, R.layout.support_simple_spinner_dropdown_item);
        mSpnAge.setAdapter(arrAdapSpnMaleAge);
        mRadGrp = (RadioGroup) findViewById(R.id.radGrpAge);

        mBtnOk = (Button) findViewById(R.id.btnOk);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mTxtHob = (TextView) findViewById(R.id.hobbies);

        hobbies.add((CheckBox) findViewById(R.id.checkBox1));
        hobbies.add((CheckBox) findViewById(R.id.checkBox2));
        hobbies.add((CheckBox) findViewById(R.id.checkBox3));
        hobbies.add((CheckBox) findViewById(R.id.checkBox4));
        hobbies.add((CheckBox) findViewById(R.id.checkBox5));
        hobbies.add((CheckBox) findViewById(R.id.checkBox6));
        hobbies.add((CheckBox) findViewById(R.id.checkBox7));
        hobbies.add((CheckBox) findViewById(R.id.checkBox8));
        hobbies.add((CheckBox) findViewById(R.id.checkBox9));
        hobbies.add((CheckBox) findViewById(R.id.checkBox10));

        /
        mBtnOk.setOnClickListener(btnOKOnClick);
        mRadGrp.setOnCheckedChangeListener(radioOnClick);
    }

    private RadioGroup.OnCheckedChangeListener radioOnClick = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            switch (checkedId) {
                case R.id.male:
                    mSpnAge.setAdapter(arrAdapSpnMaleAge);
                    break;
                case R.id.female:
                    mSpnAge.setAdapter(arrAdapSpnFemaleAge);
                    break;
            }
        }
    };


    private View.OnClickListener btnOKOnClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            MarriageSuggestion objms = new MarriageSuggestion();
            String strSex = "", strHobbies = "";
            int iAgeRange = 0;
            switch (mRadGrp.getCheckedRadioButtonId())
            {
                case R.id.male:
                    strSex = "male";
                    break;
                case R.id.female:
                    strSex = "female";
                    break;
            }
            switch (mSpnAge.getSelectedItem().toString())
            {
                case "小於30歲":
                    iAgeRange = 1;
                    break;
                case "30~40歲":
                    iAgeRange = 2;
                    break;
                case "大於40歲":
                    iAgeRange = 3;
                    break;
                case "小於28歲":
                    iAgeRange = 1;
                    break;
                case "28~35歲":
                    iAgeRange = 2;
                    break;
                case "大於35歲":
                    iAgeRange = 3;
                    break;
            }
            int size = hobbies.size();
            boolean isFirstHobbies = true;
            for (int i = 0, j = 0; i < size; i++)
            {
                if (hobbies.get(i).isChecked())
                {
                    if (!isFirstHobbies)
                        strHobbies += "，";
                    strHobbies += hobbies.get(i).getText();
                    isFirstHobbies = false;
                }
            }
            mTxtSug.setText(objms.getSuggestion(strSex, iAgeRange));
            mTxtHob.setText(strHobbies);
        }
    };
}
