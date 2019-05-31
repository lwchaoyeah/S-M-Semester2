package com.monashfriendfinder.me;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;


import com.monashfriendfinder.Login;
import com.monashfriendfinder.R;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MeFragment extends Fragment{
    private Unbinder unbinder;
    private View view;

    private EditText birthday;

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_me, null);
        unbinder = ButterKnife.bind(this, view);
        // to enter date by calendar
        birthday = (EditText)view.findViewById(R.id.register_date_enter);
        birthday.setInputType(InputType.TYPE_NULL);
        birthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(hasFocus){
                    showDatePickerDialog();
                }
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDatePickerDialog();
            }
        });
        return view;
    }

    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                birthday.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }

    @OnClick(R.id.exit_account_finish)
    void exitAccount(){
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setClass(getActivity(), Login.class);
        startActivity(i);
    }

    //get the change of data and then send to the database
    @OnClick(R.id.update_personal_information_finish)
    void updatePersonalInfo(){
        EditText et1 = (EditText) view.findViewById(R.id.register_fname_enter);
        String builder1 = new StringBuilder(et1.getText()).toString();
        et1.setText(builder1);
        EditText et2 = (EditText) view.findViewById(R.id.register_sname_enter);
        String builder2= new StringBuilder(et2.getText()).toString();
        et2.setText(builder2);

        //Gender select button
        RadioButton mGender = (RadioButton) view.findViewById(R.id.select_male);
        RadioButton fGender = (RadioButton) view.findViewById(R.id.select_female);
        //to check if be selected
        if (mGender.isChecked()) {
            //just for example, you can change into other operations
            mGender.setChecked(true);
        } else if (fGender.isChecked()) {
            fGender.setChecked(true);
        }

        Spinner et4 = (Spinner) view.findViewById(R.id.register_course_enter);
        //String builder4 = new StringBuilder(et4.getSelectedItem().toString()).toString();
        String builder4 = et4.getSelectedItem().toString();
        //use value to select corresponding item
        // if we initiate the Spinner,we can use this way
        setSpinnerItemSelectedByValue(et4,builder4);



        //Birthday can also be obtained by this way
        EditText et5=(EditText)  view.findViewById(R.id.register_date_enter);
        String builder5 = new StringBuilder(et5.getText()).toString();
        et5.setText(builder5);

        Spinner et6 = (Spinner) view.findViewById(R.id.register_studymode_enter);
        String builder6 = et6.getSelectedItem().toString();
        setSpinnerItemSelectedByValue(et6,builder6);

        EditText et7 = (EditText) view.findViewById(R.id.register_favorite_movie_height_enter);
        String builder7 = new StringBuilder(et7.getText()).toString();
        et7.setText(builder7);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.visit_bar_chart)
    public void visitBarChart(){}

    @OnClick(R.id.favorite_units_pie_graph)
    public void favoriteUnitsPieGraph(){}


    /**
     * 根据值, 设置spinner默认选中:
     * @param spinner
     * @param value
     */
    public  void setSpinnerItemSelectedByValue(Spinner spinner,String value){
        SpinnerAdapter apsAdapter= spinner.getAdapter(); //get SpinnerAdapter object
        int k= apsAdapter.getCount();
        for(int i=0;i<k;i++){
            if(value.equals(apsAdapter.getItem(i).toString())){
                //spinner.setSelection(i,true);// set default selected item
                spinner.setSelection(i);// choose corresponding item according to the value
                break;
            }
        }
    }
}
