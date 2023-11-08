public class CharNode extends Node {
    private char value;

    // constructor
    public CharNode(char value) {
        this.value = value;
    }

    // read only accessor
    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CharNode: " + value;
    }
}
