public class Exercise3_1 {
    public static void main(String[] args) {
        System.out.println("Exercise 3.1");
        // three examples of arrays
        int [] array = {3,2,14,1,2,4,6};
        int [] array2 = {1,2,3,14,52,1,4,1,2,3,6};
        int [] array3 = {1,2,3,14,1,2,3,1,2,3,6};
        boolean appear = false;
        int count = 0;
        for(int i = 0; i < array.length - 2; i++){
            if(array[i] == 1 && array[i+1] == 2 && array[i+2] == 3){
                appear = true;
                count ++;
            }
        }
        if(appear)
             System.out.println("true");

        System.out.println("_____________________");
        if (count != 0)
            System.out.println("*This sequence appears " + count + " times.");
        else
            System.out.println("*This sequence doesn't appear.");
    }
}
