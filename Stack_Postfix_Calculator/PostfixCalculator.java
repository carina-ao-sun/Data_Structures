package hw2;

public class PostfixCalculator {
    public static double evaluate(String postfix) {
        Stack<Double> stack = new ArrayStack<Double>();
        String[] tokens = postfix.split(" ");
        for (String token : tokens) {
            if (isOperator(token)) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = evaluate(operand1, operand2, token);
                stack.push(result);
            } else {
                stack.push(Double.parseDouble(token));
            }
        }
        return stack.pop();
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") ||
               token.equals("*") || token.equals("/") ||
               token.equals("^");
    }

    private static double evaluate(double operand1, double operand2, String operator) {
        if (operator.equals("+")) return operand1 + operand2;
        if (operator.equals("-")) return operand1 - operand2;
        if (operator.equals("*")) return operand1 * operand2;
        if (operator.equals("/")) return operand1 / operand2;
        if (operator.equals("^")) return Math.pow(operand1, operand2);
        return 0;
    }
}
