package com.ivlieva.calculator.util;

import java.util.ArrayList;
import java.util.Stack;

public class ReversePolishCalculator {

    public String[] getArr(String str) {
        ReversePolishCalculator calculator = new ReversePolishCalculator();
        String[] arr = str.trim().split("\\s*(\\s)");

        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {       //объединение элементов массива в строку, которая содержит выражение из математической функции
            String st = "";
            if (arr[i].contains("sin") || arr[i].contains("cos") || arr[i].contains("ln") || arr[i].contains("lg") || arr[i].contains("tg") || arr[i].contains("√")) {
                do {
                    st += arr[i++] + " ";
                } while (i < arr.length - 1 && !arr[i].contains("]"));
                arrayList.add(st);
            } else arrayList.add(arr[i]);
        }

        String[] arrStr = arrayList.toArray(new String[arrayList.size()]);

        for (int i = 0; i < arrStr.length; i++) {
            if (arrStr[i].contains("sin") || arrStr[i].contains("cos") || arrStr[i].contains("ln") || arrStr[i].contains("lg") || arrStr[i].contains("tg") || arrStr[i].contains("√")) {
                arrStr[i] = Double.toString(calculator.calculation(arrStr[i]));    // вычисление выражения из математической функции
            }
        }
        return arrStr;
    }

    public double reversePolishNotation(String[] arrStr) {

        Stack<String> stack = new Stack<>();
        Stack<String> stackNum = new Stack<>();

        for (int i = 0; i < arrStr.length; i++) {

            int priority = getPriority(arrStr[i]);
            if (priority < 0) {
                if (arrStr[i].equals("(") || arrStr[i].equals(")")) {
                    if (arrStr[i].equals("(")) stack.push(arrStr[i]);
                    if (arrStr[i].equals(")")) {
                        while (stackNum.size() > 1 && !arrStr[i].equals("(")) {
                            calculationValue(stack, stackNum); //вычисляется выражение в скобках
                            if (stackNum.size() > 1 && stack.peek().equals("("))
                                stack.pop(); //удаляет "("
                        }
                    }
                } else stackNum.push(arrStr[i]);
            } else {

                if (!stack.empty()) {
                    if (priority <= getPriority(stack.peek())) {
                        calculationValue(stack, stackNum);
                        stack.push(arrStr[i]);

                    } else stack.push(arrStr[i]);

                } else {
                    stack.push(arrStr[i]);
                }
            }
            if (i == arrStr.length - 1) {
                while (stackNum.size() > 1) {
                    calculationValue(stack, stackNum);
                }
            }
        }
        return Double.parseDouble(stackNum.peek());
    }

    int getPriority(String st) {
        switch (st) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
        }
        return -1;
    }

    void calculationValue(Stack stack, Stack stackNum) {
        double num2 = Double.parseDouble(stackNum.pop().toString());
        double num1 = Double.parseDouble(stackNum.pop().toString());
        double value = 0;
        switch (stack.peek().toString()) {
            case "*":
                value = num1 * num2;
                break;
            case "/":
                value = num1 / num2;
                break;
            case "^":
                value = Math.pow(num1, num2);
                break;
            case "+":
                value = num1 + num2;
                break;
            case "-":
                value = num1 - num2;
                break;
        }
        stackNum.push(Double.toString(value)); //ответ записывается в стек чисел
        stack.pop(); //удаляется использованная арифметическая операция
    }

    public double calculation(String str) { //вычисление sin, cos, tg и тд

        double num = reversePolishNotation(str.replaceAll("[!^cos]|[!^sin]|[!√]|[!^tg]|[!^ln]|[!^lg]", "").replace("[ ", "").replace("]", "").split(" "));

        if (str.contains("sin")) num = Math.sin(num);
        if (str.contains("cos")) num = Math.cos(num);
        if (str.contains("ln")) num = Math.log(num);
        if (str.contains("lg")) num = Math.log10(num);
        if (str.contains("tg")) num = Math.tan(num);
        if (str.contains("√")) num = Math.sqrt(num);

        return num;
    }
}
