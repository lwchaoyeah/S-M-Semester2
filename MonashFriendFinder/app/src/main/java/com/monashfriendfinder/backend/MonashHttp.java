package com.monashfriendfinder.backend;

import com.google.gson.JsonObject;


import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;

public class MonashHttp {
    // Send HTTP request and get response
    /*
    *
    * TODO
     */
    public static JsonObject sendRequest(String url, Map params,String method){
        String res = "";
        URL u = null;
        HttpURLConnection conn = null;
        JsonObject result = new JsonObject();
        StringBuilder tempParams = new StringBuilder();
        try{
            int pos = 0;
            for (Object key : params.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode((String)params.get(key),"utf-8")));
                pos++;
            }
            if(method == "GET"){
                u = new URL(url+tempParams.toString());
            }
            conn = (HttpURLConnection) u.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            if(method.equals("POST")){
                byte[] postData = tempParams.toString().getBytes();
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.write(postData);
                dos.flush();
                dos.close();
            }
            Scanner inStream = new Scanner(conn.getInputStream());
            while (inStream.hasNextLine()){
                res += inStream.nextLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }
        result = new JsonObject();
        result.getAsJsonObject(res);
        return result;
    };
    public static boolean sendUpdateRequest(String url, Map params,String method){
        String res = "";
        URL u = null;
        HttpURLConnection conn = null;
        JsonObject result = new JsonObject();
        StringBuilder tempParams = new StringBuilder();
        try{
            int pos = 0;
            for (Object key : params.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode((String)params.get(key),"utf-8")));
                pos++;
            }
            if(method == "GET"){
                u = new URL(url+tempParams.toString());
            }
            u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            if(method.equals("POST")){
                byte[] postData = tempParams.toString().getBytes();
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.write(postData);
                dos.flush();
                dos.close();
            }
            Scanner inStream = new Scanner(conn.getInputStream());
            while (inStream.hasNextLine()){
                res += inStream.nextLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }
        result = new JsonObject();
        result.getAsJsonObject(res);
        if(result.get("status").toString().equals("2000")){
            return true;
        }else{
            return false;
        }
    };
}
