package main;

public class Calculator {
/**Calculator tests the implementation of the convertToPostfix and evaluatePostfix methods*/
    public static void main(String[] args){
        /** testing convertToPostfix */
        LinkedStack<String> infixToPost = new LinkedStack<>();
        String inputEq = "a*b/(c-a)+d*e";
        System.out.println("Infix Expression: " + inputEq);
        String postfix = infixToPost.convertToPostfix(inputEq);
        /** expected outcome: ab*ca-/de*+ */
        System.out.println("Resulting Conversion: " + postfix);

        /** testing evaluatePostfix */
        ResizableArrayStack<String> postfixEval = new ResizableArrayStack<>();

        /**a=2, b=3, c=4, d=5, e=6 */
        /**pushing values from postfix into stack */
        for (int i = postfix.length() - 1; i >= 0; i--)
        {
            String change = String.valueOf(postfix.charAt(i));
            if(change.equals("a"))
            {
                change = "2";
            }
            else if (change.equals("b")) {
                change = "3";
            }
            else if (change.equals("c")) {
                change = "4";
            }
            else if (change.equals("d")) {
                change = "5";
            }
            else if (change.equals("e")) {
                change = "6";
            }
            postfixEval.push(change);
        }
        
        int evaluated = postfixEval.evaluatePostfix(postfixEval);
        /** expected outcome: 33 */
        System.out.println("Answer: " + evaluated);

        
    }
}
