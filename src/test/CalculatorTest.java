import main.ResizableArrayStack;
import main.LinkedStack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void caseTesting(){
        //infix expression 1: a-b+c
        
        LinkedStack<String> infixToPost = new LinkedStack<>();
        String postfix = infixToPost.convertToPostfix("a-b+c");
        //expected outcome: ab-c+
        assertEquals("ab-c+", postfix);

        ResizableArrayStack<String> evaluator = new ResizableArrayStack<>();
        //a=1, b=2, c=3
        for (int i = postfix.length() - 1; i >= 0; i--)
        {
            String change = String.valueOf(postfix.charAt(i));
            if(change.equals("a"))
            {
                change = "1";
            }
            else if (change.equals("b")) {
                change = "2";
            }
            else if (change.equals("c")) {
                change = "3";
            evaluator.push(change);
        }
        //expected outcome: 2
        int ans = evaluator.evaluatePostfix(evaluator);
        assertEquals(2, ans);
    }
}

}
