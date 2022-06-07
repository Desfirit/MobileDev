package ru.mirea.sulokhin.mireaproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Работу выполнил Сулохин Р.Р. БСБО-01-19");
    }

    public LiveData<String> getText() {
        return mText;
    }
}