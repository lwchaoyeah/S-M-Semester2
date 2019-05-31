package com.monashfriendfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {

    @BindView(R.id.register)
    TextView register;

    @BindView(R.id.submit)
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Spannable spannable = new SpannableString("Do not have an account? Register");
        spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(Login.this, R.color.colorPrimaryDark)), 24, 32, Spanned.SPAN_POINT_MARK);
        register.setText(spannable);
    }
    @OnClick(value = R.id.submit)
    void submitLogin(){
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setClass(Login.this,MainActivity.class);
        startActivity(i);
    }

    @OnClick(value = R.id.register)
    void newRegister(){
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setClass(Login.this, Register.class);
        startActivity(i);
    }
}
