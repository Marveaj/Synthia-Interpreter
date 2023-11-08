import java.util.List;

abstract public class CallableNode extends Node {

    private String name;
    // local variables list of Node
    private List<Node> localVariables;
    // parameters list of Node
    private List<Node> parameters;

    public CallableNode(String name, List<Node> localVariables, List<Node> parameters) {
        this.name = name;
        this.localVariables = localVariables;
        this.parameters = parameters;

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

    public void functionName() {

    }

}
