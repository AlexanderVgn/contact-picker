package com.example.contactpicker;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Contacts;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactPicker extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactpicker);

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                c.moveToPosition(position);
                int rowId = c.getInt(c.getColumnIndexOrThrow("_id"));
                Uri outURI = Uri.parse(data.toString()+rowId);
                Intent outData = new Intent();
                outData.setData(outURI);
                setResult(Activity.RESULT_OK, outData);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
