package ru.mirea.sulokhin.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText editFileName;
    private EditText editContent;
    private String curFileName;

    private SharedPreferences preferences;

    private String LAST_SAVED_FILE = "last_saved_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFileName = findViewById(R.id.editFileName);
        editContent = findViewById(R.id.editContent);

        preferences = getPreferences(MODE_PRIVATE);

        curFileName = getPrevFileName();
        if(curFileName != null)
            return;

        String curFileText = loadTextFromFile(curFileName);
        if(curFileText == null)
            return;

        editContent.setText(curFileText);
    }

    private String getPrevFileName(){
        return preferences.getString(LAST_SAVED_FILE, null);
    }

    private void saveFileName(String fileName){
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(LAST_SAVED_FILE, fileName);
        editor.apply();
    }

    private String loadTextFromFile(String fileName){
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
}