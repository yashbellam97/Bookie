package com.yaswanth.bookie;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * BookLoader activity to implement AsyncTaskLoader.
 */

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {

    private static String LOG_TAG = BookLoader.class.getName();

    private String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        ArrayList<Book> books = QueryUtils.fetchBooks(mUrl);
        return books;
    }
}
