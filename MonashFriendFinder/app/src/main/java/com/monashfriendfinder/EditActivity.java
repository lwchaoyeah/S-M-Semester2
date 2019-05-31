package com.monashfriendfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class EditActivity extends AppCompatActivity {


    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.content)
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Log.i("as",intent.getStringExtra("title"));
        title.setText(intent.getStringExtra("title"));
    }

    void readyData(){
        String userInput = content.getText().toString().trim();
        Intent i = new Intent();
        Bundle bd = new Bundle();
        if(!userInput.isEmpty()){
            bd.putString("status","good");
            bd.putString("content",userInput);
            i.putExtras(bd);
            EditActivity.this.setResult(RESULT_OK, i);
        }else{
            bd.putString("status","bad");
            i.putExtras(bd);
            EditActivity.this.setResult(RESULT_OK, i);
        }
    }

    //not confirm the condition
    void notrReadyData(){
        //String userInput = content.getText().toString().trim();
        Intent i = new Intent();
        Bundle bd = new Bundle();
        bd.putString("status","bad");
        i.putExtras(bd);
        EditActivity.this.setResult(RESULT_OK, i);
    }

    @OnClick(R.id.tv_save)
    void editSave(){
        readyData();
        super.onBackPressed();
    }

    @OnClick(R.id.tv_cancel)
    void editCancel(){
        notrReadyData();
        super.onBackPressed();
    }

    @Override
    public void onBackPressed(){
        notrReadyData();
        super.onBackPressed();
    }
}

