package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    EditText num1, num2;
    Button addBtn, subBtn;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        addBtn = findViewById(R.id.addBtn);
        subBtn = findViewById(R.id.subBtn);
        resultView = findViewById(R.id.resultView);

        addBtn.setOnClickListener(v -> calculate('+'));
        subBtn.setOnClickListener(v -> calculate('-'));
    }

    void calculate(char op) {
        String n1 = num1.getText().toString();
        String n2 = num2.getText().toString();

        if (n1.isEmpty() || n2.isEmpty()) {
            resultView.setText("Please enter both numbers.");
            return;
        }

        int a = Integer.parseInt(n1);
        int b = Integer.parseInt(n2);
        int result = (op == '+') ? a + b : a - b;

        resultView.setText("Result: " + result);
    }
}
