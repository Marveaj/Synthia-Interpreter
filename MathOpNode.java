public class MathOpNode extends Node {
    // read only attributes
    private Machine op;
    private Node left;
    private Node right;

    // constructor
    public MathOpNode(Machine op, Node left, Node right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    // accessors for read only attributes
    public Machine getOp() {
        return op;
    }
    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "MathOpNode: " + "(" + left.toString() + ", " + op.toString() + ", " + right.toString() + ")";
    }
}
