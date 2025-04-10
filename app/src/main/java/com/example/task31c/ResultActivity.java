package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView scoreText, nameText;
    Button restartBtn, finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreText = findViewById(R.id.scoreText);
        nameText = findViewById(R.id.nameText);
        restartBtn = findViewById(R.id.restartBtn);
        finishBtn = findViewById(R.id.finishBtn);

        int score = getIntent().getIntExtra("score", 0);
        SharedPreferences prefs = getSharedPreferences("quizPrefs", MODE_PRIVATE);
        String name = prefs.getString("userName", "User");

        scoreText.setText("Your Score: " + score + " / 5");
        nameText.setText("Hello, " + name + "!");

        restartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            startActivity(intent);
            finish();
        });

        finishBtn.setOnClickListener(v -> finishAffinity());
    }
}
