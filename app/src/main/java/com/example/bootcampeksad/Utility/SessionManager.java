package com.example.bootcampeksad.Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager
{
    private static String KEY_USERNAME = "USERNAME";
    private static String KEY_PASSWORD= "PASSWORD";
    private static String KEY_REMEMBER = "REMEMBER";
    private static String KEY_LOGIN="LOGIN";

    protected static SharedPreferences retrieveSharedPreferences(Context context){
        return context.getSharedPreferences("SESSION_MANAGER_V1", context.MODE_PRIVATE);
    }
    protected static SharedPreferences.Editor retrieveSharedPreferencesEditor(Context context){
        return retrieveSharedPreferences(context).edit();
    }

    //getter setter data
    public static void simpandatalogin(Context context,String userName, String Password, boolean Remember)
    {
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);

        editor.putString(KEY_USERNAME, userName);
        editor.putString(KEY_PASSWORD, Password);
        editor.putBoolean(KEY_REMEMBER, Remember);
        editor.putBoolean(KEY_LOGIN, true);
        editor.commit();
    }

    public static void setLoginFlag(Context context, boolean login)
    {
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);

        editor.putBoolean(KEY_LOGIN, login);
        editor.commit();
    }

    //getter data
    public static boolean cekLogin(Context context)
    {
        return retrieveSharedPreferences(context).getBoolean(KEY_LOGIN, false);
    }

    public static String getUsername(Context context)
    {
        return retrieveSharedPreferences(context).getString(KEY_USERNAME, "");
    }

    public static String getPassword(Context context)
    {
        return retrieveSharedPreferences(context).getString(KEY_PASSWORD, "");
    }

    public static Boolean cekRemember(Context context)
    {
        return retrieveSharedPreferences(context).getBoolean(KEY_REMEMBER, false);
    }
}
