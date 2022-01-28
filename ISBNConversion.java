import java.util.Scanner;

public class ISBNConversion {

    public static boolean ISBN13to10(String isbn){


        //take an isbn 13 and output as isbn 10
        String input = isbn;
        isbn = isbn.replaceAll("[^a-zA-Z0-9]", "");

        int scan = isbn.length();
        if (scan != 13)
            return false;

        int sum = 0;

        int j = 10;

        //get rid of 978 of isbn
        String convert = isbn.substring(3);

        //calculate weighted sum of first nine digits
        for (int i = 0; i < convert.length() - 1; i++){

            int ch = convert.charAt(i) - '0';  // return integer value of character in string
            if (ch < 0 || ch > 9)
                return false;

            //add value from rhs to lhs
            sum += (ch * (j));

            j--;

        }

        // calculate check digit
        int lastdigit = sum % 11;
        int checkdigit = 11 - lastdigit; // checkdigit is equaling 88 if 11-lastdigit = 10. Need to fix- 88 is ascii for X
        //char convertedChar = 'X';
        //check last digit for x
        if (checkdigit == 10) {

            checkdigit = 'X';

            sum += 10;

            System.out.print("10 Digit ISBN: " + convert.substring(0,9) +  "X" + "\n");

        }
        // if checkdigit = 11, check digit is actually 0
        else if (checkdigit == 11){

            checkdigit = 0;

            System.out.print("10 Digit ISBN: " + convert.substring(0,9) + checkdigit + "\n");
        }

        else {
            sum += (checkdigit);

            System.out.print("10 Digit ISBN: " + convert.substring(0, 9) + checkdigit + "\n");
        }
//        System.out.print("10 Digit ISBN: ");
//        for (int i = 0; i < convert.length() - 1; i++) {
//            System.out.print(convert.charAt(i));
//       }
//        if (checkdigit != convert.charAt(9)) {
//            System.out.println(checkdigit);
//        }

        return (sum % 11 == 0);
    }

    public static boolean ISBN10to13(String isbn){

        String input = isbn;
        isbn = isbn.replaceAll("[^a-zA-Z0-9]", "");

        int x = isbn.length();
        if (x != 10)
            return false;

        int sum = 0;
        int j = 1;
        int k = 3;

        // add 978 to isbn
        String ISBN13  = isbn;
        ISBN13 = "978" + isbn;

        //calculate weighted sum of first 12 digits
        for (int i = 0; i < ISBN13.length() - 1; i++){

            int ch = ISBN13.charAt(i) - '0'; // return integer value of character in string

            //atoi - Asci to Int
//            char c = '4';
//            int c_int = Integer.parseInt(c + "");

            if (ch < 0 || ch > 9)
                return false;

            //add value from rhs to lhs
            //multiply first number by 1, second by 3, third by 1, and so on
            if (i % 2 ==0) {

                sum += (ch * (j));

            } else {
                sum += (ch *(k));
            }
        }

        //check last digit for x
        int lastdigit = ISBN13.charAt(12);
        int checkdigit = sum % 10;

        if (checkdigit == 0){

            checkdigit = '0';

        } else {
            checkdigit = 10 - checkdigit;
        }

        if (lastdigit == 'X') {
            lastdigit = 10;
            sum+= lastdigit;
        } else {
            sum += (ISBN13.charAt(12));
        }

        //return isbn
        System.out.print("13 Digit ISBN: " + ISBN13.substring(0,12) + checkdigit + "\n");
//        System.out.print("13 Digit ISBN: ");
//        for (int i = 0; i < ISBN13.length() - 1; i++) {
//            System.out.print(ISBN13.charAt(i));
//        }
//        System.out.print(checkdigit);
//        System.out.println(" ");

        return (true);
    }

    public static void main (String[] args){

        Scanner in = new Scanner(System.in);

        System.out.println("Enter ISBN: ");

        String isbn = in.next();

        if (ISBN13to10(isbn)) {

            System.out.println("Valid ISBN");

        } else if (ISBN10to13(isbn)){

            System.out.println("Valid ISBN");
        } else

            System.out.print("Invalid ISBN");
    }
}
