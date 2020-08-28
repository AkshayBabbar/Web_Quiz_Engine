import java.util.Scanner;

public class Main {

    public static String prepareFullName(String firstName, String lastName) {
        // write your code here
        StringBuffer sb = new StringBuffer();
        if(firstName!=null) {
            sb.append(firstName);
          
        }
        if(lastName!=null){
          sb.append(" ");
        sb.append(lastName);
        }
        return sb.toString();
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        String firstName = scanner.nextLine();
        firstName = "null".equals(firstName) ? null : firstName;

        String lastName = scanner.nextLine();
        lastName = "null".equals(lastName) ? null : lastName;

        System.out.println(prepareFullName(firstName, lastName));
    }
}
