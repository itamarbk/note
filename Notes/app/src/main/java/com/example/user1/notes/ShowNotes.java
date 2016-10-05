package com.example.user1.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class ShowNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        final InternalStorageService Noteservice =new InternalStorageService(this);
        final ArrayList<Note> notes=new ArrayList<Note>();
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        final GridView gr=(GridView)findViewById(R.id.gridView);
        gr.setAdapter(adapter);
        Button btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note temp=new Note("");
                temp.editNote(ShowNotes.this);
                notes.add(temp);
                Noteservice.saveNotes(notes);
               // Noteservice.deleteEmptyNotes(notes);
            }
        });

        gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                notes.get(position).editNote(ShowNotes.this);
                Noteservice.saveNotes(notes);
               // Noteservice.deleteEmptyNotes(notes);
            }
        });

    }
}
