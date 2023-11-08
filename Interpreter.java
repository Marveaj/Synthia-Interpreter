import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Interpreter {

    private static FloatDataType flot;

    private static BoolDataType bool;

    private static IntDataType Int;

    private static CharDataType Char;

    private static StringDataType string;

    static HashMap<String, Machine> keywords = new HashMap<String, Machine>();

    static HashMap<String, CallableNode> nodes = new HashMap<String, CallableNode>();

    public static void InterpretFunction(FunctionNode Fnode, InterpreterDataType[] inter_array) {

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

    public boolean ResolveBoolean(String str) {
        if (string.str == str)
            return true;
        else
            return false;

    }

    public String ResoveSting(String str) {
        return string.str + str;

    }

    public char ResolveChar(String str) {
        return string.str.charAt(0);
    }

    public int ResolveInteger(String str) {

        int i = Integer.parseInt(str);
        return i;
    }

    public Float ResolveFloat(String str) {

        float f = Float.parseFloat("23.6");
        return f;

    }

    public int EvaluateBooleanExpression(StringBuffer s) {
        int n = s.length();

        for (int i = 0; i < n; i += 2) {

            if (i + 1 < n && i + 2 < n) {
                if (s.charAt(i + 1) == 'A') {
                    if (s.charAt(i + 2) == '0' ||
                            s.charAt(i) == 0)
                        s.setCharAt(i + 2, '0');
                    else
                        s.setCharAt(i + 2, '1');
                }

                else if ((i + 1) < n &&
                        s.charAt(i + 1) == 'B') {
                    if (s.charAt(i + 2) == '1' ||
                            s.charAt(i) == '1')
                        s.setCharAt(i + 2, '1');
                    else
                        s.setCharAt(i + 2, '0');
                }

                else {
                    if (s.charAt(i + 2) == s.charAt(i))
                        s.setCharAt(i + 2, '0');
                    else
                        s.setCharAt(i + 2, '1');
                }
            }
        }
        return s.charAt(n - 1) - '0';
    }

    public void InterpretBlock(StatmentNode[] statements, HashMap<String, VariableNode> var) {
        // it will process end and begin
        for (int i = 0; i < statements.length; i++) {
            if (statements[i].toString() == "fucntiuon") {

            }
        }

    }

    // method resolve takes in Node and returns a float
    public float Resolve(Node node) {
        // for FloatNode return the value
        if (node instanceof FloatNode) {
            return ((FloatNode) node).getValue();
        }
        // for IntegerNode return the value cast as float
        else if (node instanceof IntegerNode) {
            return (float) ((IntegerNode) node).getValue();
        }
        // for MathOpNode call Resolve() on left and right sides
        else if (node instanceof MathOpNode) {
            // get the left and right nodes
            Node left = ((MathOpNode) node).getLeft();
            Node right = ((MathOpNode) node).getRight();
            // get the operator
            Machine operator = ((MathOpNode) node).getOp();
            // if the operator is plus then return the sum of the left and right
            if (operator == Machine.Plus) {
                return Resolve(left) + Resolve(right);
            }
            // if the operator is minus then return the difference of the left and right
            else if (operator == Machine.Minus) {
                return Resolve(left) - Resolve(right);
            }
            // if the operator is times then return the product of the left and right
            else if (operator == Machine.Times) {
                return Resolve(left) * Resolve(right);
            }
            // if the operator is divide then return the quotient of the left and right
            else if (operator == Machine.Divide) {
                return Resolve(left) / Resolve(right);
            }
        }
        // return 0 if none of the above
        return 0;
    }
}
