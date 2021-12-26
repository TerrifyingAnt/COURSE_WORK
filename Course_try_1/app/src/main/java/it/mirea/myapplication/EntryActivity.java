package it.mirea.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class EntryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_page);
    }

    public void ClickRegister(View view){
        Intent intent=new Intent(EntryActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickMain(View view){
        new Single().getInstance().logic = true;
        Intent intent=new Intent(EntryActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}