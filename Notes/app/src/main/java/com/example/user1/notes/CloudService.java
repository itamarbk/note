package com.example.user1.notes;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by USER1 on 27/10/2016.
 */
public class CloudService implements NoteService {

    @Override
    public ArrayList<Note> fetchNotes() {
        ParseObject NotesTable=new ParseObject("Notes");
        ArrayList<Note> notes=new ArrayList<Note>();
        for(NotesTable.)
        return null;
    }

    @Override
    public void deleteEmptyNotes(ArrayList<Note> notes) {
        for(int i=0;i<notes.size();i++){
            if(notes.get(i).getContent()==""){
                notes.remove(i);
                i--;
            }
        }
    }

    @Override
    public void saveNotes(ArrayList<Note> notes) {
        deleteEmptyNotes(notes);
        for(int i=0;i<notes.size();i++){
            ParseObject NotesTable=new ParseObject("Notes");
            NotesTable.put("content",notes.get(i).content);
            NotesTable.saveInBackground();
        }
    }
}
