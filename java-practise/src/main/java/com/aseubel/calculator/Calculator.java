package com.aseubel.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 计算器，逆波兰表达式计算器
 *
 * @author Aseubel
 * @date 2025/9/25 上午10:52
 */
public class Calculator {

    public String evaluate(String expression) {
        // 移除空白字符
        String sanitized = expression.replaceAll("\\s+", "");
        List<String> postfix = infixToPostfix(sanitized);
        return evaluatePostfix(postfix);
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0; // 括号
    }

    private List<String> infixToPostfix(String expression) {
        // 用于存储后缀表达式的列表
        List<String> postfix = new ArrayList<>();
        // 用于存储操作符的栈
        Stack<Character> operatorStack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (Character.isDigit(c) || c == '.') {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i++));
                }
                i--;
                postfix.add(number.toString());
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.add(String.valueOf(operatorStack.pop()));
                }
                operatorStack.pop(); // Pop '('
            } else { // 执行操作
                while (!operatorStack.isEmpty() && precedence(c) <= precedence(operatorStack.peek())) {
                    postfix.add(String.valueOf(operatorStack.pop()));
                }
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.add(String.valueOf(operatorStack.pop()));
        }
        return postfix;
    }

    private String evaluatePostfix(List<String> postfix) {
        Stack<BigDecimal> operandStack = new Stack<>();
        for (String token : postfix) {
            try {
                operandStack.push(new BigDecimal(token));
            } catch (NumberFormatException e) {
                BigDecimal val2 = operandStack.pop();
                BigDecimal val1 = operandStack.pop();
                
                switch (token.charAt(0)) {
                    case '+':
                        operandStack.push(val1.add(val2));
                        break;
                    case '-':
                        operandStack.push(val1.subtract(val2));
                        break;
                    case '*':
                        operandStack.push(val1.multiply(val2));
                        break;
                    case '/':
                        if (val2.compareTo(BigDecimal.ZERO) == 0) {
                            return "Error: Division by zero";
                        }
                        // 如果可以整除，直接计算
                        if (val1.remainder(val2).compareTo(BigDecimal.ZERO) == 0) {
                            operandStack.push(val1.divide(val2));
                        } else {
                            // 否则，处理可能存在的循环小数
                            return formatRepeatingDecimal(val1, val2);
                        }
                        break;
                }
            }
        }
        return operandStack.pop().stripTrailingZeros().toPlainString();
    }

    private String formatRepeatingDecimal(BigDecimal numerator, BigDecimal denominator) {
        BigDecimal integerPart = numerator.divide(denominator, 0, RoundingMode.DOWN);
        StringBuilder fractionalPart = new StringBuilder();
        Map<BigDecimal, Integer> remainders = new HashMap<>();
        
        BigDecimal remainder = numerator.remainder(denominator);
        
        while (remainder.compareTo(BigDecimal.ZERO) != 0 && !remainders.containsKey(remainder)) {
            remainders.put(remainder, fractionalPart.length());
            remainder = remainder.multiply(BigDecimal.TEN);
            fractionalPart.append(remainder.divide(denominator, 0, RoundingMode.DOWN));
            remainder = remainder.remainder(denominator);
        }

        if (remainder.compareTo(BigDecimal.ZERO) == 0) {
            // 没有循环节，但不是整除
            BigDecimal result = numerator.divide(denominator, 100, RoundingMode.HALF_UP);
            return result.stripTrailingZeros().toPlainString();
        } else {
            int startIndex = remainders.get(remainder);
            String nonRepeating = fractionalPart.substring(0, startIndex);
            String repeating = fractionalPart.substring(startIndex);
            return integerPart.toPlainString() + "." + nonRepeating + "[" + repeating + "]";
        }
    }
}