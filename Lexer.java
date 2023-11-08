import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lexer {
    // a HashMap<String, Machine> of all the keywords
    private static final HashMap<String, Machine> keywords = new HashMap<String, Machine>();
    // add the keywords in the HashMap
    static {
        keywords.put("real", Machine.Real);
        keywords.put("integer", Machine.Integer);
        keywords.put("define", Machine.Define);
        keywords.put("begin", Machine.Begin);
        keywords.put("end", Machine.End);
        keywords.put("variables", Machine.Variables);
        keywords.put("constants", Machine.Constants);
        keywords.put("string", Machine.String);
        keywords.put("boolean", Machine.Boolean);
        keywords.put("'", Machine.CharContents);
        keywords.put("''", Machine.StringContents);
        keywords.put("char", Machine.Char);

    }

    /**
     * @param input : a line containing a number of tokens
     * @return : a list of tokens found in that string or line
     */
    public static List<Token> lex(String input) {
        // will contain all the token extracted from the line
        List<Token> result = new ArrayList<>();

        // if input is an empty line add EndOfLine and return
        if (input.isEmpty()) {
            result = new ArrayList<>();
            result.add(new Token(Machine.EndOfLine, "EndOfLine"));
            return result;
        }

        // parse all characters from line
        for (int i = 0; i < input.length();) {
            // get the character at index i
            char c = input.charAt(i);

            // if the char is a space or tab we ignore it
            if (c == ' ' || c == '\t') {
                i++;
                continue;
            }
            // if we receive a + then we add it to result
            if (c == '+') {
                result.add(new Token(Machine.Plus, "+"));
                i++;
                continue;
            }
            // if we receive a -
            if (c == '-') {
                // Now this - can be negative number if it has a number after it, and it can be
                // a sign if it has space after it
                if (i + 1 < input.length() && input.charAt(i + 1) >= '0' && input.charAt(i + 1) <= '9') {
                    int j = i + 1;
                    // if a negative number
                    while (j < input.length() && input.charAt(j) >= '0' && input.charAt(j) <= '9') {
                        j++;
                    }
                    // also see if it is a negative decimal numbers
                    if (j < input.length() && input.charAt(j) == '.') {
                        j++;
                        while (j < input.length() && input.charAt(j) >= '0' && input.charAt(j) <= '9') {
                            j++;
                        }
                    }
                    // finally add the extracted number as a number into the list
                    result.add(new Token(Machine.Number, input.substring(i, j)));
                    i = j;
                    continue;
                } else {
                    // if just a minus then add it
                    result.add(new Token(Machine.Minus, "-"));
                    i++;
                    continue;
                }
            }
            // if it is a * then add times
            if (c == '*') {
                result.add(new Token(Machine.Times, "*"));
                i++;
                continue;
            }
            // if it is a / then add divide
            if (c == '/') {
                result.add(new Token(Machine.Divide, "/"));
                i++;
                continue;
            }
            // if it is colon
            if (c == ':') {
                // if it is a assignment -> :=
                if (i + 1 < input.length() && input.charAt(i + 1) == '=') {
                    result.add(new Token(Machine.Assignment, ":="));
                    i += 2;
                    continue;
                } else {
                    // if it is just a colon
                    result.add(new Token(Machine.Colon, ":"));
                    i++;
                    continue;
                }
            }
            // if it is a semicolon
            if (c == ';') {
                result.add(new Token(Machine.SemiColon, ";"));
                i++;
                continue;
            }
            // if it is a comma
            if (c == ',') {
                result.add(new Token(Machine.Comma, ","));
                i++;
                continue;
            }
            // if it is a equal
            if (c == '=') {
                result.add(new Token(Machine.Equal, "="));
                i++;
                continue;
            }
            // if it is a left parenthesis
            if (c == '(') {
                // see if it is a comment
                // comment start with (*
                // comment end with *)
                // if a comment is received then ignore everything in (* *) and add comment to
                // result
                if (i + 1 < input.length() && input.charAt(i + 1) == '*') {
                    int j = i + 2;
                    while (j < input.length() && !(input.charAt(j) == '*' && input.charAt(j + 1) == ')')) {
                        j++;
                    }
                    result.add(new Token(Machine.Comment, input.substring(i, j + 2)));
                    i = j + 2;
                    continue;
                } else {
                    // if it is just a left parenthesis then add it
                    result.add(new Token(Machine.LeftParen, "("));
                    i++;
                    continue;
                }
            }
            // if it is a right parenthesis
            if (c == ')') {
                result.add(new Token(Machine.RightParen, ")"));
                i++;
                continue;
            }
            // see if a number is received
            if (c >= '0' && c <= '9') {
                int j = i;
                // if just a number
                while (j < input.length() && input.charAt(j) >= '0' && input.charAt(j) <= '9') {
                    j++;
                }
                // if it is a decimal
                if (j < input.length() && input.charAt(j) == '.') {
                    j++;
                    while (j < input.length() && input.charAt(j) >= '0' && input.charAt(j) <= '9') {
                        j++;
                    }
                }
                // add final result to list
                result.add(new Token(Machine.Number, input.substring(i, j)));
                i = j;
                continue;
            }
            // a decimal can also occurs as .5 or .23
            if (c == '.') {
                int j = i + 1;
                while (j < input.length() && input.charAt(j) >= '0' && input.charAt(j) <= '9') {
                    j++;
                }
                result.add(new Token(Machine.Number, input.substring(i, j)));
                i = j;
                continue;
            }
            // see if a word is received
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                int j = i + 1;
                while (j < input.length() && (input.charAt(j) >= 'a' && input.charAt(j) <= 'z'
                        || input.charAt(j) >= 'A' && input.charAt(j) <= 'Z')) {
                    j++;
                }
                // if the word in hashmap then it is keyword so make a token using Machine type
                if (keywords.containsKey(input.substring(i, j))) {
                    result.add(new Token(keywords.get(input.substring(i, j)), input.substring(i, j)));
                } else {
                    // else it is an identifier
                    result.add(new Token(Machine.Identifier, input.substring(i, j)));
                }
                i = j;
                continue;
            }

            // all others are not recognized and are considered invalid
            throw new RuntimeException("Invalid input: " + input);
        }

        // there should be no repeated numbers or operators in a row
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i).getType() == Machine.Number && result.get(i + 1).getType() == Machine.Number) {
                throw new RuntimeException("Invalid input: " + input);
            }
            // any two Plus, minus, Times, Divide are side by side then exception
            if ((result.get(i).getType() == Machine.Plus || result.get(i).getType() == Machine.Minus
                    || result.get(i).getType() == Machine.Times || result.get(i).getType() == Machine.Divide)
                    && (result.get(i + 1).getType() == Machine.Plus || result.get(i + 1).getType() == Machine.Minus
                            || result.get(i + 1).getType() == Machine.Times
                            || result.get(i + 1).getType() == Machine.Divide)) {
                throw new RuntimeException("Invalid input: " + input);
            }
        }

        // now add the EOL at the end of each line
        result.add(new Token(Machine.EndOfLine, "EndOfLine"));

        return result;
    }
}