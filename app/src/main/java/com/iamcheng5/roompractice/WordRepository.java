package com.iamcheng5.roompractice;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;


public class WordRepository {


    private WordDao wordDao;
    private LiveData<List<Word>> wordsASC, wordDESC;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        wordsASC = wordDao.getAlphabetizedWordsASC();
        wordDESC = wordDao.getAlphabetizedWordsDESC();
    }

    LiveData<List<Word>> getAllWords(boolean reverse) {
        return reverse ? wordDESC : wordsASC;
    }


    void delete(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            wordDao.delete(word);
        });
    }

    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            wordDao.insert(word);
        });
    }
}
