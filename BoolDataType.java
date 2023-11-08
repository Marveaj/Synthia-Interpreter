
public class BoolDataType extends InterpreterDataType {

    private boolean bool;

    @Override
    public String ToString() {

        return "" + bool;
    }

    @Override
    public void FromString(String input) {

    }

}
