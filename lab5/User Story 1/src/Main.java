import java.io.*;

public class Main {

    private static String stringArray[] = new String[3];
    private static char c[][] = new char[3][27];

    public static void main(String[] args) {

        File file = new File("src/bank_ocr_dojo_us1");
        readAndConvertFile(file);

    }

    //
    private static void readAndConvertFile(File file){
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new FileReader(file));

            // checking if there is anything left to read
            while ( bufferedReader.ready() ) {

                // writing lines to string array
                for (int i = 0; i < 3; i++) {
                    stringArray[i] = bufferedReader.readLine();
                    System.out.println(stringArray[i]);
                }

                convertStrToCharArr();

                // passing empty line
                bufferedReader.readLine();

                System.out.println();
                System.out.print("Converted account number:  ");

                checkAndCovertNumber();

                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static void checkAndCovertNumber(){
        for( int j = 0; j < 27; j+=3 )
        {
            if(c[0][j+1] == '_' && c[1][j+0] == '|' && c[1][j+1] == ' ' && c[1][j+2] == '|' && c[2][j+0] == '|' && c[2][j+1] == '_' && c[2][j+2] == '|')  //"0"
                System.out.print("0");
            else if(c[0][j+1] == ' ' && c[1][j+0] == ' ' && c[1][j+1] == ' ' && c[1][j+2] == '|' && c[2][j+0] == ' ' && c[2][j+1] == ' ' && c[2][j+2] == '|') //"1"
                System.out.print("1");
            else if(c[0][j+1] == '_' && c[1][j+0] == ' ' && c[1][j+1] == '_' && c[1][j+2] == '|' && c[2][j+0] == '|' && c[2][j+1] == '_' && c[2][j+2] == ' ') //"2"
                System.out.print("2");
            else if(c[0][j+1] == '_' && c[1][j+0] == ' ' && c[1][j+1] == '_' && c[1][j+2] == '|' && c[2][j+0] == ' ' && c[2][j+1] == '_' && c[2][j+2] == '|') //"3"
                System.out.print("3");
            else if(c[0][j+1] == ' ' && c[1][j+0] == '|' && c[1][j+1] == '_' && c[1][j+2] == '|' && c[2][j+0] == ' ' && c[2][j+1] == ' ' && c[2][j+2] == '|') //"4"
                System.out.print("4");
            else if(c[0][j+1] == '_' && c[1][j+0] == '|' && c[1][j+1] == '_' && c[1][j+2] == ' ' && c[2][j+0] == ' ' && c[2][j+1] == '_' && c[2][j+2] == '|') //"5"
                System.out.print("5");
            else if(c[0][j+1] == '_' && c[1][j+0] == '|' && c[1][j+1] == '_' && c[1][j+2] == ' ' && c[2][j+0] == '|' && c[2][j+1] == '_' && c[2][j+2] == '|') //"6"
                System.out.print("6");
            else if(c[0][j+1] == '_' && c[1][j+0] == ' ' && c[1][j+1] == ' ' && c[1][j+2] == '|' && c[2][j+0] == ' ' && c[2][j+1] == ' ' && c[2][j+2] == '|') //"7"
                System.out.print("7");
            else if(c[0][j+1] == '_' && c[1][j+0] == '|' && c[1][j+1] == '_' && c[1][j+2] == '|' && c[2][j+0] == '|' && c[2][j+1] == '_' && c[2][j+2] == '|') //"8"
                System.out.print("8");
            else if(c[0][j+1] == '_' && c[1][j+0] == '|' && c[1][j+1] == '_' && c[1][j+2] == '|' && c[2][j+0] == ' ' && c[2][j+1] == '_' && c[2][j+2] == '|') //"9"
                System.out.print("9");
        }
    }

    private static void convertStrToCharArr(){
        // converting strings to char array
        c[0] = stringArray[0].toCharArray();
        c[1] = stringArray[1].toCharArray();
        c[2] = stringArray[2].toCharArray();
    }
}
