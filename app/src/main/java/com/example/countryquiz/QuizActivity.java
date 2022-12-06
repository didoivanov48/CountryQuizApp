package com.example.countryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
    private TextView tvCorrect, tvWrong, tvEmpty, tvQuestion;
    private ImageView ivFlag, ivNext;
    private Button btnA, btnB, btnC, btnD;

    private FlagsDatabase fdatabase;
    private ArrayList<FlagsModel> questionsList;

    int correct = 0;
    int wrong = 0;
    int empty = 0;
    int question = 0;

    private FlagsModel correctFlag;

    private ArrayList<FlagsModel> wrongOptionsList;

    HashSet<FlagsModel> mixOptions = new HashSet<>();
    ArrayList<FlagsModel> options = new ArrayList<>();

    boolean buttonControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);
        tvEmpty = findViewById(R.id.tvEmpty);
        tvQuestion = findViewById(R.id.tvQuestion);

        ivFlag = findViewById(R.id.ivFlag);
        ivNext = findViewById(R.id.ivNext);

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        fdatabase = new FlagsDatabase(QuizActivity.this);
        questionsList = new FlagsDAO().getRandomTenQueastion(fdatabase);

        loadQuestions();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(btnA);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(btnB);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(btnC);
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(btnD);
            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question++;

                if (!buttonControl && question < 10) {
                    empty++;
                    tvEmpty.setText("Empty: " + empty);
                    loadQuestions();
                } else if (buttonControl && question < 10) {
                    loadQuestions();

                    btnA.setClickable(true);
                    btnB.setClickable(true);
                    btnC.setClickable(true);
                    btnD.setClickable(true);

                    btnA.setBackgroundColor(getResources().getColor(R.color.button));
                    btnB.setBackgroundColor(getResources().getColor(R.color.button));
                    btnC.setBackgroundColor(getResources().getColor(R.color.button));
                    btnD.setBackgroundColor(getResources().getColor(R.color.button));
                } else if (question == 10) {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    intent.putExtra("empty", empty);
                    startActivity(intent);
                    finish();
                }

                buttonControl = false;
            }
        });
    }

    public void loadQuestions() {
        tvQuestion.setText("Question: " + (question + 1));

        correctFlag = questionsList.get(question);

        ivFlag.setImageResource(getResources().getIdentifier(correctFlag.getFlag_image(), "drawable", getPackageName()));

        wrongOptionsList = new FlagsDAO().getRandomThreeOptions(fdatabase, correctFlag.getFlag_id());

        mixOptions.clear();
        mixOptions.add(correctFlag);
        mixOptions.add(wrongOptionsList.get(0));
        mixOptions.add(wrongOptionsList.get(1));
        mixOptions.add(wrongOptionsList.get(2));

        options.clear();
        for (FlagsModel flg : mixOptions) {
            options.add(flg);
        }

        btnA.setText(options.get(0).getFlag_name());
        btnB.setText(options.get(1).getFlag_name());
        btnC.setText(options.get(2).getFlag_name());
        btnD.setText(options.get(3).getFlag_name());

    }

    public void answerControl(Button btn) {
        String buttonText = btn.getText().toString();
        String correctAnswer = correctFlag.getFlag_name();

        if (buttonText.equals(correctAnswer)) {
            correct++;
            btn.setBackgroundColor(Color.GREEN);
        } else {
            wrong++;
            btn.setBackgroundColor(Color.BLUE);

            if (btnA.getText().toString().equals(correctAnswer)) {
                btnA.setBackgroundColor(Color.GREEN);
            }
            if (btnB.getText().toString().equals(correctAnswer)) {
                btnB.setBackgroundColor(Color.GREEN);
            }
            if (btnC.getText().toString().equals(correctAnswer)) {
                btnC.setBackgroundColor(Color.GREEN);
            }
            if (btnD.getText().toString().equals(correctAnswer)) {
                btnD.setBackgroundColor(Color.GREEN);
            }
        }
        btnA.setClickable(false);
        btnB.setClickable(false);
        btnC.setClickable(false);
        btnD.setClickable(false);

        tvCorrect.setText("Correct: " + correct);
        tvWrong.setText("Wrong: " + wrong);

        buttonControl = true;
    }
}