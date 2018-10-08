import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner age = new Scanner(System.in);
        System.out.println("Exercise1");
        System.out.println("---------------");
        System.out.print("Enter age a: ");
        int a = age.nextInt();
        System.out.print("Enter age b: ");
        int b = age.nextInt();
        if (a >= 13 && b < 13 || a < 13 && b >= 13){
            if(a <= 19 && b <= 19)
                System.out.println("teen");
        }
    }
}
