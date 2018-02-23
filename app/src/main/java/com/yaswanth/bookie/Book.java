package com.yaswanth.bookie;

/**
 * Custom class for Book object.
 */

public class Book {
    private String mBookName;
    private String mAuthor;
    private String mBookUrl;

    public Book(String bookName, String author, String bookUrl) {
        mBookName = bookName;
        mAuthor = author;
        mBookUrl = bookUrl;
    }

    public String getBookName() {
        return mBookName;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getBookUrl() {
        return mBookUrl;
    }
}
