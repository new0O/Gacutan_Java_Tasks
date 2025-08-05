package tasks;
import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Basic Arithmetic Operation");
        System.out.println("--------------------------");
        
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
    
        double sum = add(num1, num2);
        double difference = subtract(num1, num2);
        double product = multiply(num1, num2);
        double quotient = divide(num1, num2);
        
        System.out.println("\nResults:");
        System.out.println(num1 + " + " + num2 + " = " + sum);
        System.out.println(num1 + " - " + num2 + " = " + difference);
        System.out.println(num1 + " * " + num2 + " = " + product);
        
    
        if (Double.isInfinite(quotient) || Double.isNaN(quotient)) {
            System.out.println(num1 + " / " + num2 + " = Cannot divide by zero");
        } else {
            System.out.println(num1 + " / " + num2 + " = " + quotient);
        }
        
        scanner.close();
    }
    
    public static double add(double a, double b) {
        return a + b;
    }
    public static double subtract(double a, double b) {
        return a - b;
    }
    public static double multiply(double a, double b) {
        return a * b;
    }
    public static double divide(double a, double b) {
        return b == 0 ? Double.NaN : a / b;
    }
}

