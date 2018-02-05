package com.yaswanth.bookie;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String queryUrlString = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText searchEditText = (EditText) findViewById(R.id.search_edit_text);
        FloatingActionButton searchFab = (FloatingActionButton) findViewById(R.id.search_fab);
        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = searchEditText.getText().toString();
                TextView messageTextView = (TextView) findViewById(R.id.main_message);
                if (!inputText.isEmpty()) {
                    messageTextView.setText(inputText);
                    messageTextView.setVisibility(View.VISIBLE);
                }
            }
        });

        /*ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("Book of Android", "AuthorA"));
        books.add(new Book("Googling", "AuthorB"));
        books.add(new Book("Art of Code", "AuthorC"));
        books.add(new Book("Linux Essentials", "AuthorD"));
        books.add(new Book("Android Cookbook", "AuthorE"));
        books.add(new Book("Git and GitHub", "AuthorF"));
        books.add(new Book("Zen of Code", "AuthorG"));
        books.add(new Book("Android Studio", "AuthorH"));
        books.add(new Book("The IntelliJ IDEA", "AuthorI"));
        books.add(new Book("Google is the best", "AuthorJ"));
        books.add(new Book("Let's make that app", "AuthorK"));


*/
        enableStrictMode();
        ArrayList<Book> books = QueryUtils.fetchBooks(queryUrlString);

        BookAdapter bookAdapter = new BookAdapter(this, books);

        ListView booksList = (ListView) findViewById(R.id.books_list);

        booksList.setAdapter(bookAdapter);
    }

    public void enableStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
}
