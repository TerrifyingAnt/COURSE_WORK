package it.mirea.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;


public class ProfileAuthorActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Date date = new Date();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page_author);
        TextView text = (TextView) findViewById(R.id.textView);
        TextView phone = (TextView) findViewById(R.id.PhoneNumber);
        TextView email = (TextView) findViewById(R.id.Email);
        text.setText(new Single().getInstance().name);
        phone.setText(new Single().getInstance().number);
        email.setText(new Single().getInstance().email);
    }

    public void ClickCamera(View view){
        Intent intent=new Intent(ProfileAuthorActivity.this,CameraActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickMain(View view){
        Intent intent=new Intent(ProfileAuthorActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickSettings(View view){
        Intent intent=new Intent(ProfileAuthorActivity.this,SettingsActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickEntry(View view){
        new Single().getInstance().logic = false;
        SharedPreferences preferences = getSharedPreferences("Login session", Context.MODE_PRIVATE);
        preferences.edit().remove("log").commit();
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(ProfileAuthorActivity.this,EntryActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickAddBook(View view){
        Intent intent=new Intent(ProfileAuthorActivity.this,AddBookActivity.class);
        startActivity(intent);
        finish();
    }


}
