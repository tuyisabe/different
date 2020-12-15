package com.ayaanqui.calculatorApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.github.ayaanqui.ExpressionResolver.Expression;
import com.github.ayaanqui.ExpressionResolver.objects.Response;

public class MainActivity extends AppCompatActivity {
    private StringBuilder query = new StringBuilder();
    private Expression calc = new Expression();
    private Response res;
    private TextView expressionTextView, resultTextView, errorsTextView;

    private void setOnClick(int id, String value) {
        final Button b = findViewById(id);

        if (value.equals("=")) {
            b.setOnClickListener(e -> {
                calc.setExpression(query.toString());
                res = calc.solveExpression();

                if (res.success) {
                    String newQuery = Double.toString(res.result);
                    resultTextView.setText(newQuery);
                    expressionTextView.setText(newQuery);
                    query = new StringBuilder(newQuery);
                } else {
                    errorsTextView.setText(res.errors[0]);
                    resultTextView.setText("Error");
                }
            });
        } else if (value.equals("del")) {
            b.setOnClickListener(e -> {
                expressionTextView.setText("");
                errorsTextView.setText("");
                resultTextView.setText("0");
                query = new StringBuilder();
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
    }
}