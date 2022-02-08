package com.iamcheng5.roompractice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository wordRepository;
    private MutableLiveData<Boolean> isReverseWords;
    private LiveData<List<Word>> words;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        isReverseWords = new MutableLiveData<>(false);
        words = Transformations.switchMap(isReverseWords, input -> wordRepository.getAllWords(input));
    }

    public void setReverseWords(boolean reverse){
        isReverseWords.setValue(reverse);
    }
    public LiveData<List<Word>> getWords(){
        return words;
    }
    public void deleteWord(Word word){
        wordRepository.delete(word);
    }
    public void insertWord(Word word){
        wordRepository.insert(word);
    }
}
