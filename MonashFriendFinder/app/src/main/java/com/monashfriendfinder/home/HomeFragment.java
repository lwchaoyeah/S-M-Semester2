package com.monashfriendfinder.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.monashfriendfinder.R;
import com.weather.WeatherBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends Fragment {
    private MyTask mTask;
    private WeatherBean w;
    private ImageView sunView,cloudyView;
    private TextView tepView,updateTmView,locationView,nameToWelcome;

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, null);

        //需要实现请求获得姓名
        String name="Tom Cruise";
        nameToWelcome = (TextView)  view.findViewById(R.id.welcome);
        nameToWelcome.setText("Dear "+name);

        sunView=(ImageView) view.findViewById(R.id.img_sun);
        cloudyView=(ImageView) view.findViewById(R.id.img_cloudy);
        tepView=(TextView) view.findViewById(R.id.tep_text);
        updateTmView=(TextView)  view.findViewById(R.id.update_t);
        locationView=(TextView)  view.findViewById(R.id.location);
        String location=getLocation();
        String url = "https://free-api.heweather.net/s6/weather/now?location="+location+"&key=7a866945b78a4c71b3b6e029937b632d";
        mTask=new MyTask();
        mTask.execute(url);
        return view;
    }

    private class MyTask extends AsyncTask<String, Integer, List<String>> {
        private int n=5;
        private String result_str;
        protected volatile List<String> weatherStr=new ArrayList<String>();
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<String> doInBackground(String... params) {

            try {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    String url_str=params[0];
                    URL url = new URL(url_str);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    result_str=result.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

                Gson gosn = new Gson();
                w = new WeatherBean();
                w = gosn.fromJson(result_str, WeatherBean.class);
                String admin_area = w.getHeWeather6().get(0).getBasic().getAdmin_area();
                weatherStr.add(admin_area);
                String parent_city = w.getHeWeather6().get(0).getBasic().getParent_city();
                weatherStr.add(parent_city);
                String cnty = w.getHeWeather6().get(0).getBasic().getCnty();
                weatherStr.add(cnty);
                String updateTime = w.getHeWeather6().get(0).getUpdate().getLoc();
                weatherStr.add(updateTime);
                String cond_txt = w.getHeWeather6().get(0).getNow().getCond_txt();
                weatherStr.add(cond_txt);
                String tmp = w.getHeWeather6().get(0).getNow().getTmp();
                weatherStr.add(tmp);
            }  catch (Exception e) {
                Log.d("exception",e.getMessage());
            }
            return weatherStr;
        }

        @Override
        protected void onProgressUpdate(Integer... progresses) {}

        @Override
        protected void onPostExecute(List<String> result) {
            if (result.size()>0) {
                String cond_txt;
                if(result.get(4).equals("晴")){
                    sunView.setVisibility(View.VISIBLE);
                    cloudyView.setVisibility(View.GONE);
                    cond_txt="Sunny";
                }
                else {
                    sunView.setVisibility(View.GONE);
                    cloudyView.setVisibility(View.VISIBLE);
                    cond_txt="Cloudy";
                }
                tepView.setText(cond_txt+" "+result.get(5)+"℃");
                updateTmView.setText(result.get(3));
                if (result.get(0).equals(result.get(1))) {
                    locationView.setText(result.get(0));
                }
                else {
                    locationView.setText("Jiangsu Suzhou");
                    //locationView.setText(result.get(0)+" "+result.get(1));
                }
            }
            else{
                Log.d("UI_update1", "no_update");
            }
        }
        @Override
        protected void onCancelled() {}
    }

    String getLocation(){
        String str = "suzhou";
        return str;
    }
}
