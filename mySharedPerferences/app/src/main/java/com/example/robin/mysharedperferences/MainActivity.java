package com.example.robin.mysharedperferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText name, phone, addr;

    public static final String m_name = "name";
    public static final String m_phone = "phone";
    public static final String m_addr = "addr";
    public static final String MYPER = "myper";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
        addr = (EditText) findViewById(R.id.editText3);

        sharedPreferences = getSharedPreferences(MYPER, MODE_PRIVATE);
    }

    public void readButton(View view) {
        String n_name = sharedPreferences.getString(m_name, "");
        String n_phone = sharedPreferences.getString(m_phone, "");
        String n_addr = sharedPreferences.getString(m_addr, "");

        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText(n_name+"\n"+n_phone+"\n"+n_addr);
    }

    public void saveButton(View view) {
        String n_name = name.getText().toString();
        String n_phone = phone.getText().toString();
        String n_addr = addr.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(m_name, n_name);
        editor.putString(m_phone, n_phone);
        editor.putString(m_addr, n_addr);

        editor.commit();
    }
}
