package com.example.user1.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class ShowNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        ArrayList<Note> notes=new ArrayList<Note>();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        GridView gr=(GridView)findViewById(R.id.gridView);
        gr.setAdapter(adapter);
        Button btn=(Button)findViewById(R.id.btn);
    }
}
