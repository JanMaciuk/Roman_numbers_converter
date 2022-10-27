import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test { // This class is for testing ideas without messing with the main file, remove at the end.
    /*
    Mikołaj Kubś, Jan Maciuk
     */

    public static boolean CheckRoman(String input) {
        ArrayList<Character> symbols = new ArrayList<>();
        symbols.add('I');
        symbols.add('V');
        symbols.add('X');
        symbols.add('L');
        symbols.add('C');
        symbols.add('D');
        symbols.add('M');
        for (char ch: input.toCharArray()) {
            if (!symbols.contains(ch)) {
                System.out.println("Liczba nie jest rzymska ani dziesiętna całkowita");
                return false;
            }
        }
        return true;
    }

    public static String ConvertDecimal(String x) {
        StringBuilder output = new StringBuilder();

        String inputString = String.valueOf(Integer.parseInt(x));

        ArrayList<String> romanCharacters = new ArrayList<>(Arrays.asList("I", "V", "X", "L", "C", "D", "M", "", ""));

        int step = 0;
        for (int i = inputString.length() - 1; i >= 0; i--)
        {
            String a = romanCharacters.get(step);
            String b = romanCharacters.get(step + 1);
            String c = romanCharacters.get(step + 2);
            int currentInt = Integer.parseInt(Character.toString(inputString.charAt(i)));

            // przypadek I, II, III
            if (currentInt < 4){
                output.insert(0, new String(new char[currentInt]).replace("\0", a));
            }
            // IV
            else if (currentInt == 4){
                output.insert(0, a + b);
            }
            // V, VI, VII, VIII
            else if (currentInt <= 8){
                output.insert(0, b + new String(new char[currentInt - 5]).replace("\0", a));
            }
            // IX, X
            else {
                output.insert(0, (currentInt == 9? a : "") + c);
            }

            // przesuwamy się w tablicach o 2 w prawo i zajmujemy się liczbami 10 razy większymi
            step += 2;
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
            String currentCharacter = String.valueOf(x.charAt(step));

            String nextCharacter;
            if (step < n - 1)
            {
                nextCharacter = String.valueOf(x.charAt(step + 1));

                // sprawdzamy, czy następny znak jest większy w systemie rzymskim niż aktualny: jeśli tak, to znaczy że aktualny znak trzeba odjąć od następnego
                if (nextCharacter.equals(characters.get(characters.indexOf(currentCharacter) + 2)) ||
                        nextCharacter.equals(characters.get(characters.indexOf(currentCharacter) + 1)))
                {
                    sum -= nums.get(characters.indexOf(currentCharacter));
                }
                else
                {
                    sum += nums.get(characters.indexOf(currentCharacter));
                }
            }
            else
            {
                sum += nums.get(characters.indexOf(currentCharacter));
            }

            step++;
        }

        return sum;
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running)
        {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Wprowadź liczbę do przeliczenia: (lub wyjdź za pomocą 'exit')");
            String input = userIn.nextLine();

            if (input.equalsIgnoreCase("exit")){
                running = false;
            }
            else{
                try {
                    int integer = Integer.parseInt(input);
                    if ((integer >= 1)&& (integer <= 3999)) {System.out.println(ConvertDecimal(input));}
                    else {System.out.println("Liczba z poza zakresu liczb rzymskich (1 do 3999)");}
                }
                catch (NumberFormatException e) {
                    if (CheckRoman(input)){
                    System.out.println(ConvertRoman(input));}

                }
            }
        }
    }
}