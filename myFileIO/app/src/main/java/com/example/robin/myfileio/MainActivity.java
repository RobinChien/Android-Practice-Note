package com.example.robin.myfileio;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements ActivityCompat.RequestPermissionsRequestCodeValidator {

    EditText readView;
    EditText writeView;

    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    String file = "temp.txt";

    File myExternalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readView = (EditText) findViewById(R.id.editTextRead);
        writeView = (EditText) findViewById(R.id.editTextWrite);

        Button externalRead = (Button) findViewById(R.id.ReadExternalButton);
        Button externalWrite = (Button) findViewById(R.id.WriteExternalButton);
        Button externalDelete = (Button) findViewById(R.id.DeleteExternalButton);

        if(!isExternalStorageWritable() && isExternalStorageReadable()){
            externalRead.setEnabled(false);
            externalWrite.setEnabled(false);
            externalDelete.setEnabled(false);
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 12);
        }

        myExternalFile = getDocumentDir("temp.txt");
    }

    private File getDocumentDir(String s) {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), s);
        return file;
    }

    public void writeInternalOnClick(View view) {
        String data = writeView.getText().toString();

        try {
            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ReadInternalOnClick(View view) {
        try{
            InputStream inputStream = this.openFileInput(file);
            ReadData(inputStream);
        }catch (java.io.FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void DeleteInternalOnClick(View view) {
        this.deleteFile(file);
    }

    public void WriteExternalOnClick(View view) {
        String data = writeView.getText().toString();

        try{
            fileOutputStream = new FileOutputStream(myExternalFile);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ReadExternalOnClick(View view) {
        try {
            InputStream inputStreamReader = new FileInputStream(myExternalFile);
            ReadData(inputStreamReader);
        }catch (java.io.FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void DeleteExternalOnClick(View view) {
        myExternalFile.delete();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 12:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{

                }
        }
    }

    private void ReadData(InputStream inputStream) {
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = new String();
            String allLine = new String();

            while ((line = bufferedReader.readLine()) != null){
                allLine += line;
            }

            readView.setText(allLine);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }

}
