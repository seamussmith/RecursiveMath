package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class App
{
    public static void main(String[] args) 
    {
        var in = new Scanner(System.in);
        System.out.println(evalExpression(in.nextLine()));
    }
    static double evalExpression(String exp)
    {
        var split = Arrays.asList(exp.split(" "));
        Collections.reverse(split);
        var newStack = new Stack<String>();
        newStack.addAll(split.stream().filter(n -> n.length() != 0).toList());
        return processExpression(newStack);
    }
    static double processExpression(Stack<String> exp)
    {
        if (exp.peek().matches("^-?\\d+$"))
        {
            return Double.parseDouble(exp.pop());
        }
        
        var result = 0.0;
        var nums = new ArrayList<Double>();
        var op = exp.pop();

        while (!exp.isEmpty())
        {
            nums.add(processExpression(exp));
        }
        if (op.equals("+"))
        {
            result = nums.stream().reduce(0.0, (a, b) -> a + b);
        }
        else if (op.equals("-"))
        {
            var start = nums.get(0) * 2;
            result = nums.stream().reduce(start, (a, b) -> a - b);
        }
        else if (op.equals("*"))
        {
            result = nums.stream().reduce(1.0, (a, b) -> a * b);
        }
        else if (op.equals("/"))
        {
            var start = nums.get(0) * nums.get(0);
            result = nums.stream().reduce(start, (a, b) -> a / b);
        }

        return result;
    }
}
