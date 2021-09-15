package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) 
    {
        System.out.println("Hello, World!");
    }
    static double evalExpression(String exp)
    {
        return processExpression(new Scanner(
            Arrays.stream(exp.split(" "))
                    .filter(n -> n.length() != 0)
                    .reduce("", (a, b) -> a + "\n" + b)
            )
        );
    }
    static double processExpression(Scanner exp)
    {
        if (exp.hasNextDouble())
        {
            return exp.nextDouble();
        }
        
        var result = 0.0;
        var nums = new ArrayList<Double>();
        var op = exp.next();
        
        while (exp.hasNextDouble())
        {
            nums.add(processExpression(exp));
        }
        if (op.equals("+"))
        {
            result = nums.stream().reduce(0.0, (a, b) -> a + b);
        }
        else if (op.equals("-"))
        {
            result = nums.stream().reduce(0.0, (a, b) -> a - b);
        }
        else if (op.equals("*"))
        {
            result = nums.stream().reduce(0.0, (a, b) -> a * b);
        }
        else if (op.equals("/"))
        {
            result = nums.stream().reduce(0.0, (a, b) -> a / b);
        }

        return result;
    }
}
