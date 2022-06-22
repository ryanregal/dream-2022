import java.io.IOException;
import java.io.InputStream;
 
class MainII {
    private static InputStream in;
    static {
        in = System.in;
    }
 
    public static void getChar_while() throws IOException {
        int input = in.read();
        if(input == '\n') {
            input = in.read();
        }
        while(input != 'q') {
            System.out.printf("%c", input);
            input = in.read();
        }
    }
 
    public static void main(String[] args) throws IOException {
	    System.out.println("Please input char or String:(q to quit)");
	    getChar_while();
    }
}