package com.monashfriendfinder.discover;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.monashfriendfinder.FriendListViewAdapter;
import com.monashfriendfinder.R;
import com.monashfriendfinder.friends.FriendDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MatchFriendList extends AppCompatActivity {
    private ListView listView;
    private String A[][]=new String[][]{
            {"Jarry", "Fisher", "male","1995/03/09","FIT5183","China","full-time","The Shawshank Redemption"},
            {"Mike","Steven","male","1997/07/07","FIT5187","China","full-time","The Avengers 4"}};

    @Nullable

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_match_friendlist,null);
        ButterKnife.bind(this, view);
        listView = (ListView)view.findViewById(R.id.friend_listview);

        //update view according to conditions delivered from MatchInputFragment
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new FriendListViewAdapter(this, list));

        //设置listView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("---=====---------96", String.valueOf(position));
                //这里position就表示的Person实例化对象在personList里面的位置,例如当你点击第2个item的时候，position的值就为1.（因为从0开始计数）
                int posititonNum = position;
                Intent intent = new Intent(MatchFriendList.this,MatchFriendDetailActivity.class);
                intent.putExtra("friendList_positionNum",posititonNum);//给下一个活动传参数positionNum
                //避免重复请求数据库，此处需要将在FriendDetailActivity中需要展示的参数一并传过去
                //可将getData()中请求得到的数据存储在二维数组A中，根据position对应取A[i][]
                intent.putExtra("firstName",A[posititonNum][0]);
                intent.putExtra("familyName",A[posititonNum][1]);
                intent.putExtra("gender",A[posititonNum][2]);
                intent.putExtra("DoB",A[posititonNum][3]);
                intent.putExtra("unit",A[posititonNum][4]);
                intent.putExtra("nationality",A[posititonNum][5]);
                intent.putExtra("studyMode",A[posititonNum][6]);
                intent.putExtra("favoriteMovie",A[posititonNum][7]);
                startActivity(intent);
                //finish();
            }
        });
        setContentView(view);
        //return view;
    }



    /*@OnClick(value = R.id.match_friend_detail)
    void showDetail(){
        Intent i = new Intent();
        i.setClass(this, MatchFriendDetailActivity.class);
        startActivity(i);
    }*/

    @OnClick(value = R.id.change_map_view)
    public void changeMapView(){

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    //need to request data from database
    public List<Map<String, Object>> getData(){
        String strName[]=new String[]{"Jarry Fisher","Mike Steven"};
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 2; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("info",strName[i]);
            list.add(map);
        }
        return list;
    }
}