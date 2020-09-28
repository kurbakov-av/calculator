package smart.calc;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private static String collectionToSequenceString(Collection<String> collection) {
        return String.join(" ", collection);
    }

    @Test
    void sum() {
        final String line = "2+2";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("2 2 +", collectionToSequenceString(actual));
    }

    @Test
    void sumFewOperands() {
        final String line = "1+2+3+4+5+6";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 + 3 + 4 + 5 + 6 +", collectionToSequenceString(actual));
    }

    @Test
    void subtract() {
        final String line = "2-2";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("2 2 -", collectionToSequenceString(actual));
    }

    @Test
    void subtractFewOperands() {
        final String line = "1-2-3-4-5-6";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 - 3 - 4 - 5 - 6 -", collectionToSequenceString(actual));
    }

    @Test
    void sumAndSubtract() {
        final String line = "2+2-2";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("2 2 + 2 -", collectionToSequenceString(actual));
    }

    @Test
    void sumAndSubtractFewOperands() {
        final String line = "1+2-3+4-5-6+7";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 + 3 - 4 + 5 - 6 - 7 +", collectionToSequenceString(actual));
    }

    @Test
    void multiply() {
        final String line = "2*2";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("2 2 *", collectionToSequenceString(actual));
    }

    @Test
    void multiplyFewOperands() {
        final String line = "1*2*3*4*5*6";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 * 3 * 4 * 5 * 6 *", collectionToSequenceString(actual));
    }

    @Test
    void divide() {
        final String line = "2/2";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("2 2 /", collectionToSequenceString(actual));
    }

    @Test
    void divideFewOperands() {
        final String line = "1/2/3/4/5/6";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 / 3 / 4 / 5 / 6 /", collectionToSequenceString(actual));
    }

    @Test
    void multiplyAndDivide() {
        final String line = "1*2/3";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 * 3 /", collectionToSequenceString(actual));
    }

    @Test
    void multiplyAndDivideFewOperands() {
        final String line = "1*2/3*4*5/6/7";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 * 3 / 4 * 5 * 6 / 7 /", collectionToSequenceString(actual));
    }

    @Test
    void allOperations() {
        final String line = "1*2+3/4-5+6*7-8/9";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 * 3 4 / + 5 - 6 7 * + 8 9 / -", collectionToSequenceString(actual));
    }

    @Test
    void bracketPriority() {
        final String line = "1*(2+3)";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 3 + *", collectionToSequenceString(actual));
    }

    @Test
    void innerBracketPriority() {
        final String line = "1*(2/(3-4))";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 3 4 - / *", collectionToSequenceString(actual));
    }

    @Test
    void sequenceBracketPriority() {
        final String line = "1*(2+3)/(3-4)";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 3 + * 3 4 - /", collectionToSequenceString(actual));
    }

    @Test
    void innerAndSequenceBracketPriority() {
        final String line = "1*(2/(3-4))/1*(2+3)/(3-4)";

        Parser parser = new Parser(line);
        List<String> actual = parser.parse();

        assertEquals("1 2 3 4 - / * 1 / 2 3 + * 3 4 - /", collectionToSequenceString(actual));
    }
}