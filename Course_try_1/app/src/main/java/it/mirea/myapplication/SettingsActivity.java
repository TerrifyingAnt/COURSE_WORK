package it.mirea.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import it.mirea.myapplication.adapter.BookAdapter;
import it.mirea.myapplication.adapter.CategoryAdapter;
import it.mirea.myapplication.model.Book;
import it.mirea.myapplication.model.Category;

public class SettingsActivity extends Activity {
    static boolean f = false;
    static BookAdapter bookAdapter;
    StorageReference storageReference;
    FirebaseStorage storage;
    static Book book;
    static List<Book> bookList;
    static List<Book> fullBooksList = new ArrayList<>();
    List<Book> books;
    List<String> bookNames;
    RecyclerView booksRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        if (f == false) {
            getBooks();
            //fullBooksList.clear();
            System.out.println(new Single().getInstance().myBookList.size());
            //getBooks();
            bookList.addAll(new Single().getInstance().myBookList);
            for (int i = 0; i < bookList.size(); i++) {
                fullBooksList.add(bookList.get(i));
                System.out.println(fullBooksList.get(i).getTitle());
            }

            setBookRecycler(bookList);
            bookList.addAll(fullBooksList);
            f = true;
        }
        setBookRecycler(bookList);

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



    private void setBookRecycler(List<Book> bookList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false);
        booksRecycler = findViewById(R.id.myBooksRecycle);
        booksRecycler.setLayoutManager(layoutManager);
        bookAdapter = new BookAdapter(this, bookList);
        booksRecycler.setAdapter(bookAdapter);
    }


    public void getBooks() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference users = db.getReference("/");;
        List<String> myReadBooks = new ArrayList<>();
        final String[] readJson = {""};
        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("reading_library").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    readJson[0] = (String) task.getResult().getValue();
                    try {
                        File rootPath = new File(Environment.getExternalStorageDirectory(), "Downloads");
                        File file = new File(rootPath, "Json.json");
                        FileWriter fileWriter = new FileWriter(rootPath + "/Json.json");
                            fileWriter.write(readJson[0]);
                            fileWriter.flush();
                            fileWriter.close();
                        FileReader r = new FileReader("Json.json");
                        JsonArray objects = Jsoner.deserializeMany(r);

                        Mapper mapper = new DozerBeanMapper();

                        JsonArray o = (JsonArray) objects;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                myReadBooks.addAll(((Map)(((Map)(o.get(0))).get("Map"))).keySet());
                            }
                        } catch (JsonException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                }
        });
        bookList = new ArrayList<>();
        bookNames = new ArrayList<>();
        books = new ArrayList<>();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("/").child("Library");
        storageReference.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        bookNames.clear();
                        new Single().getInstance().bookNames.clear();
                        for (StorageReference prefix : listResult.getPrefixes()) {
                            bookNames.add(prefix.getName().split("\\.")[0]);
                            System.out.println(prefix.getName().split("\\.")[0]);
                            new Single().getInstance().bookNames.add(prefix.getName().split("\\.")[0]);
                        }
                        File rootPath = new File(Environment.getExternalStorageDirectory(), "Downloads");
                        File localFile = new File(rootPath, "metainf.json");
                        for (int i = 0; i < new Single().getInstance().bookNames.size(); i++) {
                            for(int j = 0; j < myReadBooks.size(); j++) {
                                System.out.println("8================D " + myReadBooks.get(j) + " " + new Single().getInstance().bookNames.get(i));
                                if (myReadBooks.get(j).equals(new Single().getInstance().bookNames.get(i))){
                                    if (i > 0)
                                        localFile.delete();
                                    StorageReference storageRef = storage.getReference("/").child("Library").child(bookNames.get(i) + ".txt").child("metainf.json");
                                    localFile = new File(rootPath, "metainf.json");
                                    if (!rootPath.exists()) {
                                        rootPath.mkdirs();
                                    }
                                    int finalI = i;
                                    storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            Log.e("firebase ", "найс");
                                            try (FileReader fileReader = new FileReader(new File(rootPath, "metainf.json"))) {
                                                JsonArray objects = Jsoner.deserializeMany(fileReader);
                                                JsonArray o = (JsonArray) objects;
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                                    String title = (String) (((Map) ((Map) (o.get(0))).get("Book"))).get("title");
                                                    String author = (String) (((Map) ((Map) (o.get(0))).get("Book"))).get("author");
                                                    String number_of_pages = (String) (((Map) ((Map) (o.get(0))).get("Book"))).get("number_of_pages");
                                                    String type = (String) (((Map) ((Map) (o.get(0))).get("Book"))).get("type");
                                                    StorageReference imgRef = storage.getReference("/").child("Library").child(title + ".txt").child("logo");
                                                    StorageReference descRef = storage.getReference("/").child("Library").child(title + ".txt").child("description.txt");
                                                    StorageReference textRef = storage.getReference("/").child("Library").child(title + ".txt").child(title + ".txt");
                                                    System.out.println("8=============D " + title);
                                                    book = new Book(finalI + 1, number_of_pages, type, title, author, imgRef, descRef, textRef);
                                                    System.out.println("8=============D" + book.toString());
                                                    //bookList.add(book);
                                                    new Single().getInstance().myBookList.add(book);
                                                    //setBookRecycler(fullBooksList);
                                                    System.out.println("bookList" + bookList + " fullBooksList" + fullBooksList);
                                                }
                                                bookList = new Single().getInstance().myBookList;
                                                System.out.println("8=====================D " + bookList.size());
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            } catch (JsonException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                });
    }


}