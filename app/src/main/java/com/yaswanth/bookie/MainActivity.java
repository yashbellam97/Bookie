package com.yaswanth.bookie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String queryUrlStringStart = "https://www.googleapis.com/books/v1/volumes?q=";
    private String queryUrlString = "";
    private String queryUrlStringEnd = "&maxResults=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText searchEditText = (EditText) findViewById(R.id.search_edit_text);

        FloatingActionButton searchFab = (FloatingActionButton) findViewById(R.id.search_fab);

        final ProgressBar loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        final ListView booksList = (ListView) findViewById(R.id.books_list);

        final TextView mainMessage = (TextView) findViewById(R.id.main_message);

        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingIndicator.setVisibility(View.VISIBLE);
                booksList.setVisibility(View.INVISIBLE);
                mainMessage.setVisibility(View.GONE);
                String inputText = searchEditText.getText().toString();
                if (!inputText.isEmpty()) {
                    queryUrlString = queryUrlStringStart + inputText + queryUrlStringEnd;
                    BookFetchAsyncTask task = new BookFetchAsyncTask();
                    task.execute(queryUrlString);
                }
            }
        });

        loadingIndicator.setVisibility(View.GONE);

        mainMessage.setVisibility(View.VISIBLE);

    }

    private class BookFetchAsyncTask extends AsyncTask<String, Void, ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(String... urls) {
            ArrayList<Book> books = QueryUtils.fetchBooks(urls[0]);
            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            BookAdapter bookAdapter = new BookAdapter(MainActivity.this, books);

            ProgressBar loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            ListView booksList = (ListView) findViewById(R.id.books_list);
            booksList.setVisibility(View.VISIBLE);

            booksList.setAdapter(bookAdapter);
        }
    }
}
