package com.example.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class CalculatorModel implements Parcelable {
    private int firstArg;
    private int secondArg;

    private StringBuilder inputStr = new StringBuilder();

    private int actionSelected;//какое действие выбрал пользователь

    private State state;

    public CalculatorModel() {
        state = State.firstArgInput;
    }

    public static final Creator<CalculatorModel> CREATOR = new Creator<CalculatorModel>() {
        @Override
        public CalculatorModel createFromParcel(Parcel in) {
            return new CalculatorModel(in);
        }

        @Override
        public CalculatorModel[] newArray(int size) {
            return new CalculatorModel[size];
        }
    };

    protected CalculatorModel(Parcel in) {
        firstArg = in.readInt();
        secondArg = in.readInt();
        actionSelected = in.readInt();
    }

    public void onActionPressed(int actionId) {
        if (actionId == R.id.button_equal && state == State.secondArgInput && inputStr.length() > 0) {
            secondArg = Integer.parseInt(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);
            switch (actionSelected) {
                case R.id.button_addition:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.button_subtraction:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.button_multiplication:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.button_division:
                    inputStr.append(firstArg / secondArg);
                    break;
            }
        } else if (actionId == R.id.button_AC && inputStr.length() > 0) {
            inputStr.setLength(0);
        } else if (actionId == R.id.button_C && inputStr.length() > 0) {
            inputStr.setLength(inputStr.length() - 1);
        } else if (actionId == R.id.button_percent && inputStr.length() > 0) {
            Double firstArgDouble = Double.parseDouble(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);//не забываем очищать поле
            Double secondArgDouble = 100.0;
            inputStr.append(firstArgDouble / secondArgDouble);
        } else if (inputStr.length() > 0 && state == State.firstArgInput &&
                actionId != R.id.button_AC && actionId != R.id.button_C) {
            firstArg = Integer.parseInt(inputStr.toString());
            state = State.operationSelected;
            actionSelected = actionId;
        }
    }

    public void onNumPressed(int buttonId) {

        if (state == State.resultShow) {
            state = State.firstArgInput; //если нам показывается результат, то переходим к
            // вводу первого аргумента
            inputStr.setLength(0);
        }

        if (state == State.operationSelected) {
            state = State.secondArgInput;
            inputStr.setLength(0);
        }

        if (inputStr.length() < 9) {
            switch (buttonId) {
                case R.id.button_zero:
                    if (inputStr.length() != 0) {
                        inputStr.append("0");
                    }
                    break;
                case R.id.button_one:
                    inputStr.append("1");
                    break;
                case R.id.button_two:
                    inputStr.append("2");
                    break;
                case R.id.button_three:
                    inputStr.append("3");
                    break;
                case R.id.button_four:
                    inputStr.append("4");
                    break;
                case R.id.button_five:
                    inputStr.append("5");
                    break;
                case R.id.button_six:
                    inputStr.append("6");
                    break;
                case R.id.button_seven:
                    inputStr.append("7");
                    break;
                case R.id.button_eight:
                    inputStr.append("8");
                    break;
                case R.id.button_nine:
                    inputStr.append("9");
                    break;
            }
        }

    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {
            case operationSelected:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .toString();
            case secondArgInput:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(inputStr)
                        .toString();
            case resultShow:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(secondArg)
                        .append(" = ")
                        .append(inputStr.toString())
                        .toString();
            default:
                return inputStr.toString();
        }
    }

    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.button_percent:
                return '%';
            case R.id.button_dot:
                return '.';
            case R.id.button_addition:
                return '+';
            case R.id.button_subtraction:
                return '-';
            case R.id.button_multiplication:
                return '*';
            case R.id.button_division:
            default:
                return '/';
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(firstArg);
        dest.writeInt(secondArg);
        dest.writeInt(actionSelected);
    }

    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow,
    }

}
