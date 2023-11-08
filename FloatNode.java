public class FloatNode extends Node {
    private float value;

    // constructor
    public FloatNode(float value) {
        this.value = value;
    }

    // read only accessor
    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "FloatNode: " + value;
    }
}
