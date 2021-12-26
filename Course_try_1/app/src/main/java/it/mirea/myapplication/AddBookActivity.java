package it.mirea.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class AddBookActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook_page);
    }


    public void ClickMain(View view) {
        Intent intent = new Intent(AddBookActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickProfile(View view) {
        Intent intent;
        intent=new Intent(AddBookActivity.this,ProfileActivity.class);
        switch(new Single().getInstance().type){

            case 0:
                intent=new Intent(AddBookActivity.this,ProfileActivity.class);
                break;
            case 1:
                intent=new Intent(AddBookActivity.this,ProfileAuthorActivity.class);
                break;
            case 2:
                intent=new Intent(AddBookActivity.this,ProfileSupervisorActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }

    public void ClickSettings(View view) {
        Intent intent = new Intent(AddBookActivity.this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }

}
