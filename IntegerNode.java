public class IntegerNode extends Node {
    private int value;

    // constructor
    public IntegerNode(int value) {
        this.value = value;
    }

    // read only accessor
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntegerNode: " + value;
    }
}
