package it.mirea.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.mirea.myapplication.BookActivity;
import it.mirea.myapplication.MainActivity;
import it.mirea.myapplication.R;
import it.mirea.myapplication.model.Book;
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context context;
    List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bookItems = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new BookAdapter.BookViewHolder(bookItems);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        int imageId = context.getResources().getIdentifier("ic_" + books.get(position).getImg(), "drawable", context.getPackageName());
        holder.bookImage.setImageResource(imageId);

        holder.bookTitle.setText(books.get(position).getTitle());
        holder.bookType.setText(books.get(position).getType());
        holder.pageNumber.setText(books.get(position).getNumber_of_pages());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookActivity.class);

                intent.putExtra("bookImage", imageId);
                intent.putExtra("bookTitle", books.get(position).getTitle());
                intent.putExtra("bookType", books.get(position).getType());
                intent.putExtra("bookDescription", books.get(position).getDescription());
                intent.putExtra("bookText", books.get(position).getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static final class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView bookTitle, bookType, pageNumber;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            bookImage = itemView.findViewById(R.id.bookImage);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookType = itemView.findViewById(R.id.bookType);
            pageNumber = itemView.findViewById(R.id.pageNumber);
        }
    }
}
