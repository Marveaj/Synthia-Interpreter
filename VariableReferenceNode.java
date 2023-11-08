public class VariableReferenceNode {
    private String nameReference;

    public VariableReferenceNode(String nameReference) {
        this.nameReference = nameReference;
    }

    public String getNameReference() {
        return nameReference;
    }

    public void setNameReference(String nameReference) {
        this.nameReference = nameReference;
    }

    @Override
    public String toString() {
        return "VariableReferenceNode{" + "nameReference=" + nameReference + '}';
    }
}