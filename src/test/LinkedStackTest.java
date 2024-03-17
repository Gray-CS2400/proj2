

public class LinkedStackTest {
    public static void main(String[] args) {
        LinkedStack<Character> stack = new LinkedStack<>();
        String infixExpression = "a*b/(c-a)+d*e";
        String expectedPostfix = "ab*ca-/de*+";

        String postfixExpression = stack.convertToPostfix(infixExpression);

        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Expected Postfix Expression: " + expectedPostfix);
        System.out.println("Postfix Expression: " + postfixExpression);

        if (postfixExpression.equals(expectedPostfix)) {
            System.out.println("Test Passed: Postfix expression matches the expected result.");
        } else {
            System.out.println("Test Failed: Postfix expression does not match the expected result.");
        }
    }
}
