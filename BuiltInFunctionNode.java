import java.lang.Math; 

public abstract class BuiltInFunctionNode<T> {
    private T t;

    

    public BuiltInFunctionNode(T... var)
    {
        if(var.length==0)
        {
            System.out.println("variable is not variadic");
        }


    }


    abstract public void execute(InterpreterDataType[] objects);
    
    
}
