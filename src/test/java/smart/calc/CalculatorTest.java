package smart.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @Test
    void sum() {
        final String line = "2+2";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(4, actual.intValue());
    }

    @Test
    void sumFewOperands() {
        final String line = "1+2+3+4+5+6";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(21, actual.intValue());
    }

    @Test
    void subtract() {
        final String line = "2-2";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(0, actual.intValue());
    }

    @Test
    void subtractFewOperands() {
        final String line = "1-2-3-4-5-6";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(-19, actual.intValue());
    }

    @Test
    void sumAndSubtract() {
        final String line = "2+2-2";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(2, actual.intValue());
    }

    @Test
    void sumAndSubtractFewOperands() {
        final String line = "1+2-3+4-5-6+7";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(0, actual.intValue());
    }

    @Test
    void multiply() {
        final String line = "2*2";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(4, actual.intValue());
    }

    @Test
    void multiplyFewOperands() {
        final String line = "1*2*3*4*5*6";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(720, actual.intValue());
    }

    @Test
    void divide() {
        final String line = "2/2";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(1, actual.intValue());
    }

    @Test
    void divideFewOperands() {
        final String line = "100/2/3/4";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(new BigDecimal("4.16"), actual);
    }

    @Test
    void multiplyAndDivide() {
        final String line = "1*2/3";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(new BigDecimal("0.66"), actual);
    }

    @Test
    void multiplyAndDivideFewOperands() {
        final String line = "1*2/3*4*5/6/7";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(new BigDecimal("0.31"), actual);
    }

    @Test
    void allOperations() {
        final String line = "1*2+3/4-5+6*7-8/9";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(new BigDecimal("38.86"), actual);
    }

    @Test
    void bracketPriority() {
        final String line = "1*(2+3)";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(5, actual.intValue());
    }

    @Test
    void innerBracketPriority() {
        final String line = "1*(2/(3-4))";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(-2, actual.intValue());
    }

    @Test
    void sequenceBracketPriority() {
        final String line = "1*(2+3)/(3-4)";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(-5, actual.intValue());
    }

    @Test
    void innerAndSequenceBracketPriority() {
        final String line = "1*(2/(3-4))/1*(2+3)/(3-4)";

        BigDecimal actual = calculator.calculate(line);

        assertEquals(10, actual.intValue());
    }
}