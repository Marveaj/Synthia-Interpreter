import java.io.FileReader;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;

public class SemanticAnalysis {
    public Parser parser;
    public ArrayList<VariableNode> declerations;
    public ArrayList<AssignmentNode> assigns;
    public ArrayList<BooleanExpressionNode> conditions;

    public SemanticAnalysis(FileReader file) throws IOException {

    }

    public void checkAssignments() {

        int a = 0;
        for (int i = 0; i < assigns.size(); i++) {

            BooleanExpressionNode type = assigns.get(i).getValue();
            a += 1;
            String idName = assigns.toString();
            InterpreterDataType idType = getIdentifierType(idName);

            // assign to int
            if (idType != null && (idType instanceof IntDataType)) {

                // float to int
                if (idType instanceof IntDataType)
                    System.out.println("interger datatype error");

                // boolean to int
                if (idType instanceof BoolDataType)
                    System.out.println("Bool Datatype error");

                // type(id) to int

            }

            // assign to float
            if (idType != null && (idType instanceof FloatDataType)) {

                // boolean to float
                if (idType instanceof BoolDataType)
                    System.out.println("Bool to float  error");

                // char to float
                if (idType instanceof CharDataType)
                    System.out.println("char to float error");

            }

            // assign to char
            if (idType != null && (idType instanceof CharDataType)) {

                if (idType instanceof IntDataType)
                    System.out.println("int to char error");

                // boolean to char
                if (idType instanceof BoolDataType)
                    System.out.println("Bool to char  error");

                // char to float
                if (idType instanceof FloatDataType)
                    System.out.println("float to char error");

            }

            // assign to boolean
            if (idType != null && (idType instanceof BoolDataType)) {

                if (idType instanceof IntDataType)
                    System.out.println("int to bool error");

                // boolean to char
                if (idType instanceof CharDataType)
                    System.out.println("char to  bool error");

                // char to float
                if (idType instanceof FloatDataType)
                    System.out.println("float to bool error");

            }
        }
    }

    private InterpreterDataType getIdentifierType(String name) {
        for (VariableNode dec : declerations) {
            InterpreterDataType id = dec.getType();
            if (id.name().equals(name))
                return dec.getType();
        }

        return null;
    }

}
