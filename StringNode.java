public class StringNode extends Node {
    private String value;

    // constructor
    public StringNode(String value) {
        this.value = value;
    }

    // read only accessor
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "StringNode: " + value;
    }
}
