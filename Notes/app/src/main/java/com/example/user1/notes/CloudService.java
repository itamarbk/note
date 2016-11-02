package com.example.user1.notes;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER1 on 27/10/2016.
 */
public class CloudService implements NoteService {

    public CloudService() {
    }

    @Override
    public ArrayList<Note> fetchNotes() {
        final ArrayList<Note> notes=new ArrayList<Note>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notes");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if(objects!=null) {
                    for (int i = 0; i < objects.size(); i++) {
                        notes.add(new Note((String) objects.get(i).get("content")));
                    }
                }
                Log.d("objects","objects==null"+(objects==null));
                Log.d("parse exception","exception==null"+(e==null));
            }
        });
        return notes;
    }


    @Override
    public void saveNotes(ArrayList<Note> notes) {
        deleteEmptyNotes(notes);
        for (int i = 0; i < notes.size(); i++) {
            ParseObject NotesTable = new ParseObject("Notes");
            NotesTable.put("content", notes.get(i).content);
            NotesTable.saveInBackground();
            Log.d("saving notes to cloud","saving notes to cloud");
        }
    }

    @Override
    public void deleteEmptyNotes(ArrayList<Note> notes) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getContent() == "") {
                notes.remove(i);
                i--;
            }
        }
    }
}