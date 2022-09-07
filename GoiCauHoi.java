package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GoiCauHoi extends AppCompatActivity {
    private Button btnEducation, btnGame, btnSport, btnNext;
    private TextView tvTitle;

    String goiCauHoi = "";

    public void initUI() {
        btnEducation = findViewById(R.id.btnEducation);
        btnGame = findViewById(R.id.btnGame);
        btnSport = findViewById(R.id.btnSport);
        tvTitle = findViewById(R.id.tvTitle);
        btnNext = findViewById(R.id.btnNext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goi_cau_hoi);
        initUI();
        btnNext.setBackgroundColor(Color.GRAY);
        btnEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goiCauHoi = "Education";
                btnEducation.setBackgroundColor(Color.CYAN);
            }
        });

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goiCauHoi = "Game";
                btnEducation.setBackgroundColor(Color.CYAN);
            }
        });

        btnSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goiCauHoi = "Sport";
                btnEducation.setBackgroundColor(Color.CYAN);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(goiCauHoi.isEmpty()){
                    Toast.makeText(GoiCauHoi.this, "Please select the Topic", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i1 = new Intent(GoiCauHoi.this, CauHoi.class);
                    i1.putExtra("goiCauHoi", goiCauHoi);
                    startActivity(i1);
                }
            }
        });

    }
}