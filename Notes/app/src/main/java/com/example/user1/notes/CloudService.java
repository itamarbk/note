package com.example.user1.notes;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER1 on 27/10/2016.
 */
public class CloudService implements NoteService {

    public CloudService() {
        ParseObject NotesTable = new ParseObject("Notes");
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
        ParseObject NotesTable = new ParseObject("Notes");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notes");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    if (objects != null) {
                        for (int i = 0; i < objects.size(); i++) {
                            objects.get(i).deleteInBackground();
                        }
                    }
                    Log.d("objects", "objects==null" + (objects == null));
                    Log.d("parse exception", "exception==null" + (e == null));
                }
            }
        });
        for (int i = 0; i < notes.size(); i++) {
            NotesTable.put("content", notes.get(i).content);
        }
        NotesTable.saveInBackground();
        Log.d("saving notes to cloud", "done?");
    }
   /* private void clearParseNotes(){

        ParseObject NotesTable = new ParseObject("Notes");
        for(int i=0;i<notes.size();i++){
            for(int j=0;j<temp.size();j++){
                if(notes.get(i)==savedNotes.get(j)){
                    temp.remove(i);
                }
            }
        }
        savedNotes.addAll(temp);
        return temp;
    }*/

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