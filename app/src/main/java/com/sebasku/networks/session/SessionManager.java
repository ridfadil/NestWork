package com.sebasku.networks.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.sebasku.networks.activity.LoginActivity;
import com.sebasku.networks.activity.MenuActivity;

/**
 * Created by fadil on 3/30/18.
 */

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "NestworkUserPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_ACCESSTOKEN = "ACCESSTOKEN";
    public static final String KEY_ID = "ID";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_NAMA = "NAMA";
    public static final String KEY_POSISI = "POSISI";
    public static final String KEY_NOHP = "NOHP";


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String token){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_ACCESSTOKEN, token);

        // commit changes
        editor.commit();
    }

    public void createIdSession(String id){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_ID, id);

        // commit changes
        editor.commit();
    }

    public void createEmailSession(String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }

    public void createNamaSession(String nama){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAMA, nama);

        // commit changes
        editor.commit();
    }

    public void createPosisiSession(String posisi){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_POSISI, posisi);

        // commit changes
        editor.commit();
    }

    public void createNoHpSession(String noHp){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NOHP, noHp);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    public void checkLoginPage(){
        // Check login status
        if(this.isLoggedIn()){
            // user is not logged in redirect him to Menu Activity
            Intent i = new Intent(_context, MenuActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }


    /**
     * Get stored session data
     * */
    public String getAccesToken(){
        String accesToken = pref.getString(KEY_ACCESSTOKEN, "missing");

        return accesToken;
    }

    public String getId(){
        String accesId = pref.getString(KEY_ID, "missing");

        return accesId;
    }

    public String getEmail(){
        String accesEmail = pref.getString(KEY_EMAIL, "missing");

        return accesEmail;
    }

    public String getNama(){
        String accesNama = pref.getString(KEY_NAMA, "missing");

        return accesNama;
    }

    public String getPosisi(){
        String accesPosisi = pref.getString(KEY_POSISI, "missing");

        return accesPosisi;
    }

    public String getNoHp(){
        String accesNoHp = pref.getString(KEY_NOHP, "missing");

        return accesNoHp;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

}


