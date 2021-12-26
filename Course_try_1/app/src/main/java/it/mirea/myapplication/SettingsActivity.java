package it.mirea.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.mirea.myapplication.adapter.BookAdapter;
import it.mirea.myapplication.adapter.CategoryAdapter;
import it.mirea.myapplication.model.Book;
import it.mirea.myapplication.model.Category;

public class SettingsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
    }


    public void ClickMain(View view) {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickProfile(View view) {
        Intent intent;
        intent=new Intent(SettingsActivity.this,ProfileActivity.class);
        switch(new Single().getInstance().type){

            case 0:
                intent=new Intent(SettingsActivity.this,ProfileActivity.class);
                break;
            case 1:
                intent=new Intent(SettingsActivity.this,ProfileAuthorActivity.class);
                break;
            case 2:
                intent=new Intent(SettingsActivity.this,ProfileSupervisorActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }

}