package it.mirea.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public class AddBookActivity extends Activity {

    private static final int SELECT_DOC = 1;
    private String selectedDocPath;
    private TextView bookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookName = (TextView) findViewById(R.id.bookName);
        setContentView(R.layout.addbook_page);
        ((Button) findViewById(R.id.chooserButton1))
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {

                        // in onCreate or any event where your want the user to
                        // select a file
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,
                                "Выберите файл"), SELECT_DOC);
                    }
                });

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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_DOC) {
                Uri selectedDocUri = data.getData();
                selectedDocPath = getPath(selectedDocUri);
                //bookName.setText(selectedDocPath);
            }
        }
        bookName.setText(selectedDocPath);
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Files.FileColumns.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
