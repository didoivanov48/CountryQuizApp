package com.example.countryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView tvSuccessRate, tvTotalWrong, tvTotalEmpty, tvTotalCorrect;
    private Button btnAgain, btnQuit;
    int correct, wrong, empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvTotalCorrect = findViewById(R.id.tvTotalCorrect);
        tvTotalWrong = findViewById(R.id.tvTotalWrong);
        tvTotalEmpty = findViewById(R.id.tvTotalEmpty);
        tvSuccessRate = findViewById(R.id.tvSuccessRate);

        btnAgain = findViewById(R.id.btnAgain);
        btnQuit = findViewById(R.id.btnQuit);

        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);
        empty = getIntent().getIntExtra("empty", 0);

        tvTotalCorrect.setText("Correct Answers: " + correct);
        tvTotalWrong.setText("Wrong Answers: " + wrong);
        tvTotalEmpty.setText("Empty Answers: " + empty);
        tvSuccessRate.setText("Success Rate: " + (correct * 10) + "%");

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(Intent.ACTION_MAIN);
                newIntent.addCategory(Intent.CATEGORY_HOME);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);
                finish();
            }
        });
    }
}