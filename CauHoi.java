package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CauHoi extends AppCompatActivity implements View.OnClickListener {

    private TextView tvQuestion, tvTotalQuestion;
    private Button btnA, btnB, btnC, btnD;
    private Button btnSumit;
    private ProgressBar progressBar;

    int score = 0;
    int TotalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    CountDownTimer countDownTimer;
    int timerValue = 15;


    private void initUI() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvTotalQuestion = findViewById(R.id.tvTotalQuestion);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnSumit = findViewById(R.id.btnSumit);
        progressBar = findViewById(R.id.progressBar);
    }

    void finishQuiz() {
        String passStatus = "";
        if (score == TotalQuestion) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of" + TotalQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();

    }


    void loadNewQuestion() {

        if (currentQuestionIndex == TotalQuestion) {
            finishQuiz();
            return;
        }

        tvQuestion.setText(QuestionAnswer.question[currentQuestionIndex]);
        btnA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        btnB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        btnC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        btnD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);
     
        initUI();


        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnSumit.setOnClickListener(this);

        tvTotalQuestion.setText("Total question: " + TotalQuestion);
        loadNewQuestion();

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(CauHoi.this);
            }
        };

    }

    @Override
    public void onClick(View view) {

        Button clickButton = (Button) view;
        if (clickButton.getId() == R.id.btnSumit) {
            if (selectedAnswer.equals(QuestionAnswer.correctAnsers[currentQuestionIndex])) {
                score++;
                selectedAnswer = clickButton.getText().toString();
                clickButton.setBackgroundColor(Color.GREEN);
            }
            currentQuestionIndex++;
            loadNewQuestion();


        } else {
            selectedAnswer = clickButton.getText().toString();
            clickButton.setBackgroundColor(Color.RED);
        }
    }


}