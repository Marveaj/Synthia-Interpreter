public abstract class InterpreterDataType {

    public float Float;
    public int Interger;
    public String str;
    public boolean bool;

    public boolean Bool;

    public abstract String ToString();

    public abstract void FromString(String input); // sets the value of the data type by parsing the string

    public Object name() {
        return "" + Interger;
    }

}
