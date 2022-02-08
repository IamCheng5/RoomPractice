package com.iamcheng5.roompractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {
    public static String EXTRA_REPLY = "com.iamcheng5.roompractice.REPLY";
    private EditText etEditWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        etEditWord = findViewById(R.id.newWordAct_et_edit_word);
        Button button = findViewById(R.id.newWordAct_btn_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(etEditWord.getText().toString().trim())){
                setResult(RESULT_CANCELED,replyIntent);
            }else{
                replyIntent.putExtra(EXTRA_REPLY,etEditWord.getText().toString().trim());
                setResult(RESULT_OK,replyIntent);
            }
            finish();
        });
    }
}