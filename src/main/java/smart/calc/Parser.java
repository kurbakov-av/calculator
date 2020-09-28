package smart.calc;

import java.util.*;

public class Parser {

    private static final String DELIMITERS = "()+-*/";

    private final String source;

    public Parser(String source) {
        this.source = source;
    }

    public List<String> parse() {
        List<String> output = new ArrayList<>();
        Deque<String> operations = new ArrayDeque<>();
        TokenIter iter = new TokenIter(new StringTokenizer(source, DELIMITERS, true));
        while (iter.hasNext()) {
            String token = iter.next();
            switch (token) {
                case "(":
                    operations.push(token);
                    break;
                case ")":
                    while (!operations.isEmpty()) {
                        String operation = operations.pop();
                        if (!operation.equals("(")) {
                            output.add(operation);
                        }
                    }
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    while (!operations.isEmpty() && getPriority(operations.peek()) >= getPriority(token)) {
                        output.add(operations.pop());
                    }
                    operations.push(token);
                    break;
                default:
                    output.add(token);
            }
        }

        while (!operations.isEmpty()) {
            output.add(operations.pop());
        }

        return output;
    }

    private int getPriority(String operator) {
        if ("+-".contains(operator)) {
            return 0;
        } else if ("()".contains(operator)) {
            return -1;
        } else {
            return 1;
        }
    }

    private static class TokenIter implements Iterator<String> {

        private final StringTokenizer tokenizer;

        public TokenIter(StringTokenizer tokenizer) {
            this.tokenizer = tokenizer;
        }

        @Override
        public boolean hasNext() {
            return tokenizer.hasMoreElements();
        }

        @Override
        public String next() {
            return tokenizer.nextToken();
        }
    }
}
