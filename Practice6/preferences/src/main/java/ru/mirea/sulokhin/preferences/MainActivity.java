package ru.mirea.sulokhin.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText textName;
    private EditText textUniversity;
    private SharedPreferences preferences;

    final String PERSON_NAME = "name";
    final String PERSON_UNIVERSITY = "university";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = findViewById(R.id.editTextPersonName);
        textUniversity = findViewById(R.id.editTextPersonUniversity);

        preferences = getPreferences(MODE_PRIVATE);
    }

    public void onLoadText(View view) {
        textName.setText(preferences.getString(PERSON_NAME, "Name"));
        textUniversity.setText(preferences.getString(PERSON_UNIVERSITY, "University"));
    }

    public void onSaveText(View view) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PERSON_NAME, textName.getText().toString());
        editor.putString(PERSON_UNIVERSITY, textUniversity.getText().toString());

        editor.apply();

        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }
}