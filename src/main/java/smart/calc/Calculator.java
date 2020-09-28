package smart.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Calculator {

    private static final int DEFAULT_SCALE = 2;

    public BigDecimal calculate(String line) {
        return calculate(line, DEFAULT_SCALE);
    }

    public BigDecimal calculate(String line, int scale) {
        Parser parser = new Parser(line);
        List<String> statements = parser.parse();

        Deque<BigDecimal> operands = new ArrayDeque<>();
        for (String statement : statements) {
            if ("+-*/".contains(statement) && operands.size() > 1) {
                BigDecimal b = operands.pollLast();
                BigDecimal a = operands.pollLast();
                BigDecimal value = Operation.valueOf(statement).execute(a, b).setScale(16, RoundingMode.FLOOR);
                operands.offer(value);
            } else {
                operands.offer(new BigDecimal(statement).setScale(16, RoundingMode.FLOOR));
            }
        }

        return operands.pop().setScale(scale, RoundingMode.FLOOR);
    }
}
