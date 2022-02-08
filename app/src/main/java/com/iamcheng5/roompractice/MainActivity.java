package com.iamcheng5.roompractice;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements WordListAdapter.OnWordListItemClicked {
    private WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter wordListAdapter = new WordListAdapter( this);
        recyclerView.setAdapter(wordListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wordViewModel.getWords().observe(this, words -> {
            wordListAdapter.submitList(words);
        });

        ActivityResultLauncher<Intent> newWordActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK){
                wordViewModel.insertWord(new Word(result.getData().getStringExtra(NewWordActivity.EXTRA_REPLY)));
            }else{
                Toast.makeText(
                        getApplicationContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton button = findViewById(R.id.fab);
        button.setOnClickListener(view -> {
            newWordActivityLauncher.launch(new Intent(MainActivity.this, NewWordActivity.class));

        });

        ToggleButton toggleButton = findViewById(R.id.toggle_btn);
        toggleButton.setOnCheckedChangeListener((compoundButton, b) -> {
            wordViewModel.setReverseWords(!b);
        });
    }

    @Override
    public void onItemClicked(Word word) {
        wordViewModel.deleteWord(word);
    }
}