package com.github.naturquiz;

import android.support.v7.app.AppCompatActivity;

import com.github.naturquiz.QuestionManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    QuestionManager q_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        q_manager = new QuestionManager(this);
    }
}
