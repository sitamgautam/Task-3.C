package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView questionText, questionCountText;
    RadioGroup optionsGroup;
    RadioButton option1, option2, option3, option4;
    Button submitBtn;
    ProgressBar progressBar;

    String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest ocean?",
            "Who wrote 'Romeo and Juliet'?",
            "Which element has atomic number 1?"
    };
    String[][] options = {
            {"London", "Berlin", "Paris", "Rome"},
            {"Earth", "Mars", "Venus", "Jupiter"},
            {"Atlantic", "Indian", "Southern", "Pacific"},
            {"Shakespeare", "Keats", "Chaucer", "Milton"},
            {"Hydrogen", "Oxygen", "Helium", "Carbon"}
    };
    int[] answers = {2, 1, 3, 0, 0};

    int index = 0;
    int score = 0;
    boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        questionCountText = findViewById(R.id.questionCountText);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submitBtn = findViewById(R.id.submitBtn);
        progressBar = findViewById(R.id.progressBar);

        loadQuestion();

        submitBtn.setOnClickListener(v -> {
            if (!answered) {
                int selected = optionsGroup.getCheckedRadioButtonId();
                if (selected == -1) return;

                int answerIndex = optionsGroup.indexOfChild(findViewById(selected));

                if (answerIndex == answers[index]) {
                    score++;
                    ((RadioButton) optionsGroup.getChildAt(answerIndex)).setTextColor(Color.GREEN);
                } else {
                    ((RadioButton) optionsGroup.getChildAt(answerIndex)).setTextColor(Color.RED);
                    ((RadioButton) optionsGroup.getChildAt(answers[index])).setTextColor(Color.GREEN);
                }

                answered = true;
                submitBtn.setText("Next");
            } else {
                index++;
                if (index < questions.length) {
                    loadQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
                answered = false;
            }
        });
    }

    private void loadQuestion() {
        optionsGroup.clearCheck();
        for (int i = 0; i < optionsGroup.getChildCount(); i++) {
            ((RadioButton) optionsGroup.getChildAt(i)).setTextColor(Color.BLACK);
        }

        questionText.setText(questions[index]);
        questionCountText.setText("Question " + (index + 1) + " of " + questions.length);
        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);
        option4.setText(options[index][3]);
        progressBar.setProgress((int) (((double) (index) / questions.length) * 100));
        submitBtn.setText("Submit");
    }
}
