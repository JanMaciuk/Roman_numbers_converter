import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void CheckRoman(String input) {
        ArrayList<Character> symbols = new ArrayList<>();
        symbols.add('I');
        symbols.add('V');
        symbols.add('X');
        symbols.add('L');
        symbols.add('C');
        symbols.add('D');
        for (char ch: input.toCharArray()) {
            if (!symbols.contains(ch)) {
                System.out.println("Liczba nie jest rzymska ani dziesiętna całkowita"); System.exit(1);
            }
        }
        System.out.println(ConvertRoman(input));
    }

    public static String ConvertDecimal(String x) {
        StringBuilder output = new StringBuilder();
        int input;
        try
        {
            input = Integer.parseInt(x);
        }
        catch (NumberFormatException e)
        {
            input = 0;
        }

        String inputString = String.valueOf(input);
        int step = 0;
        ArrayList<String> characters = new ArrayList<>(Arrays.asList("I", "V", "X", "L", "C", "D", "", "", ""));

        for (int i = inputString.length() - 1; i >= 0; i--)
        {
            String a = characters.get(step);
            String b = characters.get(step + 1);
            String c = characters.get(step + 2);
            int iInt = Integer.parseInt(Character.toString(inputString.charAt(i)));

            if (iInt < 4){
                output.insert(0, new String(new char[iInt]).replace("\0", a));
            }
            else if (iInt == 4){
                output.insert(0, a + b);
            }
            else if (iInt <= 8){
                output.insert(0, b + new String(new char[iInt - 5]).replace("\0", a));
            }
            else
            {
                output.insert(0, (iInt == 9? a : "") + c);
            }

            step+=2;
        }
        return output.toString();
    }
    public static int ConvertRoman(String x) {
        ArrayList<String> characters = new ArrayList<>(Arrays.asList("I", "V", "X", "L", "C", "D", "M", "", ""));
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 5, 10, 50, 100, 500, 1000, 0, 0));

        int sum = 0;
        int step = 0;
        int n = x.length();
        while (step < n)
        {
            String current = String.valueOf(x.charAt(step));

            String next;
            if (step < n - 1)
            {

                next = String.valueOf(x.charAt(step + 1));
                boolean foundBigger = false;
                for (int i = characters.indexOf(current) + 1; i < characters.size(); i++){
                    if (next.equals(characters.get(i))){
                        foundBigger = true;
                        break;
                    }
                }
                if (foundBigger){
                    sum -= nums.get(characters.indexOf(current));

                }
                else{
                    sum += nums.get(characters.indexOf(current));

                }
            }
            else
            {
                sum += nums.get(characters.indexOf(current));
            }

            step++;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner userIn = new Scanner(System.in);
        System.out.println("Wprowadź liczbę do przeliczenia:");
        String input = userIn.nextLine();
        try {
            int integer = Integer.parseInt(input);
            if ((integer >= 1)&& (integer <= 3999)) {System.out.println(ConvertDecimal(input));}
            else {System.out.println("Liczba z poza zakresu liczb rzymskich (1 do 3999)");}

        }
        catch (NumberFormatException e) {
            CheckRoman(input); // TODO: add support for values above 1000
        }


    }
}