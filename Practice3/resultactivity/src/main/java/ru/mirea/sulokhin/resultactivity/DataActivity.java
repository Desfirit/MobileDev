package ru.mirea.sulokhin.resultactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
    }

    public void onClickEndUserInputButton(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextPersonUniversity);

        Intent resIntent = new Intent();
        resIntent.putExtra("university", editText.getText().toString());

        setResult(RESULT_OK, resIntent);
        finish();
    }
}