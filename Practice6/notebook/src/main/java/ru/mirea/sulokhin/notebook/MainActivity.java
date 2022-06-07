package ru.mirea.sulokhin.notebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String>{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText editFileName;
    private EditText editContent;

    private Button saveButton;
    private Button loadButton;

    private String curFileName;

    private SharedPreferences preferences;

    private String LAST_SAVED_FILE = "last_saved_file";

    private final int SaverID = 007;
    private final int LoaderID = 228;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFileName = findViewById(R.id.editFileName);
        editContent = findViewById(R.id.editContent);

        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);

        preferences = getPreferences(MODE_PRIVATE);

        curFileName = getSavedFileName();
        if(curFileName == null)
            return;

        editFileName.setText(curFileName);

        onLoadButton(null);
    }

    private String getSavedFileName(){
        return preferences.getString(LAST_SAVED_FILE, null);
    }

    private void saveFileName(String fileName){
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(LAST_SAVED_FILE, fileName);
        editor.apply();
    }

    public void onLoadButton(View view) {
        disableButtons();

        String fileNameToLoad = editFileName.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(FileLoaderAsync.ARG_FILE_NAME, fileNameToLoad);

        getSupportLoaderManager().restartLoader(LoaderID, bundle, this);
    }

    private void onTextLoaded(String text){
        editContent.setText(text);
        Toast.makeText(this, "Файл " + text + " загружен", Toast.LENGTH_SHORT).show();

        enableButtons();
    }

    public void onSaveButton(View view) {
        disableButtons();

        String fileNameToSave = editFileName.getText().toString();
        String text = editContent.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(FileSaverAsync.ARG_FILE_NAME, fileNameToSave);
        bundle.putString(FileSaverAsync.ARG_FILE_CONTENT, text);

        getSupportLoaderManager().restartLoader(SaverID, bundle, this);
    }

    public void onTextSaved(String res){
        if(res == "true")
            Toast.makeText(this, "Файл  сохранён", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Файл не удалось сохранить", Toast.LENGTH_SHORT).show();

        String curFileName = editFileName.getText().toString();
        saveFileName(curFileName);

        enableButtons();
    }

    public void disableButtons(){
        saveButton.setEnabled(false);
        loadButton.setEnabled(false);
    }

    public void enableButtons(){
        saveButton.setEnabled(true);
        loadButton.setEnabled(true);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        switch (id){
            case SaverID:
                return new FileSaverAsync(this, args);
            case LoaderID:
                return new FileLoaderAsync(this, args);

            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        switch (loader.getId()){
            case SaverID:
                onTextSaved(data);
                break;
            case LoaderID:
                onTextLoaded(data);
                break;
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.i(LOG_TAG, "onLoaderReset");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");

        LoaderManager manager = getSupportLoaderManager();
        manager.destroyLoader(LoaderID);
        manager.destroyLoader(SaverID);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }
}