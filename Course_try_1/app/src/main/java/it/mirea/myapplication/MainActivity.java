package it.mirea.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import it.mirea.myapplication.adapter.BookAdapter;
import it.mirea.myapplication.adapter.CategoryAdapter;
import it.mirea.myapplication.model.Book;
import it.mirea.myapplication.model.Category;

public class MainActivity extends AppCompatActivity {

    static boolean f = false;
    RecyclerView categoryRecycler, booksRecycler;
    CategoryAdapter categoryAdapter;
    static BookAdapter bookAdapter;
    static List<Book> bookList = new ArrayList<>();
    static List<Book> fullBooksList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(new Single().getInstance().logic){
            setContentView(R.layout.activity_main);

            List<Category> categoryList = new ArrayList<>();
            categoryList.add(new Category(0, "Все"));
            categoryList.add(new Category(1, "Роман"));
            categoryList.add(new Category(2, "Детективы"));
            categoryList.add(new Category(3, "Приключения"));
            categoryList.add(new Category(4, "Фантастика"));
            categoryList.add(new Category(5, "Научная"));
            categoryList.add(new Category(6, "Детям"));

            setCategoryRecycler(categoryList);

            if(f == false) {
                bookList.add(new Book(1, "123 стр", "Роман", "Мастер и Маргарита", "mm", "Здесь будет описание книги", "Здесь будет книга"));
                bookList.add(new Book(2, "9999 стр", "Роман", "Война и мир", "peace_and_death", "Здесь будет описание книги", "Здесь будет книга"));
                bookList.add(new Book(3, "1000 стр", "Детективы", "Мертвые души", "deadsouls", "Здесь будет описание книги", "Здесь будет книга"));
                bookList.add(new Book(4, "500 стр", "Детективы", "Преступление и\n наказание", "crime", "Здесь будет описание книги", "Здесь будет книга"));
                bookList.add(new Book(5, "450 стр", "Приключения", "Отцы и дети", "athers_and_children", "Здесь будет описание книги", "Здесь будет книга"));
                bookList.add(new Book(6, "350 стр", "Детям", "Обломов", "oblomoff", "Здесь будет описание книги", "Здесь будет книга"));
                bookList.add(new Book(7, "250 стр", "Приключения", "Герой нашего\nвемени", "pechorin", "Здесь будет описание книги", "Здесь будет книга"));
                fullBooksList.addAll(bookList);
                f = true;
            }

            setBookRecycler(bookList);

        }
        else{
            Intent intent=new Intent(MainActivity.this,EntryActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void setBookRecycler(List<Book> bookList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false);
        booksRecycler = findViewById(R.id.booksRecycler);
        booksRecycler.setLayoutManager(layoutManager);

        bookAdapter = new BookAdapter(this, bookList);
        booksRecycler.setAdapter(bookAdapter);
    }


    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    public void ClickSettings(View view) {
        Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
        startActivity(intent);
        finish();
    }

    public void ClickProfile(View view) {
        Intent intent;
        intent=new Intent(MainActivity.this,ProfileActivity.class);
        switch(new Single().getInstance().type){

            case 0:
                intent=new Intent(MainActivity.this,ProfileActivity.class);
                break;
            case 1:
                intent=new Intent(MainActivity.this,ProfileAuthorActivity.class);
                break;
            case 2:
                intent=new Intent(MainActivity.this,ProfileSupervisorActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }

    public static void showBooksByCategory(String category){

        bookList.clear();
        bookList.addAll(fullBooksList);

        List<Book> filterBooks = new ArrayList<>();
        if (category == "Все"){
            filterBooks.addAll(bookList);
        }
        else {

            for (Book i : bookList) {
                if (i.getType() == category)
                    filterBooks.add(i);
            }
        }
        bookList.clear();
        bookList.addAll(filterBooks);

        bookAdapter.notifyDataSetChanged();

    }

}