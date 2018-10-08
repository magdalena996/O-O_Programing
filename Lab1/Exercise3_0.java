public class Exercise3_0 {
    public static void main(String[] args) {
        System.out.println("Exercise 3.0");
        System.out.println("---------------");
        // three examples of arrays
        int [] array = {3,2,14,1,2,4,6};
        int [] array2 = {1,2,3,14,52,1,4,1,2,3,6};
        int [] array3 = {1,2,3,14,1,2,3,1,2,3,6};
        int count = 0;
        int temp = 0;
        int temp2 = 0;
        for(int value : array2){
            if(value == 1) {
                temp = 1;
                continue;
            }
            else if (temp != 1 && temp != 2 && temp != 3)
                temp = 0;
            if(value == 2 && temp == 1) {
                temp = 2;
                continue;
            }
            else if (temp != 1 && temp != 2 && temp != 3)
                temp = 0;
            if(value == 3 && temp == 2){
                temp2 = 3;
                count++;
            }
            else if (temp != 1 && temp != 2 && temp != 3)
                temp = 0;
        }
        if(temp2 == 3)
            System.out.println("true");
        System.out.println("_____________________");
        if (count != 0)
            System.out.println("*This sequence appears " + count + " times.");
        else
            System.out.println("*This sequence doesn't appear.");
    }
}
