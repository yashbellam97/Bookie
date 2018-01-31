package com.yaswanth.bookie;

/**
 * Custom class for Book object.
 */

public class Book {
    private String mBookName;
    private String mAuthor;

    public Book(String bookName, String author) {
        mBookName = bookName;
        mAuthor = author;
    }

    public String getBookName() {
        return mBookName;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
