public class RealNode extends Node {
    private String value;

    // constructor
    public RealNode(String value) {
        this.value = value;
    }

    // read only accessor
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RealNode: " + value;
    }
}
