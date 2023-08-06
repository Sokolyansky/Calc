import java.util.HashMap;
import java.util.Scanner;

import static java.util.regex.Pattern.matches;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        try {
            System.out.println(calc(input));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String calc(String input) throws Exception {
        int ansver = 0;
        input = input.replaceAll(" ","");
        String[] parts = input.split("\\W");
        input = input.replace(parts[0], "");
        input = input.replace(parts[1], "");
        if (matches("\\d+", parts[0]))
             return String.valueOf(calcArab(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),input));
         return String.valueOf(calcRoman(parts[0],parts[1],input));
    }
    static int calcArab (int first, int second, String sign) throws Exception {
        if((first<1)||(first>10)||(second<1)||(second>10)) throw new Exception("The number is less than 1 or more then 10");
        switch (sign){
            case("+"):
                return first+second;

            case("-"):
                return first-second;

            case("*"):
                return first*second;

            case("/"):
                return first/second;
            default :
            throw new Exception("The string does not match parameters");


        }
    }
    static String calcRoman (String first, String second, String sign) throws Exception {
        if( calcArab(romanToInt(first),romanToInt(second),sign)<1)
            throw new Exception("The result less than 1");
        return intToRoman( calcArab(romanToInt(first),romanToInt(second),sign));

    }
    public static int romanToInt(String str)
    {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("VI", 6);
        map.put("VII", 7);
        map.put("VIII", 8);
        map.put("IX", 9);
        map.put("X", 10);
        return map.get(str);
    }
    public static String intToRoman(int num)
    {
        String[] keys = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] vals = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        StringBuilder ret = new StringBuilder();
        int ind = 0;

        while(ind < keys.length)
        {
            while(num >= vals[ind])
            {
                int d = num / vals[ind];
                num = num % vals[ind];
                for(int i=0; i<d; i++)
                    ret.append(keys[ind]);
            }
            ind++;
        }

        return ret.toString();
    }
}
