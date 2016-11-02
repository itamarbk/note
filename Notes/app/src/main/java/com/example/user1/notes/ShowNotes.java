package com.example.user1.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.parse.Parse;

import java.util.ArrayList;

public class ShowNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("jPFcw09FZEhu6fF5ijs5V14TZFrHAP9B25JjDXKY")
                .clientKey("DZYzyWXpYlpBaRBSxM7qOtwBGWOFO7ftNdwV9Gw1")
                .server("https://parseapi.back4app.com/").build()
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        final CloudService Noteservice =new CloudService();
        final ArrayList<Note> notes=Noteservice.fetchNotes();
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        final GridView gr=(GridView)findViewById(R.id.gridView);
        gr.setAdapter(adapter);
        Button btn=(Button)findViewById(R.id.btn);
        Button clearbtn=(Button) findViewById(R.id.clearbtn);
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.clear();
                Noteservice.saveNotes(notes);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note temp=new Note("");
                temp.editNote(ShowNotes.this);
                notes.add(temp);
                adapter.setNotifyOnChange(true);
                gr.setAdapter(adapter);
                Noteservice.saveNotes(notes);
                //notes.clear();
                //notes.addAll(Noteservice.fetchNotes());
            }
        });

        gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                notes.get(position).editNote(ShowNotes.this);
                Noteservice.saveNotes(notes);
                //notes.clear();
                //notes.addAll(Noteservice.fetchNotes());
                adapter.setNotifyOnChange(true);
                gr.setAdapter(adapter);
            }
        });

    }
}
