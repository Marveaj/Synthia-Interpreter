
public class BooleanExpressionNode extends Node {

    private Boolean value;

    public BooleanExpressionNode(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String toString(String tab) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(tab);
        buffer.append("BooleanExpressionNode(\n");
        buffer.append(" " + tab + value);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BooleanExpressionNode]");
        return buffer.toString();
    }

    @Override
    public String toString() {
        return "" + value;
    }
    
}