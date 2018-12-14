package com.example.robin.mysearchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] myStringArray = getResources().getStringArray(R.array.my_string_array);
        adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, myStringArray);
        Log.v("adapter", String.valueOf(adapter));
        ListView listView = (ListView) findViewById(R.id.myListView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_option, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v("onQueryTextSubmit:", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("onQueryTextSubmit:", newText);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
