package com.example.calculator;

public class CalculatorModel {
    private int firstArg;
    private int secondArg;

    private StringBuilder inputStr = new StringBuilder();

    private int actionSelected;

    private State state;

    public CalculatorModel() {
        state = State.firstArgInput;
    }

    public void onNumPressed(int buttonId) {

        if (state == State.resultShow) {
            state = State.firstArgInput;
            inputStr.setLength(0);
        }

        if (state == State.operationSelected) {
            state = State.secondArgInput;
            inputStr.setLength(0);
        }

        if (inputStr.length() < 9) {
            switch (buttonId) {
                case R.id.buttonZero:
                    if (inputStr.length() != 0) {
                        inputStr.append("0");
                    }
                    break;
                case R.id.buttonOne:
                    inputStr.append("1");
                    break;
                case R.id.buttonTwo:
                    inputStr.append("2");
                    break;
                case R.id.buttonThree:
                    inputStr.append("3");
                    break;
                case R.id.buttonFour:
                    inputStr.append("4");
                    break;
                case R.id.buttonFive:
                    inputStr.append("5");
                    break;
                case R.id.buttonSix:
                    inputStr.append("6");
                    break;
                case R.id.buttonSeven:
                    inputStr.append("7");
                    break;
                case R.id.buttonEight:
                    inputStr.append("8");
                    break;
                case R.id.buttonNine:
                    inputStr.append("9");
                    break;
            }
        }

    }

    public void onActionPressed(int actionId) {
        if (actionId == R.id.buttonEqual && state == State.secondArgInput && inputStr.length() > 0) {
            secondArg = Integer.parseInt(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);
            switch (actionSelected) {
                case R.id.buttonAddition:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.buttonSubtraction:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.buttonMultiplication:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.buttonDivision:
                    inputStr.append(firstArg / secondArg);
                    break;
            }

        } else if (inputStr.length() > 0 && state == State.firstArgInput) {
            firstArg = Integer.parseInt(inputStr.toString());
            state = State.operationSelected;
            actionSelected = actionId;
        }
    }

    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow
    }
}
