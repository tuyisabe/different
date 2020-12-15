package com.ayaanqui.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    StringBuilder query = new StringBuilder();

    Calculator c = new Calculator();

    private void setOnClick(int id, String value) {
        final Button b = findViewById(id);
        final TextView expressionTextView = findViewById(R.id.expressionTextView);
        b.setOnClickListener(e -> {
            query.append(value);
            expressionTextView.setText(query);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Num pad buttons
        setOnClick(R.id.button0, "0");
        setOnClick(R.id.button1, "1");
        setOnClick(R.id.button2, "2");
        setOnClick(R.id.button3, "3");
        setOnClick(R.id.button4, "4");
        setOnClick(R.id.button5, "5");
        setOnClick(R.id.button6, "6");
        setOnClick(R.id.button7, "7");
        setOnClick(R.id.button8, "8");
        setOnClick(R.id.button9, "9");

        // Operator buttons
        setOnClick(R.id.buttonAdd, "+");
        setOnClick(R.id.buttonSub, "-");
        setOnClick(R.id.buttonMul, "*");
        setOnClick(R.id.buttonDiv, "/");
        setOnClick(R.id.buttonDec, ".");
        setOnClick(R.id.buttonEq, "=");
        setOnClick(R.id.buttonDel, "del");
    }
}