package com.yaswanth.bookie;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom adapter for Book objects.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Book book = getItem(position);

        TextView bookNameTextView = (TextView) listItemView.findViewById(R.id.book_name_textview);
        bookNameTextView.setText(book.getBookName());

        TextView bookAuthorTextView = (TextView) listItemView.findViewById(R.id.book_author_textview);
        bookAuthorTextView.setText(book.getAuthor());

        return listItemView;
    }
}
