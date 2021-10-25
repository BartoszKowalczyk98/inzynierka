package glownaPaczka;

import java.text.Collator;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Collator collator = Collator.getInstance();
        String[] names = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
                "Zyta", "Órszula", "Świętopełk"};
        printowanko(names);
        sortStrings(collator,names);
        printowanko(names);
    }
    public static void printowanko(String [] slowa){
        for (String s: slowa) {
            System.out.print(s+ " ");
        }
        System.out.println("\n");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void sortStrings(Collator collator, String[] words){
        int i,j;
        String key;
        for (j = 1; j < words.length; j++) { //the condition has changed
            key = words[j];
            i = j - 1;
            while (i >= 0) {
                if (/*key.compareTo(words[i])*/ collator.compare(key,words[i]) > 0) {//here too
                    break;
                }
                words[i + 1] = words[i];
                i--;
            }
            words[i + 1] = key;

        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void fastSortStrings(Collator collator, String[] words){
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return collator.compare(o1,o2);
            }
        });

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void fastSortStrings2(Collator collator, String[] words){
        Arrays.sort(words, (o1, o2) -> collator.compare(o1,o2));
    }

}
