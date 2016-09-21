package com.example.user1.notes;

import java.util.ArrayList;

/**
 * Created by USER1 on 21/09/2016.
 */
public interface NoteService {
    /*
    returns the saved notes
     */
    public ArrayList<Note> fetchNotes();
    /*
    saves/updates the array in the storage
     */
    public void saveNotes(ArrayList<Note> notes);
}
