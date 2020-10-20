package com.mamboexample.roomwordexample.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mamboexample.roomwordexample.entities.Word;
import com.mamboexample.roomwordexample.local.WordDao;
import com.mamboexample.roomwordexample.local.WordDatabase;

import java.util.List;

public class WordRepository {

    private LiveData<List<Word>> mAllWords;
    private WordDao mWordDao;

    public WordRepository(Application application) {

        WordDatabase db = WordDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getWords();

    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public LiveData<List<Word>> getAlphabetizedWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insertWord(word);
        });
    }

    public void delete(Word word) {
        WordDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.deleteWord(word);
        });
    }

    public void deleteAll() {
        WordDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.deleteAll();
        });
    }
}
