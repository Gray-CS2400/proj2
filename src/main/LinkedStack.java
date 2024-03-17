package main;
import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
    private Node topNode; // Reference to the first node in the chain

    // Private inner class for Node
    private class Node {
        private T data; // Entry in the stack
        private Node next; // Link to the next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }

    public LinkedStack() {
        topNode = null;
    }

    @Override
    public void push(T newEntry) {
        topNode = new Node(newEntry, topNode);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T top = topNode.data;
        topNode = topNode.next;
        return top;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topNode.data;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
    }

    // Convert infix expression to postfix
    public String convertToPostfix(String infix) {
        LinkedStack<Character> operatorStack = new LinkedStack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char nextCharacter = infix.charAt(i);
            switch (nextCharacter) {
                case ' ': // Ignore whitespace
                    break;
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!operatorStack.isEmpty() && precedence(nextCharacter) <= precedence(operatorStack.peek())) {
                        postfix.append(operatorStack.pop());
                    }
                    operatorStack.push(nextCharacter);
                    break;
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                case ')':
                    char topOperator = operatorStack.pop();
                    while (topOperator != '(') {
                        postfix.append(topOperator);
                        topOperator = operatorStack.pop();
                    }
                    break;
                default: // Operand
                    postfix.append(nextCharacter);
                    break;
            }
        }

        // Append remaining operators from the stack
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }

    // Method to determine precedence of operators
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1; // Lowest precedence
        }
    }

    public static void main(String[] args) {
        LinkedStack<Character> stack = new LinkedStack<>();
        String infixExpression = "a + b * (c ^ d - e) / f";

        String postfixExpression = stack.convertToPostfix(infixExpression);
        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);
    }
}
