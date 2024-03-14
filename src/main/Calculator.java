package main;

public class Calculator {
/**Calculator tests the implementation of the convertToPostFix and evaluatePostFix methods */
    public static void main(String[] args){
        StackInterface<String> infixToPost = new LinkedStack<>();
        //currently not sure how im supposed to add the stuff to the stack
        //input value: a*b/(c-a)+d*e
        String inputEq = "a*b/(c-a)+d*e";
        for(int i = 0; i < inputEq.length();i++)
        {
            infixToPost.push(inputEq.charAt(i));
        }
        StackInterface<String> result = new LinkedStack<>();
        result.convertToPostFix(infixToPost);
        //probably need to do the for loop thing like last time or something maybe push all the values off the stack in a loop
        System.out.print(result);


        //expected outcome: ab*ca-/de*+

        StackInterface<String> postfixEval = new ResizableArrayStack<>();
        StackInterface<String> carrier = new ResizableArrayStack<>();
        //a=2, b=3, c=4, d=5, e=6
        //also might need to do a loop to change the values of the string to put into resizablearraystack
        //to make the code better could probably take the value from infix stack and do a loop, could also try to take user input bc not sure what to do
        postfixEval.push("2");
        postfixEval.push("3");
        postfixEval.push("*");
        postfixEval.push("4");
        postfixEval.push("2");
        postfixEval.push("-");
        postfixEval.push("/");
        postfixEval.push("5");
        postfixEval.push("6");
        postfixEval.push("*");
        postfixEval.push("+");

        int evaluated = carrier.evaluatePostFix(postfixEval);
        System.out.println(evaluated);



    }
}
