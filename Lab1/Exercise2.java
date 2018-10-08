import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner number = new Scanner(System.in);
        System.out.println("Exercise 2");
        System.out.println("---------------");
        System.out.println("Enter 3 int values ");
        System.out.print("Number a: ");
        int a = number.nextInt();
        System.out.print("Number b: ");
        int b = number.nextInt();
        System.out.print("Number c: ");
        int c = number.nextInt();
        int sum = 0;
        while(true) {
            if (a == 13)
                break;
            else
                sum = sum + a;
            if (b == 13)
                break;
            else
                sum = sum + b;
            if (c == 13)
                break;
            else
                sum = sum + c;
            break;
        }
        System.out.println("Sum: " + sum);
    }
}
