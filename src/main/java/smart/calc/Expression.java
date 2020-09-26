package smart.calc;

import java.math.BigDecimal;

@FunctionalInterface
public interface Expression {
    BigDecimal compute(BigDecimal a, BigDecimal b);
}