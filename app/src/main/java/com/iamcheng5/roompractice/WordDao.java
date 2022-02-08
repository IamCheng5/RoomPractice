package com.iamcheng5.roompractice;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY WORD ASC")
    LiveData<List<Word>> getAlphabetizedWordsASC();

    @Query("SELECT * FROM word_table ORDER BY WORD DESC")
    LiveData<List<Word>> getAlphabetizedWordsDESC();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();
}
