package com.example.user1.notes;

/**
 * Created by USER1 on 21/09/2016.
 */
public class Note {
    String content;

    public Note(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
