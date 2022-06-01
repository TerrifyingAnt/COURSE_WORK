package it.mirea.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Single {

    private static final Single INSTANCE = new Single();
    public Single(){
        logic = false;
        name = "";
        email="";
        number="";
        text = "";
        type = 0;
        db = null;
        auth = null;
    }

    Boolean logic;
    String name, email, number, text;
    FirebaseDatabase db;
    FirebaseAuth auth;
    int type;

    public static Single getInstance(){
        return INSTANCE;
    }

    public Boolean getLogic() {
        return logic;
    }

    public void setLogic(Boolean logic) {
        this.logic = logic;
    }
}