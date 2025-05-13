package Tasks.Week2;

import java.util.Scanner;

// The implementation that got ditched by the lecturer for using loop :(
// But this should actually the most optimal solution that doesn't have any constraint
// unlike the instruction given in the task that has a constraint from 0 to 1000.
public class P2Task2 {
    public static void main(String[] args) {
        System.out.print("Enter a number: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int initialNumber = number;

        int product = 1;

        while (number > 0) {
            int digit = number % 10;
            product *= digit;
            number /= 10;
        }

        System.out.println("The multiplication of all digits in " + initialNumber + " is " + product);
    }
}
