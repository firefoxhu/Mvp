package com.quickly.xqw.utils;
import android.content.SharedPreferences;
/**
 * 用户登录操作对象
 */
public class UserSessionUtil {

    /**
     * editor.putString(USER_NAME, "逍遥力");
     editor.putString(USER_COOKIE, "fdassssssssssdsafdsafdsaf");
     editor.putString(USER_AVATAR, "https://avatar.csdn.net/9/D/B/3_caiwenfeng_for_23.jpg");
     editor.putString(USER_GENDER, "男");
     * @return
     */


    private static final String USER_NAME = "username";

    private static final String USER_AVATAR = "user_avatar";

    private static final String USER_GENDER = "gender";

    private static final String USER_COOKIE = "session_key";

    private SharedPreferences sharedPreferences;


    public void saveSession(String username,String avatar,String gender,String cookie){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(USER_NAME,username);
        edit.putString(USER_COOKIE,cookie);
        edit.putString(USER_AVATAR, avatar);
        edit.putString(USER_GENDER,gender);
        edit.apply();
    }

    public UserSessionUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }


    public void setUsername(String username){
        sharedPreferences.edit().putString(USER_NAME,username).apply();
    }

    public String getUsername(){
        return sharedPreferences.getString(USER_NAME,"");
    }

    public void setUserCookie(String cookie) {
        sharedPreferences.edit().putString(USER_COOKIE,cookie).apply();
    }

    public String getUserCookie(){
        return sharedPreferences.getString(USER_COOKIE,"");
    }

    public void setUserAvatar(String avatar) {
        sharedPreferences.edit().putString(USER_AVATAR,avatar).apply();
    }

    public String getUserAvatar() {
        return sharedPreferences.getString(USER_AVATAR,"");
    }

    public void setUserGender(String gender) {
        sharedPreferences.edit().putString(USER_GENDER,gender).apply();
    }

    public String getUserGender() {
        return sharedPreferences.getString(USER_GENDER,"");
    }


}
