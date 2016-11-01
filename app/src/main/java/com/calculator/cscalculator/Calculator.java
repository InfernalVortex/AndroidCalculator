package com.calculator.cscalculator;

/**
 * Created by Pranav on 12/5/2015.
 */
public class Calculator {

    private CalculatorViewInterface view;
    private String str;
    private String val;
    private String expression;
    private boolean equalPressed;
    private boolean lastValOper;
    private boolean operationPressed;
    private boolean decimalPressed;
    private double num1;
    private double num2;

    /*
     * Constructor. Initializes the instance variables.
     */
    public Calculator(CalculatorViewInterface view) {
        this.view = view;
        str = "";
        expression = "";
        operationPressed = false;
        equalPressed = false;
    }

    public void inputDigit(char act) {
        lastValOper = false;
        val = "" + act;
        if (str.length() > 2 && (str.substring(str.length() - 1).equals(" "))) {
            lastValOper = true;
        }
        if (val.equals("q")) {
            str = "";
            view.display(str);
        } else {
            if (equalPressed || str.equals("Infinity") || str.equals("-Infinity") || str.equals("NaN")) {
                str = val;
                view.display(str);
            } else if (str.contains(".") && !str.substring(str.length() - 1).equals(" ")) {
                String[] arr = new String[0];
                String num;
                if (str.length() == 0) {
                } else {
                    arr = str.split(" ");
                }
                if (arr.length == 1) {
                    num = arr[0]; //DELETE
                    if (!str.substring(str.length() - 1).equals(".") &&
                            num.substring(num.indexOf(".")).length() > 2) {
                        view.invalid();
                    } else {
                        str += val;
                    }
                }
                if (arr.length == 3) {
                    num = arr[2];
                    if (!str.substring(str.length() - 1).equals(".") && arr[2].contains(".")
                            && num.substring(num.indexOf(".")).length() > 2) {
                        view.invalid();
                    } else
                        str += val;
                }
            } else
                str += val;
            view.display(str);
        }
        if (!val.equals("=")) {
            equalPressed = false;
        }
    }

    public void equal() {
        String[] arr = str.split(" ");
        if (arr.length != 3) {
            view.invalid();
        } else {
            expression = str;
            equalPressed = true;
            operationPressed = false;
            if (arr[1].equals("/"))
                str = "" + (Double.parseDouble(arr[0]) / Double.parseDouble(arr[2]));
            else if (arr[1].equals("*"))
                str = "" + (Double.parseDouble(arr[0]) * Double.parseDouble(arr[2]));
            else if (arr[1].equals("+"))
                str = "" + (Double.parseDouble(arr[0]) + Double.parseDouble(arr[2]));
            else if (arr[1].equals("-"))
                str = "" + (Double.parseDouble(arr[0]) - Double.parseDouble(arr[2]));
            if (!str.equals("NaN") && !str.equals("Infinity") && !str.equals("-Infinity")) {
                str = String.format("%.2f", 10.0);
                if (arr[1].equals("/"))
                    str = "" + String.format("%.2f", Double.parseDouble(arr[0]) / Double.parseDouble(arr[2]));
                else if (arr[1].equals("*"))
                    str = "" + String.format("%.2f", Double.parseDouble(arr[0]) * Double.parseDouble(arr[2]));
                else if (arr[1].equals("+"))
                    str = "" + String.format("%.2f", Double.parseDouble(arr[0]) + Double.parseDouble(arr[2]));
                else if (arr[1].equals("-"))
                    str = "" + String.format("%.2f", Double.parseDouble(arr[0]) - Double.parseDouble(arr[2]));
            }
            view.display(str);
        }
    }

    public void dot() {
        try {
            if (str.length() == 0 || str.substring(str.length() - 1).equals(" ")) {
                view.invalid();
            } else {
                str += ".";
                String[] arr = new String[0];
                try {
                    if (str.length() == 0) {
                    } else
                        arr = str.split(" ");
                    if (arr.length >= 1) {
                        Double.parseDouble(arr[0]);
                        /*if (num.contains(".")) { //DELETE
                            if (num.substring(num.indexOf(".")).length() > 2) {
                                throw new NumberFormatException();
                            }
                        }*/
                    } else if (arr.length == 3) {
                        Double.parseDouble(arr[2]);
                        /*if (num.contains(".")) {
                            if (num.substring(str.indexOf(".")).length() > 2) {
                                throw new NumberFormatException();
                            }
                        }*/
                    }
                } catch (NumberFormatException e) {
                    view.invalid();
                    str = str.substring(0, str.length() - 1);
                }
                view.display(str);
            }
            equalPressed = false;
        } catch (NumberFormatException e) {
            view.invalid();
        }

    }

    public void delete() {
        lastValOper = false;
        if (str.length() > 2 && (str.substring(str.length() - 1).equals(" "))) {
            lastValOper = true;
        }
        if (str.length() == 0) {
        } else if (equalPressed) {
            str = expression;
            operationPressed = true;
        } else if (lastValOper) {
            str = str.substring(0, str.length() - 3);
            operationPressed = false;
        } else if (str.equals("Infinity") || str.equals("NaN") || str.equals("-Infinity"))
            str = expression;
        else
            str = str.substring(0, str.length() - 1);
        view.display(str);
        equalPressed = false;

    }

    public void operator(char op) {
        lastValOper = false;
        val = "" + op;
        if (str.length() > 2 && (str.substring(str.length() - 1).equals(" "))) {
            lastValOper = true;
        }
        if (str.length() == 0 || str.equals("Infinity") || str.equals("NaN") || str.equals("-Infinity")) {
            view.invalid();
            return;
        }
        if (val.equals("*")) {
            if (lastValOper) {
                str = str.substring(0, str.length() - 3);
                operationPressed = true;
                str += " * ";
                view.display(str);
            } else if (operationPressed) {
                view.invalid();
                return;
            } else {
                operationPressed = true;
                str += " * ";
                view.display(str);
            }
        } else if (val.equals("/")) {
            if (lastValOper) {
                str = str.substring(0, str.length() - 3);
                operationPressed = true;
                str += " / ";
                view.display(str);
            } else if (operationPressed) {
                view.invalid();
                return;
            } else {
                operationPressed = true;
                str += " / ";
                view.display(str);
            }
        } else if (val.equals("+")) {
            if (lastValOper) {
                str = str.substring(0, str.length() - 3);
                operationPressed = true;
                str += " + ";
                view.display(str);
            } else if (operationPressed) {
                view.invalid();
                return;
            } else {
                operationPressed = true;
                str += " + ";
                view.display(str);
            }
        } else if (val.equals("-")) {
            if (lastValOper) {
                str = str.substring(0, str.length() - 3);
                operationPressed = true;
                str += " - ";
                view.display(str);
            } else if (operationPressed) {
                view.invalid();
                return;
            } else {
                operationPressed = true;
                str += " - ";
                view.display(str);
            }
        }
        equalPressed = false;
    }
}