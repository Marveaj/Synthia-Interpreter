public class BoolNode extends Node {
    private boolean value;

    // constructor
    public BoolNode(Boolean value) {
        this.value = value;
    }

    // read only accessor
    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BoolNode: " + value;
    }
}
