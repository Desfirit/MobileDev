package ru.mirea.sulokhin.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String fileName = "mirea.txt";
    private TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = findViewById(R.id.textView);

        String string = "Hello mirea!";
        if(!writeTextInFile(string)){
            Toast.makeText(this, "Can't write text in file", Toast.LENGTH_SHORT).show();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                tvOut.post(new Runnable() {
                    @Override
                    public void run() {
                        tvOut.setText(getTextFromFile());
                    }
                });
            }
        }).start();
    }

    private String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(fileName);

            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);

            String text = new String(bytes);

            Log.d(LOG_TAG, text);

            return text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    private boolean writeTextInFile(String text){
        try {
            FileOutputStream outputStream;
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}