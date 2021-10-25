import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> li = new ArrayList<>();
        Collections.addAll(li, (double) -5100, 43.257, (double) 200000, 2000000.5);
        List<String> zwrot;
        zwrot = formattedNumbers(li, 2, ',', 2, true);
        for (String s : zwrot)
            System.out.println(s);
        List<String> fn2 = formattedNumbers(li, 3, '|', 2, false);
        for (String s : fn2)
            System.out.println(s);
    }

    public static List<String> formattedNumbers(List<Double> nums, int group, char separator, int nDigits, boolean padding) {
        DecimalFormatSymbols unusualsymbols = new DecimalFormatSymbols();

        //znaki separacji
        unusualsymbols.setDecimalSeparator('.');
        unusualsymbols.setGroupingSeparator(separator);


        String pattern = "#,#";
        pattern += ".";
        if (nDigits > 0) {
            if (padding) {
                for (int i = 0; i < nDigits; i++) pattern += "0";
            } else {
                for (int i = 0; i < nDigits; i++) pattern += "#";
            }
        }


        DecimalFormat decimalFormat = new DecimalFormat(pattern, unusualsymbols);
        decimalFormat.setGroupingSize(group);


        //formatowanie i wyjscie z nowa lista
        List<String> templist = new ArrayList<>();
        for (Double d : nums) {
            templist.add(decimalFormat.format(d));
        }


        //spacje
        List<String> result = new ArrayList<>();
        int maksrozmiar = -1;
        for (String s : templist) {

            String[] temp = s.split("\\.");
            if (maksrozmiar < temp[0].length()) {
                maksrozmiar = temp[0].length();
            }
        }
        for (String s : templist) {
            String[] temp = s.split("\\.");
            if (temp[0].length() < maksrozmiar) {
                String tobebuilt = s;
                for (int i = 0; i < maksrozmiar - temp[0].length(); i++)
                    tobebuilt = " " + tobebuilt;
                result.add(tobebuilt);
            } else
                result.add(s);
        }
        return result;

    }
}
