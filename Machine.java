public enum Machine {
    Number,
    Plus,
    Minus,
    Times,
    Divide,
    EndOfLine,
    LeftParen,
    RightParen,
    Identifier,
    Define,
    String,
    Boolean,
    Integer,
    Char,
    CharContents,
    StringContents,
    Real,
    Begin,
    End,
    SemiColon,
    Colon,
    Equal,
    Comma,
    Variables,
    Constants,
    Comment,
    Assignment;

    // to make sure the output is all in uppercase
    @Override
    public String toString() {
        if (this == EndOfLine) {
            return name();
        }
        return name().toUpperCase();
    }
}