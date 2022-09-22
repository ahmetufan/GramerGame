package com.ahmet.gramer.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.ahmet.gramer.MainActivity
import com.ahmet.gramer.view.FirstFragment

class LoginPref {

    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var con: Context
    var PRIVATEMODE: Int = 0

    constructor(con: Context) {
        this.con = con
        pref = con.getSharedPreferences(PREF_NAME, PRIVATEMODE)
        editor = pref.edit()
    }

    companion object {
        val PREF_NAME = "Login_Preference"
        val IS_LOGIN = "isLoggedin"
        val key_username = "username"
    }

    fun createLoginSession(username: String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(key_username, username)
        editor.commit()
    }

    fun checkLogin() {
        if (!this.isLoggedIn()) {
            var i: Intent = Intent(con, FirstFragment::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            con.startActivity(i)
        }
    }

    fun getUserDetails(): HashMap<String, String> {
        var user: Map<String, String> = HashMap<String, String>()
        (user as HashMap).put(key_username,pref.getString(key_username,null)!!)
        // (user as HashMap).put(key_email,pref.getString(key_username,null)!!)
        return user
    }
    fun LogoutUser() {
        editor.clear()
        editor.commit()
        val i : Intent = Intent(con, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        con.startActivity(i)
    }
    fun isLoggedIn(): Boolean {
        return  pref.getBoolean(IS_LOGIN,false)
    }


}