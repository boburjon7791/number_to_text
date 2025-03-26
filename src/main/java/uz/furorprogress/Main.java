package uz.furorprogress;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static long[] testNumbers = {
            1L, 9L, 15L, 20L, 75L, 100L, 321L, 999L,
            1000L, 5678L, 12345L, 99999L, 100000L, 654321L, 999999L,
            1000000L, 2345678L, 50000000L, 123456789L, 999999999L,
            1000000000L, 5432109876L, 50000000000L, 987654321000L, 999999999999L,
            1000000000000L, 2147483648000L, 5678901234567L, 50000000000000L,
            99999999999999L, 100070001040509L, 940070001040509L,
            3, 27, 58, 112, 235, 789, 1543, 6789, 12345, 98765,
            543210, 876543, 1000001, 3456789, 56789012, 176143110,
            2147483647L, 500008000000L, 9994999999999L, 100000000000000L
    };
    static final Random RANDOM = new Random();

    static DecimalFormat df = new DecimalFormat("0.##");
    static {
        df.setMaximumFractionDigits(2); // Kasr qismini cheklash
    }

    static double[] testDoubleNumbers = Arrays.stream(testNumbers)
            .mapToDouble(num -> num + Math.random()) // Random kasr qo'shish
            .filter(num -> Math.abs(num) < 1_000_000_000_000_000L) // 15 xonadan katta bo'lmasligi uchun
            .map(operand -> {
                double multiplicationResult = operand * 2;
                return multiplicationResult / 2;
            })
            .toArray();
    static {
        System.out.println(Arrays.toString(Arrays.stream(testDoubleNumbers).boxed().map(number -> df.format(number)).toArray(String[]::new)));
    }


    public static void main(String[] args) {
//        testInputs();
//        testStaticNumbers();
    }

    static void testInputs(){
        double number;
        while (true){
            try {
                number = new Scanner(System.in).nextDouble();
                System.out.printf("%s = %s", df.format(number), NumberToTextUtils.toText(number, "uz"));
                System.out.println();
                System.out.printf("%s = %s", df.format(number),  NumberToTextUtils.toText(number, "ru"));
                System.out.println();
                System.out.println();
                System.out.printf("%s = %s", df.format(number),  NumberToTextUtils.toText(number, "uk"));
                System.out.println();
                System.out.println("---------------");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static void testStaticNumbers(){
        while (true) {
            try {
                for (long number : testNumbers) {
                    System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "uz"));
                    System.out.println();
                    System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "ru"));
                    System.out.println();
                    System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "uk"));
                    System.out.println();
                    System.out.println("---------------");
                }
                for (double number : testDoubleNumbers) {
                    System.out.printf("%s = %s", df.format(number),  NumberToTextUtils.toText(number, "uz"));
                    System.out.println();
                    System.out.printf("%s = %s", df.format(number),  NumberToTextUtils.toText(number, "ru"));
                    System.out.println();
                    System.out.printf("%s = %s", df.format(number),  NumberToTextUtils.toText(number, "uk"));
                    System.out.println();
                    System.out.println("---------------");
                }
                long min = -999_999_999_999_999L;
                long max = 999_999_999_999_999L;
                for (int i = 0; i < 10; i++) {
                    long number = RANDOM.nextLong() % max;
                    System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "uz"));
                    System.out.println();
                    System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "ru"));
                    System.out.println();
                    System.out.printf("%s = %s", number,  NumberToTextUtils.toText(number, "uk"));
                    System.out.println();
                    System.out.println("---------------");
                }
                System.out.print("Input testing numbers: ");
                double number = new Scanner(System.in).nextDouble();
                System.out.printf("%s = %s", df.format(number),  NumberToTextUtils.toText(number, "uz"));
                System.out.println();
                System.out.printf("%s = %s", df.format(number),  NumberToTextUtils.toText(number, "ru"));
                System.out.println();
                System.out.printf("%s = %s", df.format(number),  NumberToTextUtils.toText(number, "uk"));
                System.out.println();
                System.out.println("---------------");
                System.out.println("Press the enter to continue...");
                new Scanner(System.in).nextLine();

                // eng asosiy xato hisoblangan sonlar
                // 500008000000.85 Besh yuz milliard yuz sakkiz million butun yuzdan sakson besh
                // 56789012        Ellik olti million yetti yuz sakson to'qqiz ming
                // 50000007000 = Ellik milliard million yetti
                // -933691099782589 = Minus to'qqiz yuz o'ttiz uch trillion olti yuz to'qson bir milliard yuz to'qson to'qqiz million yetti yuz sakson ikki ming besh yuz sakson to'qqiz
                // -604563185080300 = Minus olti yuz to'rt trillion besh yuz oltmish uch milliard bir yuz sakson besh million sakson uch yuz

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Press the enter to continue...");
                new Scanner(System.in).nextLine();
            }
        }
    }
}
