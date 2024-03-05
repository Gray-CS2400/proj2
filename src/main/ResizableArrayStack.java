package main;

import java.util.Arrays;
import java.util.EmptyStackException;

import static java.lang.Integer.parseInt;

public class ResizableArrayStack<T> implements StackInterface<T> {
    private T[] stack;
    private int topIndex;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    // implementations of StackInterface methods
    // includes ensureCapacity, checkIntegrity and checkCapacity

    public ResizableArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * @param initialCapacity The initial capacity for the stack
     */
    public ResizableArrayStack(int initialCapacity){
        integrityOK = false;
        checkCapacity(initialCapacity);

        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    }

    /**
     * @param capacity The capacity to be checked
     */
    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("Capacity exceeded");
    }

    /**
     * @param newEntry  An object to be added to the stack.
     */
    public void push (T newEntry){
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    private void checkIntegrity(){
        if(!integrityOK){
            throw new IllegalStateException("Integrity is not ok");
        }
    }

    private void ensureCapacity(){
        if(topIndex >= stack.length - 1){
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }

    /**
     * @return The object that is removed from the stack
     */
    public T pop(){
        checkIntegrity();
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else{
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }

    /**
     * @return The topmost element in the stack
     */
    public T peek(){
        checkIntegrity();
        if(isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }

    /**
     * @return If the stack is empty or not
     */
    public boolean isEmpty(){
        return topIndex < 0;
    }

    public void clear(){
        checkIntegrity();
        while(topIndex > -1){
            stack[topIndex] = null;
            topIndex--;
        }
    }

    //helper method for evaluatePostfix

    /**
     * @param a The string to be checked
     * @return If the string is a number or not
     */
    public boolean checkNum(String a){
        try{
            Integer.parseInt(a);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    // algorithm evaluatePostfix

    /**
     * @param postfix The postfix notation of a stack
     * @return The evaluated integer of the postfix equation
     */
    public int evaluatePostfix(StackInterface<String> postfix){
        ResizableArrayStack<Integer> valueStack = new ResizableArrayStack<>();
        //continuously loops until the stack is empty
        while(!postfix.isEmpty()){
            //nextCharacter is pop() of postfix, loops if nextCharacter is a blank character
            String nextCharacter = postfix.pop();
            while(nextCharacter.equals(" ")){
                nextCharacter = postfix.pop();
            }
            //checks if nextCharacter is an integer
            if(checkNum(nextCharacter)) {
                valueStack.push(parseInt(nextCharacter));
            }

            int operandOne;
            int operandTwo;

            //switch case for the operations
            switch(nextCharacter){
                case "+":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    valueStack.push(operandTwo + operandOne);
                    break;
                case "-":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    valueStack.push(operandTwo - operandOne);
                    break;
                case "*":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    valueStack.push(operandTwo * operandOne);
                    break;
                case "/":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    valueStack.push(operandTwo / operandOne);
                    break;
                case "^":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    valueStack.push((int)(Math.pow(operandTwo, operandOne)));
                    break;
                default:
                    break;
            }
        }
        return valueStack.pop();
    }
}