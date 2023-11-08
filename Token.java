public class Token {
    // contain instance of enum Machine and value string
    private Machine type;
    private String value;

    // a constructor to initialize a object
    public Token(Machine type, String value) {
        this.type = type;
        this.value = value;
    }

    // accessors for both instance variables
    public Machine getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    // overloaded toString to print like we want
    @Override
    public String toString() {
        // if a number than print the value in parentheses else print only enum
        if (type == Machine.Number) {
            return type + " (" + value + ")";
        } else {
            return type.toString();
        }
    }
}