package ru.mirea.sulokhin.practice4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String>{
    private int CalcerID = 228;

    TextView lessonsCountTextView;
    TextView daysCountTextView;

    TextView outTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lessonsCountTextView = findViewById(R.id.editLessonsCount);
        daysCountTextView = findViewById(R.id.editLessonDaysCount);

        outTextView = findViewById(R.id.outputTextView);

        Thread mainThread = Thread.currentThread();
        outTextView.setText("Текущий поток: " + mainThread.getName());
// Меняем имя и выводим в текстовом поле
        mainThread.setName("MireaThread");
        outTextView.append("\n Новое имя потока: " + mainThread.getName());

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if(id == CalcerID){
            return new MyLoader(this, args);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (loader.getId() == CalcerID) {
            outTextView.setText("Среднее количество пар в день = " + data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void onCalcButtonClick(View view) {
        try {
            int lessonsCount = Integer.parseInt(lessonsCountTextView.getText().toString());
            int daysCount = Integer.parseInt(daysCountTextView.getText().toString());

            Bundle bundle = new Bundle();
            bundle.putInt(MyLoader.ARG_LESSONS_COUNT, lessonsCount);
            bundle.putInt(MyLoader.ARG_DAYS_COUNT, daysCount);

            getSupportLoaderManager().initLoader(CalcerID, bundle, this);
        }
        catch (NumberFormatException e)
        {
            outTextView.setText("Вводите числа!");
        }
    }
}