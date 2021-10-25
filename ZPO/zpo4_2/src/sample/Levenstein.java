package sample;

import java.util.ArrayList;

public class Levenstein {
    public static double LevQWERTY(String s0, String s1) {


        double[][] pkt = new double[s0.length() + 1][s1.length() + 1];
        for (int i = 0; i <= s0.length(); i++) {
            for (int j = 0; j <= s1.length(); j++) {
                if (i == 0) {
                    pkt[i][j] = j;
                } else if (j == 0) {
                    pkt[i][j] = i;
                } else {
                    pkt[i][j] = min(pkt[i - 1][j - 1] + costOfSubstitution(s0.charAt(i - 1), s1.charAt(j - 1)),
                            pkt[i - 1][j] + 1,
                            pkt[i][j - 1] + 1);
                }
            }
        }
        return pkt[s0.length()][s1.length()];
    }

    public static double costOfSubstitution(char a, char b) {
        if (a == b)
            return 0.0;
        return 1.0;
    }

    public static double min(double firstNum, double secondNum, double thirdNum) {
        return Double.min(Double.min(firstNum, secondNum), thirdNum);
    }
    public static double minLevQWERTY(String given, ArrayList<String> good){
        double min = LevQWERTY(given,good.get(0));
        if(good.size()>1){
            double temp;
            for(String s: good){
                temp = LevQWERTY(given,s);
                if(temp<min)
                    min=temp;
            }
        }
        return min;
    }

}
