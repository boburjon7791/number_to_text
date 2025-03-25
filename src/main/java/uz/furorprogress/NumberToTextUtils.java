package uz.furorprogress;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class NumberToTextUtils {
    private static final String UZ = "uz";
    private static final String UK = "uk";
    private static final String RU = "ru";

    private static final NumberLanguages YUZ_TO_TEXT = NumberLanguages.of("yuz", "юз", "сто");
    private static final NumberLanguages MING_TO_TEXT = NumberLanguages.of("ming", "минг", "тысяча");
    private static final NumberLanguages MILLION_TO_TEXT = NumberLanguages.of("million", "миллион", "миллион");
    private static final NumberLanguages MILLIARD_TO_TEXT = NumberLanguages.of("milliard", "миллиард", "миллиард");
    private static final NumberLanguages TRILLION_TO_TEXT = NumberLanguages.of("trillion", "триллион", "триллион");
    private static final NumberLanguages MINUS_TEXT = NumberLanguages.of("minus ", "минус ", "минус ");

    private static final Map<Integer, NumberLanguages> BIRLAR_XONASI = ofMap(
            1, NumberLanguages.of("bir", "бир", "один"),
            2, NumberLanguages.of("ikki", "икки", "два"),
            3, NumberLanguages.of("uch", "уч", "три"),
            4, NumberLanguages.of("to'rt", "тўрт", "четыре"),
            5, NumberLanguages.of("besh", "беш", "пять"),
            6, NumberLanguages.of("olti", "олти", "шесть"),
            7, NumberLanguages.of("yetti", "етти", "семь"),
            8, NumberLanguages.of("sakkiz", "саккиз", "восемь"),
            9, NumberLanguages.of("to'qqiz", "тўққиз", "девять")
    );

    private static final Map<Integer, NumberLanguages> ONDAN_BIRDAN_TOQQIZGACHA_RAQAMLAR_XONASI = ofMap(
            1, NumberLanguages.of("o'n bir", "ўн бир", "одиннадцать"),
            2, NumberLanguages.of("o'n ikki", "ўн икки", "двенадцать"),
            3, NumberLanguages.of("o'n uch", "ўн уч", "тринадцать"),
            4, NumberLanguages.of("o'n to'rt", "ўн тўрт", "четырнадцать"),
            5, NumberLanguages.of("o'n besh", "ўн беш", "пятнадцать"),
            6, NumberLanguages.of("o'n olti", "ўн олти", "шестнадцать"),
            7, NumberLanguages.of("o'n yetti", "ўн етти", "семнадцать"),
            8, NumberLanguages.of("o'n sakkiz", "ўн саккиз", "восемнадцать"),
            9, NumberLanguages.of("o'n to'qqiz", "ўн тўққиз", "девятнадцать")
    );

    private final static Map<Integer, NumberLanguages> ONLAR_XONASI = ofMap(
            1, NumberLanguages.of("o'n", "ўн", "десять"),
            2, NumberLanguages.of("yigirma", "йигирма", "двадцать"),
            3, NumberLanguages.of("o'ttiz", "ўттиз", "тридцать"),
            4, NumberLanguages.of("qirq", "қирқ", "сорок"),
            5, NumberLanguages.of("ellik", "эллик", "пятьдесят"),
            6, NumberLanguages.of("oltmish", "олтмиш", "шестьдесят"),
            7, NumberLanguages.of("yetmish", "етмиш", "семьдесят"),
            8, NumberLanguages.of("sakson", "саксон", "восемьдесят"),
            9, NumberLanguages.of("to'qson", "тўқсон", "девяносто")
    );

    private NumberToTextUtils() {}

    private static class NumberLanguages{
        private final String uz;
        private final String uk;
        private final String ru;

        private NumberLanguages(String uz, String uk, String ru) {
            this.uz=uz;
            this.uk=uk;
            this.ru=ru;
        }

        public static NumberLanguages of(String uz, String uk, String ru) {
            return new NumberLanguages(uz, uk, ru);
        }

        public static NumberLanguages ofEmptyString() {
            return new NumberLanguages("", "", "");
        }

        private String getUz() {
            return uz;
        }

        private String getUk() {
            return uk;
        }

        private String getRu() {
            return ru;
        }

        public String getText(String language) {
            String text = "";

            switch (language) {
                case UZ: text=getUz(); break;
                case UK: text=getUk(); break;
                case RU: text=getRu(); break;
            }

            return text;
        }
    }

    public static String toText(long number, String language) {
        boolean isNegative = number < 0;

        StringBuilder text = new StringBuilder();

        if (isNegative) {
            number = Math.abs(number);
            text.append(MINUS_TEXT.getText(language));
        }

        List<String> sonlarListi = new LinkedList<>();

        long n = number;
        int onXonalikSonningIkkinchiRaqami = 0;
        int ikkitaOldingiRaqami = 0;

        int i=0;
        while (n > 0) {
            i++;
            int digit = (int) (n % 10);

            switch (i) {
                case 1: {
                    onXonalikSonningIkkinchiRaqami = digit;
                    sonlarListi.add(BIRLAR_XONASI.getOrDefault(digit, NumberLanguages.ofEmptyString()).getText(language));
                    break;
                }

                case 14:
                case 11:
                case 8:
                case 5:
                case 2: {

                    if (digit==1 && onXonalikSonningIkkinchiRaqami!=0) {
                        sonlarListi.remove(sonlarListi.size()-1);
                        sonlarListi.add(ONDAN_BIRDAN_TOQQIZGACHA_RAQAMLAR_XONASI.getOrDefault(onXonalikSonningIkkinchiRaqami, NumberLanguages.ofEmptyString()).getText(language));
                        break;
                    }

                    sonlarListi.add(ONLAR_XONASI.getOrDefault(digit, NumberLanguages.ofEmptyString()).getText(language));
                    break;
                }

                case 15:
                case 13:
                case 12:
                case 10:
                case 9:
                case 7:
                case 6:
                case 4:
                case 3: {
                    if (i==3 || i==6 || i==9 || i==12 || i==15) {
                        if (i!=3 && digit==0 && ikkitaOldingiRaqami==0) {
                            sonlarListi.remove(sonlarListi.size()-3);
                            break;
                        }
                        if (digit==0 && ikkitaOldingiRaqami==0) {
                            sonlarListi.remove(sonlarListi.size()-1);
                            break;
                        }
                        sonlarListi.add(YUZ_TO_TEXT.getText(language));
                    }
                    if (i==4) {
                        sonlarListi.add(MING_TO_TEXT.getText(language));
                        onXonalikSonningIkkinchiRaqami = digit;
                        ikkitaOldingiRaqami = digit;
                    }
                    if (i==7) {
                        sonlarListi.add(MILLION_TO_TEXT.getText(language));
                        onXonalikSonningIkkinchiRaqami = digit;
                        ikkitaOldingiRaqami = digit;
                    }
                    if (i==10) {
                        sonlarListi.add(MILLIARD_TO_TEXT.getText(language));
                        onXonalikSonningIkkinchiRaqami = digit;
                        ikkitaOldingiRaqami = digit;
                    }
                    if (i==13) {
                        sonlarListi.add(TRILLION_TO_TEXT.getText(language));
                        onXonalikSonningIkkinchiRaqami = digit;
                        ikkitaOldingiRaqami = digit;
                    }

                    sonlarListi.add(BIRLAR_XONASI.getOrDefault(digit, NumberLanguages.ofEmptyString()).getText(language));
                    break;
                }
            }

            n /=10;

        }

        for (int j = sonlarListi.size()-1; j >= 0; j--) {

            String str = sonlarListi.get(j);
            if (!str.intern().trim().isEmpty()) {
                text.append(str);
                text.append(" ");
            }
        }

        String trimmed = text.toString().intern().trim();
        return !trimmed.isEmpty() ? (Character.toUpperCase(trimmed.charAt(0))+trimmed.substring(1)).intern() : "";
    }

    private static <K, V> Map<K, V> ofMap(Object... keysAndValues) {
        // checking keysAndValues arguments length is even
        if (keysAndValues.length % 2 != 0) {
            throw new IllegalArgumentException("Number of keys and values must be even");
        }

        HashMap<K, V> objectObjectHashMap = new HashMap<>();

        for (int i = 0; i < keysAndValues.length; i++) {
            if (i % 2 == 0) {
                try {
                    objectObjectHashMap.put((K)keysAndValues[i], null);
                }catch (Exception e) {
                    throw new RuntimeException("Key data type is mismatching", e);
                }
                continue;
            }
            try {
                objectObjectHashMap.put((K)keysAndValues[i-1], (V)keysAndValues[i]);
            }catch (Exception e) {
                throw new RuntimeException("Key or value data type is mismatching", e);
            }
        }
        return objectObjectHashMap;
    }
}
