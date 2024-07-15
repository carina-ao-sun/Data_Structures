package hw2;

import java.util.List;

public class Converter {
    private String infixExpression;
    private List<String> tokens;

    public Converter(String infixExpression) {
        this.infixExpression = infixExpression;
        this.tokens = ParserHelper.parse(infixExpression.toCharArray());
    }

    public String toPostFix() {
        String postfix = "";
        Stack<String> stack = new ArrayStack<String>();
        for (String token : tokens) {
            if (isOperator(token)) {
                while (!stack.isEmpty() &&
                       precedence(stack.top()) >= precedence(token)) {
                    postfix += stack.pop() + " ";
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.top().equals("(")) {
                    postfix += stack.pop() + " ";
                }
                stack.pop();
            } else {
                postfix += token + " ";
            }
        }
        while (!stack.isEmpty()) {
            postfix += stack.pop() + " ";
        }
        return postfix;
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^");
    }

    private int precedence(String operator) {
        if (operator.equals("^")) return 3;
        if (operator.equals("*") || operator.equals("/")) return 2;
        if (operator.equals("+") || operator.equals("-")) return 1;
        return 0;
    }
}
