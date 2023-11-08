public class IntNode extends Node {
    private String value;

    // constructor
    public IntNode(String value) {
        this.value = value;
    }

    // read only accessor
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntNode: " + value;
    }
}
