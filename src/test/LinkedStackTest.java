import java.LinkedStack;

//class LinkedStackTest
class LinkedStackTest {
    //main method
    public static void main (String[] args) throws Exception {
        String infix = "a*b/(c-a)+d*e";
        //call the convertToPostfix method
        String postfix = convertToPostfix(infix);
        System.out.println ("Postfix expression: " + postfix);
    }
}
