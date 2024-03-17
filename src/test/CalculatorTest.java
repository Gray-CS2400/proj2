import main.ResizableArrayStack;
import main.LinkedStack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    /**Calculator testing uses various infix expressions from the textbook*/
    @Test
    void caseTesting(){
        
        /**trial 1 - infix expression 1: a-b+c */
        LinkedStack<String> infixToPost = new LinkedStack<>();
        String postfix = infixToPost.convertToPostfix("a-b+c");
        
        /** expected outcome: ab-c+ */
        assertEquals("ab-c+", postfix);

        ResizableArrayStack<String> evaluator = new ResizableArrayStack<>();
        /** a=1, b=2, c=3 */
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
            }
            evaluator.push(change);
        }
        /** expected outcome: 2 */
        int ans = evaluator.evaluatePostfix(evaluator);
        assertEquals(2, ans);


        /**trial 2 - infix expression 2: a^b^c */
        evaluator.clear();

        postfix = infixToPost.convertToPostfix("a^b^c");
        
        /** expected outcome: abc^^ */
        assertEquals("abc^^", postfix);

        /** a=1, b=2, c=3 */
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
            }
            evaluator.push(change);
        }
        /** expected outcome: 1*/
        ans = evaluator.evaluatePostfix(evaluator);
        assertEquals(1, ans);


        /**trial 3 - infix expression 3: a/b*(c+(d−e))*/
        evaluator.clear();

        postfix = infixToPost.convertToPostfix("a/b*(c+(d-e))");
        
        /** expected outcome: ab/cde-+**/
        assertEquals("ab/cde-+*", postfix);

        /** a=2, b=3, c=4, d=5, e=6 */
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
            evaluator.push(change);
        }
        /** expected outcome: 0*/
        ans = evaluator.evaluatePostfix(evaluator);
        assertEquals(0, ans);

        /**trial 4 - infix expression 4: a-(b/(c-d)*e+f)^g*/
        evaluator.clear();

        postfix = infixToPost.convertToPostfix("a-(b/(c-d)*e+f)^g");
        
        /** expected outcome: ab/cde-+**/
        assertEquals("abcd-/e*f+g^-", postfix);

        /** a=2, b=3, c=4, d=5, e=6, f=7, g=1 */
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
            else if (change.equals("f")) {
                change = "7";
            }
            else if (change.equals("g")) {
                change = "1";
            }
            evaluator.push(change);
        }
        /** expected outcome: 13*/
        ans = evaluator.evaluatePostfix(evaluator);
        assertEquals(13, ans);
    }
}

