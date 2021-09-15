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

        return processExpression(newStack);
    }
    static double processExpression(Queue<String> tokens)
    {
        // If the next token is a number, just return it
        if (tokens.peek().matches("^\\-?[1-9]\\d*(\\.\\d+)?$"))
            return Double.parseDouble(tokens.poll());
        
        // Data needed to calculate expression
        var op = tokens.poll();
        Queue<Double> nums = new LinkedList<Double>();

        // Get every number until the queue is empty.
        // If the next token is an operator,
        // the recursive function will just calculate that expression, and so on.
        // BECAUSE RECURSION!
        while (!tokens.isEmpty())
            nums.add(processExpression(tokens));
        
        // Number to start reducing on
        var start = nums.poll();

        // And do the arithmetic
        if (op.equals("+"))
            return nums.stream().reduce(start, (a, b) -> a + b);
        else if (op.equals("-"))
            return nums.stream().reduce(start, (a, b) -> a - b);
        else if (op.equals("*"))
            return nums.stream().reduce(start, (a, b) -> a * b);
        else if (op.equals("/"))
            return nums.stream().reduce(start, (a, b) -> a / b);
        
        return 0;
    }
}
