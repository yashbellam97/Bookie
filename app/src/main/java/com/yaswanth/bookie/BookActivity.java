package com.yaswanth.bookie;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    private static String LOG_TAG = BookActivity.class.getName();
    private static int BOOK_LOADER_ID = 1;
    TextView mainMessage;
    ProgressBar loadingIndicator;
    private String queryUrlStringStart = "https://www.googleapis.com/books/v1/volumes?q=";
    private String queryUrlString = "";
    private String queryUrlStringEnd = "&maxResults=10";
    private int count = 1;
    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView booksList = (ListView) findViewById(R.id.books_list);
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        booksList.setAdapter(mAdapter);

        mainMessage = (TextView) findViewById(R.id.main_message);

        final EditText searchEditText = (EditText) findViewById(R.id.search_edit_text);
        FloatingActionButton searchFab = (FloatingActionButton) findViewById(R.id.search_fab);

        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMessage.setVisibility(View.GONE);
                loadingIndicator.setVisibility(View.VISIBLE);
                String inputText = searchEditText.getText().toString();
                if (!inputText.isEmpty()) {
                    queryUrlString = queryUrlStringStart + inputText + queryUrlStringEnd;
                    reload();
                }
            }
        });

        if (count == 1) {
            loadingIndicator.setVisibility(View.GONE);
            mainMessage.setText(R.string.lonely);
            count++;
        } else {
            loadingIndicator.setVisibility(View.VISIBLE);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        }
    }

    public void reload() {
        getLoaderManager().restartLoader(BOOK_LOADER_ID, null, this);
    }

    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, queryUrlString);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> books) {
        loadingIndicator.setVisibility(View.GONE);

        mAdapter.clear();

        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        } else {
            mainMessage.setText(R.string.no_books);
            mainMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {
        mAdapter.clear();
    }
}
