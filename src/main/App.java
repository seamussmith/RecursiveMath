package main;

import java.util.*;

public class App
{
    public static void main(String[] args) 
    {
        var in = new Scanner(System.in);
        System.out.println(evalExpression(in.nextLine()));
    }
    static double evalExpression(String expression)
    {
        // Put each token in the string into a queue for easier parsing
        var split = Arrays.asList(expression.split(" "));
        Queue<String> newStack = new LinkedList<String>();
        newStack.addAll(split.stream().filter(n -> n.length() != 0).toList());

        return processExpression(newStack, 0);
    }
    static double processExpression(Queue<String> tokens, int n)
    {
        // If the next token is a number, just return it
        if (tokens.peek().matches("^\\-?[1-9]\\d*(\\.\\d+)?$"))
            return Double.parseDouble(tokens.poll());
        
        // Data needed to calculate expression
        var op = tokens.poll();
        // NOTE: Will raise a NullPointerException if the tokens queue is empty
        var num1 = processExpression(tokens, n + 1);
        var num2 = processExpression(tokens, n + 2);

        // And do the arithmetic
        if (op.equals("+"))
            return num1 + num2;
        else if (op.equals("-"))
            return num1 - num2;
        else if (op.equals("*"))
            return num1 * num2;
        else if (op.equals("/"))
            return num1 / num2;
        
        return 0;
    }
}
