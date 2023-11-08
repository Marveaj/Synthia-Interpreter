import java.lang.ProcessBuilder.Redirect.Type;

enum dataType {
    integer, real, string, Boolean, Float;
}

public class VariableNode extends Node {
    private String name;
    private boolean isConstant;
    private dataType type;
    private InterpreterDataType interType;
    private Node astNode;

    // constructor
    public VariableNode(String name, boolean isConstant, dataType type, Node astNode) {
        this.name = name;
        this.isConstant = isConstant;
        this.type = type;
        this.astNode = astNode;
    }

    // read only accessor
    public String getName() {
        return name;
    }

    // read only accessor
    public boolean getIsConstant() {
        return isConstant;
    }

    // read only accessor
    public InterpreterDataType getType() {
        return interType;
    }

    // setter for type
    public void setType(dataType type) {
        this.type = type;
    }

    // setter for astNode
    public void setAstNode(Node astNode) {
        this.astNode = astNode;
    }

    @Override
    public String toString() {
        return "VariableNode [name=" + name + ", isConstant=" + isConstant + ", type=" + type + ", astNode=" + astNode
                + "]";
    }
}
