package com.yaswanth.bookie;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

        ListView booksList = (ListView) findViewById(R.id.books_list);
        String[] bookNames = {"Book of Android", "Android Book", "Objective C", "Swift", "Head First Java", "Programming Python", "Book of Android", "Android Book", "Objective C", "Swift"};
        ArrayAdapter<String> booksArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, bookNames);
        booksList.setAdapter(booksArrayAdapter);
    }
}
