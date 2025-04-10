package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText nameInput;
    Button startQuizBtn, calculatorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        startQuizBtn = findViewById(R.id.startQuizBtn);
        calculatorBtn = findViewById(R.id.calculatorBtn);

        SharedPreferences prefs = getSharedPreferences("quizPrefs", MODE_PRIVATE);
        String savedName = prefs.getString("userName", "");
        nameInput.setText(savedName);

        startQuizBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("userName", name);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        calculatorBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
        });
    }
}
