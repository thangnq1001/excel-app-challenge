import java.util.*;

public class Solution {
    private Map<String, String> cellToValue;

    private static String SPACE = " ";
    private interface Operator {
        String PLUS = "+";
        String MINUS = "-";
        String MULTIPLY = "*";
        String DIVIDE = "/";
    }

    public Solution(Map<String, String> cellToValue) {
        this.cellToValue = cellToValue;
    }

    public void print() {
        for (Map.Entry<String, String> entry : cellToValue.entrySet()) {
            System.out.println(entry.getKey());
            double doubleVal = Double.parseDouble(entry.getValue());
            if (isInteger(doubleVal)) {
                System.out.println((int) doubleVal);
            } else {
                System.out.println(entry.getValue());
            }
        }
    }

    private static boolean isInteger(double s) {
        return s == (int) s;
    }

    public void solve() {
        while (true) {
            boolean isAllCalculated = true;
            boolean isUpdated = false;
            Map<String, String> cellToDependency = new HashMap<>();
            for (Map.Entry<String, String> entry : cellToValue.entrySet()) {
                if (isNumber(entry.getValue())) {
                    continue;
                }
                String[] strings = entry.getValue().split(SPACE);
                for (String s : strings) {
                    if (isCell(s)) {
                        String valOfDependency = cellToValue.get(s);
                        if (isNumber(valOfDependency)) {
                            isUpdated = true;
                            String newVal = entry.getValue().replace(s, valOfDependency);
                            entry.setValue(newVal);
                        } else {
                            cellToDependency.put(s, getDependency(valOfDependency));
                        }
                    }
                }
                if (isCalculable(entry.getValue())) {
                    String numVal = String.valueOf(calculate(entry.getValue()));
                    entry.setValue(numVal);
                } else {
                    isAllCalculated = false;
                }
            }
            if (!isUpdated) {
                for (Map.Entry<String, String> entry : cellToDependency.entrySet()) {
                    if (cellToDependency.containsKey(entry.getValue())) {
                        System.out.println("Circular dependency between " + entry.getKey() + " and " + entry.getValue() + " detected");
                        return;
                    }
                }
                return;
            }
            if (isAllCalculated) {
                break;
            }
        }
        print();
    }

    private static String getDependency(String formula) {
        String[] strings = formula.split(SPACE);
        for (String s : strings) {
            if (isCell(s)) {
                return s;
            }
        }
        return null;
    }

    /**
     * @param formula a postfix expression
     * @return true if there are no dependencies
     */
    private static boolean isCalculable(String formula) {
        String[] strings = formula.split(SPACE);
        for (String s : strings) {
            if (isCell(s)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOperator(String s) {
        switch (s) {
            case Operator.PLUS:
            case Operator.MINUS:
            case Operator.MULTIPLY:
            case Operator.DIVIDE:
                return true;
        }
        return false;
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isCell(String s) {
        return !isNumber(s) && !isOperator(s);
    }

    /**
     * @param formula a postfix expression
     * @return result of the postfix expression
     */
    private static double calculate(String formula) {
        Stack<Double> stack = new Stack<>();
        for (String s : formula.split(SPACE)) {
            if (isNumber(s)) {
                stack.push(Double.parseDouble(s));
            } else { // if (isOperator(s))
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = calculate(operand1, operand2, s);
                stack.push(result);
            }
        }
        return stack.pop();
    }

    /**
     * @return d1 "operator" d2
     */
    private static double calculate(double d1, double d2, String operator) {
        switch (operator) {
            case Operator.PLUS:
                return d1 + d2;
            case Operator.MINUS:
                return d1 - d2;
            case Operator.MULTIPLY:
                return d1 * d2;
            case Operator.DIVIDE:
                return d1 / d2;
        }
        return 0.0;
    }

}
