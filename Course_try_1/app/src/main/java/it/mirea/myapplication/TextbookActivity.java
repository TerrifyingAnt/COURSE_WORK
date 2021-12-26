package it.mirea.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TextbookActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textbook_page);

        TextView bookText = findViewById(R.id.bookText);
        bookText.setText(getIntent().getStringExtra("bookText"));
    }


    public void ClickMain(View view) {
        Intent intent = new Intent(TextbookActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickProfile(View view) {
        Intent intent;
        intent=new Intent(TextbookActivity.this,ProfileActivity.class);
        switch(new Single().getInstance().type){

            case 0:
                intent=new Intent(TextbookActivity.this,ProfileActivity.class);
                break;
            case 1:
                intent=new Intent(TextbookActivity.this,ProfileAuthorActivity.class);
                break;
            case 2:
                intent=new Intent(TextbookActivity.this,ProfileSupervisorActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }

    public void ClickSettings(View view) {
        Intent intent = new Intent(TextbookActivity.this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }

}
