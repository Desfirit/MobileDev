package ru.mirea.sulokhin.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyLooper myLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLooper = new MyLooper();
        myLooper.start();
    }

    public void OnClick1(View view) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", "mirea");
        msg.setData(bundle);
        if (myLooper != null) {
            myLooper.handler.sendMessage(msg);
        }
    }

    public void OnClick2(View view) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("WORK", "Касперский");
        bundle.putLong("AGE", 20);
        msg.setData(bundle);
        if (myLooper != null) {
            myLooper.myHandler.sendMessageDelayed(msg, 1000*20);
        }
    }
}