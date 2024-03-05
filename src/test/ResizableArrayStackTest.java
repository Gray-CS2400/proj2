import main.ResizableArrayStack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResizableArrayStackTest {
    @Test
    public void methodTest(){
        ResizableArrayStack<String> stack = new ResizableArrayStack<>();
        stack.push("Hi");
        stack.push("Hola");
        stack.push("Bonjour");
        stack.push("Testing");

        //Testing, Bonjour, Hola, Hi
        assertEquals("Testing", stack.peek());

        //Pop test
        /**
         *
         */
        stack.pop();
        assertEquals("Bonjour", stack.peek());

        //Push test
        stack.push("Good");
        assertEquals("Good", stack.peek());

        //Clear + isEmpty test
        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void evaluateTest(){
        //Test 1:
        ResizableArrayStack<String> stack = new ResizableArrayStack<>();

        stack.push("+");
        stack.push("*");
        stack.push("3");
        stack.push("4");
        stack.push("2");

        //Postfix Stack: 243*+
        //2+4*3 = 14
        //Expected outcome: 14
        assertEquals(14, stack.evaluatePostfix(stack));

        //Test 2:
        stack = new ResizableArrayStack<>();

        stack.push("+");
        stack.push("/");
        stack.push("-");
        stack.push("4");
        stack.push("7");
        stack.push("3");
        stack.push("5");

        //Postfix Stack: 5374+/-
        //5+3/(7-4) = 6
        //Expected outcome: 6
        assertEquals(6, stack.evaluatePostfix(stack));

        //Test 3:
        stack = new ResizableArrayStack<>();

        stack.push("^");
        stack.push("/");
        stack.push("+");
        stack.push("*");
        stack.push("-");
        stack.push("4");
        stack.push("7");
        stack.push("2");
        stack.push("3");
        stack.push("9");
        stack.push("2");

        //Postfix Stack: 293274-*+/^
        //2^(9/(3+(2*(7-4)))) = 2
        //Expected outcome: 2
        assertEquals(2, stack.evaluatePostfix(stack));
    }
}