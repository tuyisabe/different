package com.ayaanqui.calculatorApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.github.ayaanqui.expressionresolver.Resolver;
import com.github.ayaanqui.expressionresolver.objects.Response;

public class MainActivity extends AppCompatActivity {
    private StringBuilder query = new StringBuilder();
    private Resolver resolver = new Resolver();
    private Response res;
    private TextView expressionTextView, resultTextView, errorsTextView;
    private boolean parenthOpen = false;

    private void setOnClick(int id, String value) {
        final Button b = findViewById(id);

        if (value.equals("=")) {
            b.setOnClickListener(e -> {
                res = resolver
                        .setExpression(query.toString())
                        .solveExpression();

                if (res.success) {
                    String newQuery = Double.toString(res.result);
                    resultTextView.setText(newQuery);
                    expressionTextView.setText(newQuery);
                    query = new StringBuilder(newQuery);
                    errorsTextView.setText("");
                } else {
                    errorsTextView.setText(res.errors[0]);
                    resultTextView.setText("Error");
                }
            });
        } else if (value.equals("del")) {
            b.setOnClickListener(e -> {
                if (query.length() > 0) {
                    if (query.charAt(query.length() - 1) == ')')
                        parenthOpen = true;

                    query.deleteCharAt(query.length() - 1);
                    
                    if (query.length() < 1)
                        parenthOpen = false;

                    expressionTextView.setText(query.toString());
                    errorsTextView.setText("");
                }
            });
        } else if (value.equals("ac")) {
            b.setOnClickListener(e -> {
                expressionTextView.setText("");
                resultTextView.setText("0");
                query = new StringBuilder();
                errorsTextView.setText("");
                parenthOpen = false;
            });
        } else if (value.equals("parenth")) {
            b.setOnClickListener(e -> {
                if (!parenthOpen) {
                    query.append("(");
                    parenthOpen = true;
                } else {
                    query.append(")");
                    parenthOpen = false;
                }
                expressionTextView.setText(query.toString());
            });
        } else {
            b.setOnClickListener(e -> {
                query.append(value);
                expressionTextView.setText(query);
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionTextView = findViewById(R.id.expressionTextView);
        resultTextView = findViewById(R.id.resultTextView);
        errorsTextView = findViewById(R.id.errorsTextView);

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
        setOnClick(R.id.buttonAc, "ac");
        setOnClick(R.id.buttonParenth, "parenth");
    }
}