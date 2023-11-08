import java.util.List;

public class FunctionNode extends Node {
    private String name;
    // local variables list of Node
    private List<Node> localVariables;
    // parameters list of Node
    private List<Node> parameters;

    // constructor
    public FunctionNode(String name, List<Node> localVariables, List<Node> parameters) {
        this.name = name;
        this.localVariables = localVariables;
        this.parameters = parameters;
    }

    // getters
    public String getName() {
        return name;
    }

    public List<Node> getLocalVariables() {
        return localVariables;
    }

    public List<Node> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        // print the name, local variables and parameters
        String local = "Local Variables: ";
        // append all local variables in this string
        for (Node node : localVariables) {
            local += node + " ";
        }

        String param = "Parameters: ";
        // append all parameters in this string
        for (Node node : parameters) {
            param += node + " ";
        }

        return "FunctionNode: " + name + " " + local + " " + param;
    }
}