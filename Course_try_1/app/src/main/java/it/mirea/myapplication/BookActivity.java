package it.mirea.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BookActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_page);

        ImageView bookImage = findViewById(R.id.bookImage);
        TextView bookDescription = findViewById(R.id.bookDescription);
        TextView bookTitle = findViewById(R.id.bookTitle);

        bookImage.setImageResource(getIntent().getIntExtra("bookImage", 0));
        bookTitle.setText(getIntent().getStringExtra("bookTitle"));
        bookDescription.setText(getIntent().getStringExtra("bookDescription"));
    }


    public void ClickMain(View view) {
        Intent intent = new Intent(BookActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickProfile(View view) {
        Intent intent;
        intent=new Intent(BookActivity.this,ProfileActivity.class);
        switch(new Single().getInstance().type){

            case 0:
                intent=new Intent(BookActivity.this,ProfileActivity.class);
                break;
            case 1:
                intent=new Intent(BookActivity.this,ProfileAuthorActivity.class);
                break;
            case 2:
                intent=new Intent(BookActivity.this,ProfileSupervisorActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }

    public void ClickSettings(View view) {
        Intent intent = new Intent(BookActivity.this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickRead(View view){
        Intent intent = new Intent(BookActivity.this, TextbookActivity.class);
        new Single().getInstance().text = "Здесь будет книга";
        intent.putExtra("bookText", new Single().getInstance().text);
        startActivity(intent);
        finish();
    }

}