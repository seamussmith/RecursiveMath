package main;

import java.util.*;

public class App
{
    public static void main(String[] args) 
    {
        var in = new Scanner(System.in);
        System.out.println(evalExpression(in.nextLine()));
    }
    static double evalExpression(String exp)
    {
        // Put each token in the string into a stack for easier parsing
        var split = Arrays.asList(exp.split(" "));
        Collections.reverse(split);
        Queue<String> newStack = new LinkedList<String>();
        newStack.addAll(split.stream().filter(n -> n.length() != 0).toList());

        return processExpression(newStack);
    }
    static double processExpression(Queue<String> exp)
    {
        // If the next token is a number, just return it
        if (exp.peek().matches("^\\-?[1-9]\\d*(\\.\\d+)?$"))
        {
            return Double.parseDouble(exp.poll());
        }
        
        var result = 0.0;        
        Queue<Double> nums = new LinkedList<Double>();
        var op = exp.poll();

        while (!exp.isEmpty())
        {
            nums.add(processExpression(exp));
        }
        
        var start = nums.poll();

        if (op.equals("+"))
        {
            result = nums.stream().reduce(start, (a, b) -> a + b);
        }
        else if (op.equals("-"))
        {
            result = nums.stream().reduce(start, (a, b) -> a - b);
        }
        else if (op.equals("*"))
        {
            result = nums.stream().reduce(start, (a, b) -> a * b);
        }
        else if (op.equals("/"))
        {
            result = nums.stream().reduce(start, (a, b) -> a / b);
        }

        return result;
    }
}
