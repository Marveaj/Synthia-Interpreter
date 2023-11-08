import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Shank {
    public static void main(String[] args) throws IOException {
        // check if exception are not exactly one print exception and exit
        if (args.length != 1) {
            System.out.println("Error: Incorrect number of arguments");
            System.exit(1);
        }

        // consider the argument as filename
        String filename = args[0];
        // read all lines using Files.readAllLines
        List<String> lines = Files.readAllLines(Path.of(filename));

        // instantiate lexer class
        Lexer lexer = new Lexer();
        // instantiate parser class
        Parser parser;
        // instantiate interpreter class
        Interpreter interpreter = new Interpreter();

        // parse all the lines
        for (String line : lines) {
            // System.out.println(line.length());
            // use lex method and catch exception thrown by lex method
            try {
                ArrayList<Token> tokens = (ArrayList<Token>) Lexer.lex(line);
                // print each token
                System.out.print("Tokens: ");
                for (Token token : tokens) {
                    System.out.print(token + " ");
                }
                System.out.println();
                System.out.println();

                // initialize the parser with tokens we got form each line
                // parser = new Parser(tokens);
                // parse them
                // Node node = parser.parse();

                // print for debugging and checking
                // System.out.println("Parser: " + node);

                // call to Resolve() to check math
                // float result = interpreter.Resolve(node);
                // System.out.println("Result: " + result + "\n");
                // Node node = parser.functionDefinition();
                // System.out.println(node);

            } catch (Exception e) {
                System.out.println("Exception: " + e + "\n");
            }
        }
    }
}
