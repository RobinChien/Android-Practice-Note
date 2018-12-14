package com.example.robin.myadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<User> arrayOfUsers= new ArrayList<User>();
        UserAdapter adapter = new UserAdapter(this, arrayOfUsers);
        ListView listView = (ListView) findViewById(R.id.myListView);
        listView.setAdapter(adapter);

        User u1 = new User("aaa", "aaa");
        User u2 = new User("bbb", "bbb");
        User u3 = new User("acc", "ccc");

        adapter.add(u1);
        adapter.add(u2);
        adapter.add(u3);
    }
}
