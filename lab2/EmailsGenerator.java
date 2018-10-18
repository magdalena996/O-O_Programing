package lab2;

import java.util.Scanner;

public class EmailsGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many employees would you like to add? | ");
        int n = scanner.nextInt();
        Employee arrayMails[] = new Employee[n];
        System.out.println("     **********************************");
        System.out.println("Please enter name and surname of " + n + " employees.");
        System.out.println(" ");
        for (int i = 0; i < arrayMails.length; i++) {
            arrayMails[i] = new Employee();
            System.out.print("Enter name " + (i+1) + ": ");
            arrayMails[i].name = scanner.next();
            System.out.print("Enter surname " + (i+1) + ": ");
            arrayMails[i].surname = scanner.next();
            System.out.println(" ");
        }
        int count = 1;
        System.out.println("    _______________________________");
        System.out.println("      Generated email addresses:");
        System.out.println(" ");
        for(int i = 0; i < arrayMails.length; i++){
            for(int j = i+1; j <arrayMails.length; j++){
                if(arrayMails[i].name.toLowerCase().equals(arrayMails[j].name.toLowerCase())
                        && arrayMails[i].surname.toLowerCase().equals(arrayMails[j].surname.toLowerCase())) {
                    arrayMails[j].name += Integer.toString(count++);
                }
            }
            count = 1;
            System.out.println("    Email " + (i+1) + ": " + arrayMails[i].surname + "." + arrayMails[i].name + "@mex.com");
        }
        System.out.println("    _______________________________");
    }
}