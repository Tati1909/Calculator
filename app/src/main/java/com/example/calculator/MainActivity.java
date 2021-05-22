package com.example.calculator;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CalculatorModel calculator;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private char CURRENT_ACTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] numberIds = new int[]{
                R.id.buttonZero,
                R.id.buttonOne,
                R.id.buttonTwo,
                R.id.buttonThree,
                R.id.buttonFour,
                R.id.buttonFive,
                R.id.buttonSix,
                R.id.buttonSeven,
                R.id.buttonEight,
                R.id.buttonNine

        };

        int[] actionsIds = new int[]{
                R.id.buttonAC,
                R.id.buttonC,
                R.id.buttonAddition,
                R.id.buttonDot,
                R.id.buttonMultiplication,
                R.id.buttonDivision,
                R.id.buttonPercent,
                R.id.buttonSubtraction,
                R.id.buttonEqual
        };

        calculator = new CalculatorModel();

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onNumPressed(view.getId());
            }
        };

        View.OnClickListener actionButtonOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onActionPressed(view.getId());
            }
        };
        for (int i = 0; i < numberIds.length; i++) {
            findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }

        for (int i = 0; i < actionsIds.length; i++) {
            findViewById(actionsIds[i]).setOnClickListener(actionButtonOnclickListener);
        }

    }
}