package ru.mirea.sulokhin.practice6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String PERSISTANT_STORAGE_NAME = "Mger";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences =
                getSharedPreferences(PERSISTANT_STORAGE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mg", "Mger");
        editor.putInt("age", 007);
        editor.apply();

        preferences.getString("mg", "def");
        preferences.getInt("age", 000);

    }
}