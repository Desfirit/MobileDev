package ru.mirea.sulokhin.looper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread{
    private int number = 0;
    Handler handler;
    Handler myHandler;

    @SuppressLint("HandlerLeak")
    @Override
    public void run(){
        Log.d("MyLooper", "run");
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg){
                Log.d("MyLooper", number + ":"+ msg.getData().getString("KEY"));
                number++;
            }
        };

        myHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                Bundle data = msg.getData();
                Log.d("MyLooper", data.getLong("AGE") + " лет, работаю в " + data.getString("WORK"));
            }
        };

        Looper.loop();
    }
}
