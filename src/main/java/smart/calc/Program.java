package smart.calc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Program {

    private final Scanner scanner;

    private final OutputStream out;

    public Program(InputStream in, OutputStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    private boolean enabled;

    public void start() {
        if (enabled) {
            throw new IllegalStateException("Program already started");
        }

        enabled = true;
        Calculator calculator = new Calculator();
        while (enabled && scanner.hasNextLine()) {
            try {
                String line = scanner.nextLine();
                Parser parser = new Parser(line);
                List<String> statements = parser.parse();
                BigDecimal result = calculator.calculate(statements);

                out.write(result.toString().getBytes());
                out.write("\n".getBytes());
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        enabled = false;
        scanner.close();
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
