import java.util.ArrayList;
import java.util.List;

public class Parser {
    // collection of tokens
    private List<Token> tokens;

    // constructor for the parser
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    // parse the tokens and return a Node
    public Node parse() {
        // remove end of line
        matchAndRemove(Machine.EndOfLine);
        if (tokens.size() == 0) {
            throw new RuntimeException("No tokens\n");
        }
        // parse the expression
        return expression();
    }

    // matchAndRemove will remove the token from front of the list if it matches and
    // return true else return false
    public boolean matchAndRemove(Machine type) {
        if (tokens.get(0).getType() == type) {
            tokens.remove(0);
            return true;
        } else {
            return false;
        }
    }

    // expression = term { ( "+" | "-" ) term }
    public Node expression() {
        // parse the first term
        Node node = term();
        // while the next token is a plus or minus
        while (tokens.get(0).getType() == Machine.Plus || tokens.get(0).getType() == Machine.Minus) {
            // use matchAndRemove
            if (matchAndRemove(Machine.Plus)) {
                // parse the next term
                Node right = term();
                // create a new MathOpNode
                node = new MathOpNode(Machine.Plus, node, right);
            } else if (matchAndRemove(Machine.Minus)) {
                // parse the next term
                Node right = term();
                // create a new MathOpNode
                node = new MathOpNode(Machine.Minus, node, right);
            }
        }
        // return the node
        return node;
    }

    // term = factor { ( "*" | "/" ) factor }
    public Node term() {
        // parse the first factor
        Node node = factor();
        // while the next token is a multiply or divide
        while (tokens.get(0).getType() == Machine.Times || tokens.get(0).getType() == Machine.Divide) {
            // use matchAndRemove
            if (matchAndRemove(Machine.Times)) {
                // parse the next factor
                Node right = factor();
                // create a new MathOpNode
                node = new MathOpNode(Machine.Times, node, right);
            } else if (matchAndRemove(Machine.Divide)) {
                // parse the next factor
                Node right = factor();
                // create a new MathOpNode
                node = new MathOpNode(Machine.Divide, node, right);
            }
        }
        return node;
    }

    // factor = {-} number | "(" expression ")"
    public Node factor() {
        // if the token is a minus then remove it and return a negative node
        if (matchAndRemove(Machine.Minus)) {
            // if the next token is a number
            if (tokens.get(0).getType() == Machine.Number) {
                // if the number is a float then return a float node
                if (tokens.get(0).getValue().contains(".")) {
                    return new FloatNode(-Float.parseFloat(tokens.remove(0).getValue()));
                }
                // else return an integer node
                else {
                    return new IntegerNode(-Integer.parseInt(tokens.remove(0).getValue()));
                }
            } else {
                // return a exception
                throw new RuntimeException("Expected a number after minus");
            }
        } else if (tokens.get(0).getType() == Machine.Number) {
            if (tokens.get(0).getValue().contains(".")) {
                return new FloatNode(Float.parseFloat(tokens.remove(0).getValue()));
            } else {
                return new IntegerNode(Integer.parseInt(tokens.remove(0).getValue()));
            }
        } else if (tokens.get(0).getType() == Machine.Boolean) {

            if (tokens.get(0).getValue().contains(".")) {
                if (tokens.remove(0).getValue() == "")
                    return new BoolNode(false);
                else
                    return new BoolNode(true);

            }

        } else if (tokens.get(0).getType() == Machine.String) {

            if (tokens.get(0).getValue().contains(".")) {
                return new StringNode(tokens.remove(0).getValue());

            }

        } else if (tokens.get(0).getType() == Machine.Char) {

            if (tokens.get(0).getValue().contains(".")) {
                return new CharNode(tokens.remove(0).getValue().charAt(0));

            }

        } else {
            matchAndRemove(Machine.LeftParen);
            Node node = expression();
            matchAndRemove(Machine.RightParen);
            return node;
        }
        return null;
    }

    public Node functionDefinition() {
        // if the token is a define
        if (matchAndRemove(Machine.Define)) {
            // get the name
            String name = tokens.remove(0).getValue();
            // look for left parenthesis
            matchAndRemove(Machine.LeftParen);
            // get the parameters
            List<Node> parameters = parameters();
            // get the constants
            Node constants = constants();
            // get the variables
            List<Node> variables = variables();
            // get the body
            Node body = body();
            // return a function node
            return new FunctionNode(name, variables, parameters);
        } else {
            // return a exception
            throw new RuntimeException("Expected a define");
        }
    }

    // parameters()
    public List<Node> parameters() {
        // create a list of Nodes
        List<Node> parameters = new ArrayList<>();
        // while the next token is not a right paren
        while (tokens.get(0).getType() != Machine.RightParen) {
            // format : value : real | int
            // get the value
            String value = tokens.remove(0).getValue();
            // get the colon
            matchAndRemove(Machine.Colon);
            // get the type
            String type = tokens.remove(0).getValue();

            // if the type is real then add a new RealNode
            if (type.equals("real")) {
                parameters.add(new RealNode(value));
            }
            // else if type is int add a new IntNode
            else if (type.equals("int")) {
                parameters.add(new IntNode(value));
            }
            // else throw a exception
            else {
                throw new RuntimeException("Expected a real or int");
            }
            matchAndRemove(Machine.SemiColon);
        }
        // return the list of parameters
        return parameters;
    }

    // constants()
    public Node constants() {
        // look for constants if find call processConstants
        if (matchAndRemove(Machine.Constants)) {
            return processConstants();
        } else {
            // return a exception
            throw new RuntimeException("Expected a constant");
        }
    }

    // processConstants()
    public Node processConstants() {
        // while the next token is not a variables
        while (tokens.get(0).getType() != Machine.Variables) {
            // get the identifier
            String identifier = tokens.remove(0).getValue();
            // get the equals
            matchAndRemove(Machine.Equal);
            // get the number
            String number = tokens.remove(0).getValue();
            // get the end of line
            matchAndRemove(Machine.EndOfLine);

            // make a variableNode for each if a "." in number than RealNode else IntNode
            if (number.contains(".")) {
                return new VariableNode(identifier, true, dataType.real, new RealNode(number));
            } else {
                return new VariableNode(identifier, true, dataType.integer, new IntNode(number));
            }
        }
        return null;
    }

    // variables()
    public List<Node> variables() {
        // create a list of Nodes
        List<Node> variables = new ArrayList<>();
        // while the next token is not a begin
        while (tokens.get(0).getType() != Machine.Begin) {
            while (tokens.get(0).getType() != Machine.Colon) {
                // get the identifier
                String identifier = tokens.remove(0).getValue();
                // get the comma
                matchAndRemove(Machine.Comma);
                // make a variableNode for each
                variables.add(new VariableNode(identifier, false, null, null));
            }
            // get the colon
            matchAndRemove(Machine.Colon);
            // get the type
            String type = tokens.remove(0).getValue();
            // get the end of line
            matchAndRemove(Machine.EndOfLine);

            // if the type is real set all nodes type to real
            if (type.equals("real")) {
                for (Node node : variables) {
                    ((VariableNode) node).setType(dataType.real);
                }
            }
            // else if type is int set all nodes type to int
            else if (type.equals("int")) {
                for (Node node : variables) {
                    ((VariableNode) node).setType(dataType.integer);
                }
            }

            // if type is real set all nodes astNode to new RealNode("0")
            if (type.equals("real")) {
                for (Node node : variables) {
                    ((VariableNode) node).setAstNode(new RealNode("0"));
                }
            }
            // else if type is int set all nodes astNode to new IntNode("0")
            else if (type.equals("int")) {
                for (Node node : variables) {
                    ((VariableNode) node).setAstNode(new IntNode("0"));
                }
            }
            // else throw a exception
            else {
                throw new RuntimeException("Expected a real or int");
            }
        }
        // return the list of variables
        return variables;
    }

    public Node body() {
        // look for begin if found start building FunctionNode
        if (matchAndRemove(Machine.Begin)) {
            // get the end of line
            matchAndRemove(Machine.EndOfLine);
            // get the end
            matchAndRemove(Machine.End);
            // get the end of line
            matchAndRemove(Machine.EndOfLine);
            return null;
        } else {
            // return a exception
            throw new RuntimeException("Expected a body");
        }
    }
}