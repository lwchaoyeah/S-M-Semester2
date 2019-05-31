package com.monashfriendfinder.backend;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;


/**
 * this class is intended for network communication in the app
 * @authors Group 4
 * date 2019/04
 */

public class API {

    // define this var for server ip in case of server ip changing
    private String serverIp;


    /**
     * consturct method
     * accept parameters for server ip
     * @return
     */

    public void API(String ip){
        this.serverIp = ip;
    }

    /**
     * method for login
     * @params email and password
     * @return json Object
     * {state:2000} if login is correct
     * {state:5000} if login is incorrect
     */
    public JsonObject login(String email,String password){
        Map params = new HashMap();
        params.put("email",email);
        params.put("password",password);
        return MonashHttp.sendRequest(serverIp+"/login",params,"POST");
    }

    /**
     * method for register
     * @params multi paraeters
     * @return json Object
     */

    public JsonObject register(String age,String studymode,String firname,String famname){
        Map params = new HashMap();
        params.put("age",age);
        params.put("firname",firname);
        params.put("famname",famname);
        params.put("studymode",studymode);
        return MonashHttp.sendRequest(serverIp+"/login",params,"POST");
    }


    /**
     * method for getting the information in Fragment 'Me'
     * @params session should be included in the HTTP header
     * @return json Object
     */
    public JsonObject getCurrentUser(){
        return MonashHttp.sendRequest(serverIp+"/getMyInformation",new HashMap(),"GET");
    }

    /**
     * method for getting the friend list
     * @params session should be included in the HTTP header
     * @return json Object
     */
    public JsonObject getFriendList(){
        return MonashHttp.sendRequest(serverIp+"/getFriendList",new HashMap(),"GET");
    }

    /**
     * method for getting the friend detail
     * @params friendid
     * @params session should be included in the HTTP header
     * @return json Object
     */
    public JsonObject getFriendList(String friendid){
        Map param = new HashMap();
        param.put("friendid",friendid);
        return MonashHttp.sendRequest(serverIp+"/getFriendList",param,"GET");
    }

    /**
     * method for updating current user's information
     * @params what kind of information should be updated
     * in the form of map(<string key,string value>)
     * @params session should be included in the HTTP header
     * @return boolean
     * true if update succeded or false if update failed
     */
    public boolean getFriendList(String key,String value){
        Map params = new HashMap();
        params.put("key",key);
        params.put("value",value);
        return MonashHttp.sendUpdateRequest(serverIp+"/updateMyInfo",params,"POST");
    }

    /**
     * method for matching friends
     * @params matching condition
     * in the form of map(<string key,string value>)
     * @params session should be included in the HTTP header
     * @return json Object for the possible future friends
     */
    public JsonObject matchFriends(Map params){
        return MonashHttp.sendRequest(serverIp+"/getStudentList",params,"POST");
    }

    /**
     * method for getting a friend's detail information
     * @params friendid
     * @return json Object for that friend
     */
    public JsonObject getFriend(String friendid){
        Map params = new HashMap();
        params.put("friendid",friendid);
        return MonashHttp.sendRequest(serverIp+"/getFriend",params,"POST");
    }

    /**
     * method for getting favorite unites information
     * @params user session in header
     * @return favorite units
     */
    public JsonObject getFavunt(){
        return MonashHttp.sendRequest(serverIp+"/getFavunt",new HashMap(),"GET");
    }

    /**
     * method for getting my visit frequences
     * @params user session in header
     * @return the place visiting times
     */
    public JsonObject getFrequency(){
        return MonashHttp.sendRequest(serverIp+"/getFrequency",new HashMap(),"GET");
    }



    /**
     * method for adding a friend
     * @params friendid
     * @params session should be included in the HTTP header
     * @return boolean
     * true if adding succeded or false if adding failed
     */
    public boolean addFriend(String friendid){
        Map params = new HashMap();
        params.put("friendid",friendid);
        return MonashHttp.sendUpdateRequest(serverIp+"/addFriend",params,"POST");
    }

    /**
     * method for delteting a friend
     * @params friendid
     * @params session should be included in the HTTP header
     * @return boolean
     * true if deleting succeded or false if deleting failed
     */
    public boolean delteteFriend(String friendid){
        Map params = new HashMap();
        params.put("friendid",friendid);
        return MonashHttp.sendUpdateRequest(serverIp+"/deleteFriend",params,"POST");
    }

}
