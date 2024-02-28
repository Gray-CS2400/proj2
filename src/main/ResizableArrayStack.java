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
    public ResizableArrayStack(int initialCapacity){
        integrityOK = false;
        checkCapacity(initialCapacity);

        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    }

    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("Capacity exceeded");
    }

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

    public T peek(){
        checkIntegrity();
        if(isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }

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

    // algorithm evaluatePostfix
    // has many errors, this is not a final solution
    public int evaluatePostfix(StackInterface<String> postfix){
        ResizableArrayStack<Integer> valueStack = new ResizableArrayStack<>();
        while(!postfix.isEmpty()){
            //nextCharacter = next non-blank character of postfix
            String nextCharacter = postfix.pop();
            valueStack.push(parseInt(nextCharacter));

            int operandOne;
            int operandTwo;
            int result;

            switch(nextCharacter){
//                case variable:
//                    valueStack.push(parseInt(nextCharacter));
//                    break;
                case "+":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    result = operandOne + operandTwo;
                    valueStack.push(result);
                    break;
                case "-":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    result = operandOne - operandTwo;
                    valueStack.push(result);
                    break;
                case "*":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    result = operandOne * operandTwo;
                    valueStack.push(result);
                    break;
                case "/":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    result = operandOne / operandTwo;
                    valueStack.push(result);
                    break;
                case "^":
                    operandOne = valueStack.pop();
                    operandTwo = valueStack.pop();
                    result = (int)(Math.pow(operandOne, operandTwo));
                    valueStack.push(result);
                    break;
                default:
                    break;
            }
        }
        return valueStack.pop();
    }
}