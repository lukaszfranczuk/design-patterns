package com.be.strategy;

import com.be.strategy.pattern.RpnStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class Rpn {

    private static final String TOKEN_DELIMITER = " ";

    public String calculate(String expression) {
        String[] splitExpression = expression.split(TOKEN_DELIMITER);
        Deque<String> stack = new ArrayDeque<>();
        for (String token : splitExpression) {
            modifyStack(stack, token);
        }
        return stack.pop();
    }

    private void modifyStack(Deque<String> stack, String token) {
        RpnStrategy rpnStrategy = new RpnStrategy();
        if (StringUtils.isNumeric(token)) {
            stack.push(token);
        } else {
            String second = stack.pop();
            String first = stack.pop();
            stack.push(rpnStrategy.calculate(first, second, token));
        }
    }
}