package it.mirea.myapplication;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.io.File;
import java.util.Date;

import it.mirea.myapplication.model.Book;
import it.mirea.myapplication.user.NewBook;

public class AddBookActivity extends Activity {

    private static final int SELECT_DOC = 1;
    private String selectedDocPath;
    private TextView bookName;
    private Button chooserButton;
    private File file;
    Spinner spinner;
    FirebaseDatabase db;
    DatabaseReference books, users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook_page);
        chooserButton = ((Button) findViewById(R.id.chooserButton1));
        bookName = findViewById(R.id.bookName);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    public void ClickMain(View view) {
        Intent intent = new Intent(AddBookActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickAddBook(View view) {
        // in onCreate or any event where your want the user to
        // select a file
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Выберите файл"), SELECT_DOC);

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
        isStoragePermissionGranted();
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_DOC) {
                Uri selectedDocUri = data.getData();
                // = getPath(selectedDocUri);
                file = new File(selectedDocUri.getPath());//create path from uri
                String path = file.getPath();
                //String filepath = split[0];//assign it to a string(your choice).
                bookName.setText(path);
            }
        }
        //bookName.setText(selectedDocPath);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    public void sentToDB(View view) {
        NewBook book = new NewBook(file, file.getName(), new Single().getInstance().email, spinner.getSelectedItem().toString());
        db = new Single().getInstance().db;
        books = db.getReference("/books");
        users = db.getReference("/");
        books.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue("Скоро здесь появится объект под именем книга)")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddBookActivity.this, "Книга проходит проверку!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddBookActivity.this, ProfileAuthorActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddBookActivity.this, "Хуй!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}