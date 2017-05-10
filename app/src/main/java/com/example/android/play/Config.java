package com.example.android.play;

/**
 * Created by Mukesh on 28-03-2017.
 */
public class Config {
    //URL to our login.php file
    public static final String T_LOGIN_URL = "http://192.168.43.75/playgroup/TLogin.php";
    public static final String T_REGISTER_URL="http://192.168.43.75/playgroup/TRegister.php";
    public static final String P_LOGIN_URL = "http://192.168.43.75/playgroup/PLogin.php";
    public static final String P_REGISTER_URL="http://192.168.43.75/playgroup/PRegister.php";
    public static final String REGISTER_CHILD_URL="http://192.168.43.75/playgroup/RegisterChildren.php";
    public static final String URL_GET_ALL="http://192.168.43.75/playgroup/ViewAllChildren.php";
    public static final String URL_GET_CHILD="http://192.168.43.75/playgroup/ViewChildren.php";
    public static final String URL_UPDATE_CHILD="http://192.168.43.75/playgroup/Update.php";
    public static final String URL_VIEW_DETAILS="http://192.168.43.75/playgroup/View.php";
    public static final String URL_VIEW_FOR_UPDATE="http://192.168.43.75/playgroup/ViewForUpdate.php";
    public static final String URL_CHILDREN_PARENT_VIEW="http://192.168.43.75/playgroup/ViewParent.php";
    public static final String URL_CHILDREN_PARENT="http://192.168.43.75/playgroup/ViewAll.php";


    public static final String KEY_CHILD_USERNAME="cname";
    public static final String KEY_CHILD_AGE="cage";
    public static final String TAG_CHILD_USERNAME="cname";

    public static final String TAG_CHILD_ID="cid";
    public static final String TAG_CHILD_AGE="cage";
    public static final String TAG_CHILD_PARENTNAME="pname";
    public static final String KEY_CHILD_PARENTNAME="pname";
    public static final String KEY_TEACHER_NAME="tname";

    public static final String TAG_L1="l1";
    public static final String TAG_L2="l2";
    public static final String TAG_L3="l3";
    public static final String TAG_L4="l4";
    public static final String TAG_L5="l5";


    public static final String KEY_L1="l1";
    public static final String KEY_L2="l2";
    public static final String KEY_L3="l3";
    public static final String KEY_L4="l4";
    public static final String KEY_L5="l5";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String T_KEY_EMAIL = "temail";
    public static final String T_KEY_PASSWORD = "tpassword";
    public static final String T_KEY_USERNAME="tusername";
    public static final String P_KEY_EMAIL = "pemail";
    public static final String P_KEY_PASSWORD = "ppassword";
    public static final String P_KEY_USERNAME="pusername";
//tags for json
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_KEY_TEMAIL = "temail";
    public static final String TAG_KEY_TPASSWORD = "tpassword";
    public static final String TAG_KEY_TUSERNAME="tusername";
    public static final String TAG_KEY_PEMAIL = "pemail";
    public static final String TAG_KEY_PPASSWORD = "ppassword";
    public static final String TAG_KEY_PUSERNAME="pusername";
    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";
    public static String TUSERNAME = null;
    public static String PUSERNAME = null;
    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}