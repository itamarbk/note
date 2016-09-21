package com.example.user1.notes;

import android.content.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by USER1 on 21/09/2016.
 */
public class InternalStorageService implements NoteService{
    final String FILE_PATH="note.txt";
    final String SEPERATOR="//////ahhhhhh/////seperate!!!###@!@@?";
    Context context;

    public InternalStorageService(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<Note> fetchNotes() {
        ArrayList<Note> notes=new ArrayList<Note>();
        File file=new File(context.getFilesDir(),FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(SEPERATOR);
            while(scanner.hasNext()){
                notes.add(new Note(scanner.next()));
            }
            scanner.close();
            return notes;
        }
        catch(Exception e){}
        return null;
    }

    @Override
    public void saveNotes(ArrayList<Note> notes) {
        File file=new File(context.getFilesDir(),FILE_PATH);
        try {
            PrintWriter pr = new PrintWriter(file);
            for(int i=0;i<notes.size();i++){
                pr.println(notes.get(i)+SEPERATOR);
            }
            pr.close();
        }catch(Exception e){}
    }
}
