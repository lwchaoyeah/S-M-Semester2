package com.monashfriendfinder.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.monashfriendfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendDetailActivity extends AppCompatActivity {

    @BindView(R.id.back)
    TextView back;

    @BindView(R.id.delete)
    TextView delete;

    //data to display obtained from FriendListFragment
    @BindView(R.id.tv_username)
    TextView username;

    @BindView(R.id.tv_gender)
    TextView gender;

    @BindView(R.id.tv_DoB)
    TextView DoB;

    @BindView(R.id.tv_unit)
    TextView unit;

    @BindView(R.id.tv_nationality)
    TextView nationality;

    @BindView(R.id.tv_studymode)
    TextView studyMode;

    @BindView(R.id.tv_favoriteMovie)
    TextView favoriteMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_friend_detail);
        ButterKnife.bind(this);

        // get data and update view
        Intent intent = getIntent();
        String firName=intent.getStringExtra("firstName");
        String famName=intent.getStringExtra("familyName");
        String name = firName+" "+famName;
        username.setText(name);
        gender.setText(intent.getStringExtra("gender"));
        DoB.setText(intent.getStringExtra("DoB"));
        unit.setText(intent.getStringExtra("unit"));
        nationality.setText(intent.getStringExtra("nationality"));
        studyMode.setText(intent.getStringExtra("studyMode"));
        favoriteMovie.setText(intent.getStringExtra("favoriteMovie"));
    }

    //call for movie API to search for detailed information
    @OnClick(R.id.tv_favoriteMovie)
    void searchMovie(){

    }

    @OnClick(R.id.back)
    void cilckBack(){
        super.onBackPressed();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
