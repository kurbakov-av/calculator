package smart.calc;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Calculator {

    public BigDecimal calculate(List<String> statements) {
        Deque<BigDecimal> operands = new ArrayDeque<>();
        for (String statement : statements) {
            if ("+-*/".contains(statement) && operands.size() > 1) {
                BigDecimal b = operands.pollLast();
                BigDecimal a = operands.pollLast();
                BigDecimal value = Operation.valueOf(statement).execute(a, b);
                operands.offer(value);
            } else {
                operands.offer(new BigDecimal(statement));
            }
        }

        return operands.poll();
    }
}
