package ru.mirea.sulokhin.intentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGoToMireaButtonClick(View view)
    {
        Log.i("onGoToMireaButtonClick", "Enter begin method");

        Uri address = Uri.parse("https://www.mirea.ru/");

        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);

        if(openLinkIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(openLinkIntent);
        }
        else
        {
            Log.d("Intent", "Не получается обработать намерение!");
        }

        Log.i("onGoToMireaButtonClick", "End method");
    }

    public void onAnotherButtonClick(View view)
    {
        Log.i("onAnotherButtonClick", "Enter begin method");

        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "ФАМИЛИЯ ИМЯ ОТЧЕСТВО");

        startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"));

        Log.i("onAnotherButtonClick", "End method");
    }
}