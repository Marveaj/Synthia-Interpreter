import java.beans.Expression;
import java.lang.annotation.Target;

public class AssignmentNode extends StatmentNode {
    private VariableReferenceNode variableReferenceNode;
    private Node node;
    private BooleanExpressionNode value;

    public AssignmentNode(VariableReferenceNode variableReferenceNode, Node node) {
        super();
        this.variableReferenceNode = variableReferenceNode;
        this.node = node;
    }

    public VariableReferenceNode getVariableReferenceNode() {
        return variableReferenceNode;
    }

    public void setVariableReferenceNode(VariableReferenceNode variableReferenceNode) {
        this.variableReferenceNode = variableReferenceNode;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public BooleanExpressionNode getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AssignmentNode{" + "variableReferenceNode=" + variableReferenceNode + ", node=" + node + '}';
    }
}