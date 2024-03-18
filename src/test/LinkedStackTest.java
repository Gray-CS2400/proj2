import main.LinkedStack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {
    @Test
    public void methodTest() {
        LinkedStack<Character> stack = new LinkedStack<>();
        String infixExpression = "a*b/(c-a)+d*e";
        String expectedPostfix = "ab*ca-/de*+";

        String postfixExpression = stack.convertToPostfix(infixExpression);

        assertEquals(expectedPostfix, postfixExpression);
    }

    @Test
    public void evaluationTest() {
        //Test 1
        LinkedStack<Character> stack = new LinkedStack<>();

        String testExpression = "a+b";
        String expectedtestPostfix = "ab+";

        String testpostfixExpression = stack.convertToPostfix(testExpression);

        assertEquals(expectedtestPostfix, testpostfixExpression);

        //Test 2
        LinkedStack<Character> stacktwo = new LinkedStack<>();

        String secondExpression = "a/b+(c-d)";
        String expectedsecondPostfix = "ab/cd-+";

        String secondpostfixExpression = stacktwo.convertToPostfix(secondExpression);

        assertEquals(expectedsecondPostfix, secondpostfixExpression);

        //Test 3
        LinkedStack<Character> stackthree = new LinkedStack<>();

        String thirdExpression = "e-b*c^a+d";
        String expectedthirdPostfix = "ebca^*-d+";

        String thirdpostfixExpression = stackthree.convertToPostfix(thirdExpression);

        assertEquals(expectedthirdPostfix, thirdpostfixExpression);

    }
}
