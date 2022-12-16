package com.myapps.majorprojectversion5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Survey extends AppCompatActivity {

    TextView tvQuestion, tvOption1, tvOption2, tvOption3, tvOption4;
    MaterialButton materialButton;
    TextInputEditText textInputEditText;
    String wallet;
    String answer;
    String question, option1, option2, option3, option4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "");

        materialButton = findViewById(R.id.materialButton);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvOption1 = findViewById(R.id.tvOption1);
        tvOption2 = findViewById(R.id.tvOption2);
        tvOption3 = findViewById(R.id.tvOption3);
        tvOption4 = findViewById(R.id.tvOption4);
        textInputEditText = findViewById(R.id.etCorrectAnswer);
        String title = getIntent().getStringExtra("title");



    }

    @Override
    public void onBackPressed() {

    }
}

class Coins {
    String coins;

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public Coins() {
    }

    public Coins(String coins) {
        this.coins = coins;
    }
}