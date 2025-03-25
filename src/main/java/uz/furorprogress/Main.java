package uz.furorprogress;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Main {
    static long[] testNumbers = {
            1L, 9L, 15L, 20L, 75L, 100L, 321L, 999L,
            1000L, 5678L, 12345L, 99999L, 100000L, 654321L, 999999L,
            1000000L, 2345678L, 50000000L, 123456789L, 999999999L,
            1000000000L, 5432109876L, 50000000000L, 987654321000L, 999999999999L,
            1000000000000L, 2147483648000L, 5678901234567L, 50000000000000L,
            99999999999999L, 100000000000000L, 100070001040509L, 940070001040509L,
            3, 27, 58, 112, 235, 789, 1543, 6789, 12345, 98765,
            543210, 876543, 1000001, 3456789, 56789012, 876543210,
            2147483647L, 500008000000L, 9994999999999L, 100000000000000L
    };
    static final Random RANDOM = new Random();

    public static void main(String[] args) {
        for (long number : testNumbers) {
            System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "uz"));
            System.out.println();
            System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "ru"));
            System.out.println();
            System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "uk"));
            System.out.println();
            System.out.println("---------------");
        }
        for (int i = 0; i < 10; i++) {
            long number;
            do{
                number=RANDOM.nextLong();
            } while (number>999_999_999_999_999L || number<-999_999_999_999_999L);
            System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "uz"));
            System.out.println();
            System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "ru"));
            System.out.println();
            System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "uk"));
            System.out.println();
            System.out.println("---------------");
        }
    }
}