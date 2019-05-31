package com.monashfriendfinder.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.monashfriendfinder.EditActivity;
import com.monashfriendfinder.R;
import com.monashfriendfinder.friends.FriendDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MatchInputFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.age_switch)
    SwitchCompat ageSwitch;
    @BindView(R.id.age_value)
    TextView ageValue;

    @BindView(R.id.gender_switch)
    SwitchCompat genderSwitch;
    @BindView(R.id.gender_value)
    TextView genderValue;

    @BindView(R.id.unit_switch)
    SwitchCompat courseSwitch;
    @BindView(R.id.unit_value)
    TextView courseValue;

    @BindView(R.id.nationality_switch)
    SwitchCompat nationalitySwitch;
    @BindView(R.id.nationality_value)
    TextView nationalityValue;

    @BindView(R.id.studymode_switch)
    SwitchCompat studymodeSwitch;
    @BindView(R.id.studymode_value)
    TextView studymodeValue;

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_match_input, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnCheckedChanged({R.id.age_switch,R.id.gender_switch,R.id.unit_switch,R.id.nationality_switch,R.id.studymode_switch})
    void editMatchInfo(SwitchCompat s,boolean b) {
        if(b){
            Intent i = new Intent();
            i.setClass(getActivity(), EditActivity.class);
            Bundle bd = new Bundle();
            switch (s.getId()) {
                case R.id.age_switch:
                    bd.putString("title","edit age");
                    i.putExtras(bd);
                    startActivityForResult(i,21);
                    break;
                case R.id.gender_switch:
                    bd.putString("title","edit gender");
                    i.putExtras(bd);
                    startActivityForResult(i,22);
                    break;
                case R.id.unit_switch:
                    bd.putString("title","edit course");
                    i.putExtras(bd);
                    startActivityForResult(i,23);
                    break;
                case R.id.nationality_switch:
                    bd.putString("title","edit nationality");
                    i.putExtras(bd);
                    startActivityForResult(i,24);
                    break;
                case R.id.studymode_switch:
                    bd.putString("title","edit nationality");
                    i.putExtras(bd);
                    startActivityForResult(i,25);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 21){
            if(data.getStringExtra("status").equals("good")){
                ageValue.setText(data.getStringExtra("content"));
            }else{
                ageSwitch.setChecked(false);
                ageValue.setText("ignore");
            }
        }else if(requestCode == 22){
            if(data.getStringExtra("status").equals("good")){
                genderValue.setText(data.getStringExtra("content"));
            }else{
                genderSwitch.setChecked(false);
                genderValue.setText("ignore");
            }
        }
        else if(requestCode == 23){
            if(data.getStringExtra("status").equals("good")){
                courseValue.setText(data.getStringExtra("content"));
            }else{
                courseSwitch.setChecked(false);
                courseValue.setText("ignore");
            }
        }
        else if(requestCode == 24){
            if(data.getStringExtra("status").equals("good")){
                nationalityValue.setText(data.getStringExtra("content"));
            }else{
                nationalitySwitch.setChecked(false);
                nationalityValue.setText("ignore");
            }
        }
        else if(requestCode == 25) {
            if (data.getStringExtra("status").equals("good")) {
                studymodeValue.setText(data.getStringExtra("content"));
            } else {
                studymodeSwitch.setChecked(false);
                studymodeValue.setText("ignore");
            }
        }
    }

    @OnClick(R.id.btn_start_condition_match)
    public void showMatchedResult(){
        Intent i = new Intent();
        i.setClass(getActivity(),MatchFriendList.class);
        startActivity(i);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }
}
