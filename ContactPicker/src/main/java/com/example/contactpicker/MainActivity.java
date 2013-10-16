package com.example.contactpicker;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Contacts;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.net.URI;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        String dataPath = intent.getData().toString();

        final Uri data = Uri.parse(dataPath + "people/");
        final Cursor c = managedQuery(data, null, null, null,null);

        String [] from = new String[] {Contacts.People.NAME};
        int[] to = new int[] {R.id.itemTextView};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.listitemlayout,
                c,
                from,
                to);
        ListView lv = (ListView)findViewById(R.id.contactListView);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
