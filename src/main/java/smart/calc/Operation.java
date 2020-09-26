package smart.calc;

import java.math.BigDecimal;

public class Operation implements Comparable<Operation> {

    private static final Operation PLUS = new Operation("+", 1, BigDecimal::add);

    private static final Operation SUBTRACT = new Operation("-", 1, BigDecimal::subtract);

    private static final Operation MULTIPLY = new Operation("*", 2, BigDecimal::multiply);

    private static final Operation DIVIDE = new Operation("/", 2, BigDecimal::divide);

    private final String sign;

    private final Integer priority;

    private final Expression expression;

    public Operation(String sign, Integer priority, Expression expression) {
        this.sign = sign;
        this.priority = priority;
        this.expression = expression;
    }

    public static Operation valueOf(String value) {
        switch (value) {
            case "+":
            case "--":
                return PLUS;
            case "-":
                return SUBTRACT;
            case "*":
                return MULTIPLY;
            case "/":
                return DIVIDE;
            default:
                throw new IllegalArgumentException();
        }
    }

    public BigDecimal execute(BigDecimal a, BigDecimal b) {
        return expression.compute(a, b);
    }

    @Override
    public int compareTo(Operation o) {
        return priority.compareTo(o.priority);
    }
}