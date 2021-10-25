import java.io.*;
import java.util.*;

public class Main {
    static int ilu_do_sumy = 2;

    public static void main(String[] args) {

        try {

            List<Persona> personaArrayList = new ArrayList<>();
            Map<String, Integer> mapakrajow = new LinkedHashMap<>();


            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:\\Users\\Bartosz\\IdeaProjects\\zpo6_1\\src\\dane.txt")));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                String[] temp = inputLine.split(" ");
                personaArrayList.add(new Persona(temp[0], temp[1], temp[2], Integer.valueOf(temp[3])));
                mapakrajow.merge(temp[2],1,(oldval,newval)->oldval+newval);
            }
            System.out.println(mapakrajow);


            Collections.sort(personaArrayList, (o1, o2) -> o1.zarobki - o2.zarobki);
            int suma_najbogatszych = 0, suma_najbiedniejszych = 0, i = 0, ilu_juzzsumowalo = 0;
            for (; i < personaArrayList.size() && ilu_juzzsumowalo < ilu_do_sumy; i++) {
                if (personaArrayList.get(i).kraj.equals("PL")) {
                    ilu_juzzsumowalo++;
                    suma_najbiedniejszych += personaArrayList.get(i).zarobki;
                }
            }
            for (i = personaArrayList.size() - 1, ilu_juzzsumowalo = 0; i > 0 && ilu_juzzsumowalo < ilu_do_sumy; i--) {
                if (personaArrayList.get(i).kraj.equals("PL")) {
                    ilu_juzzsumowalo++;
                    suma_najbogatszych += personaArrayList.get(i).zarobki;
                }
            }
            System.out.println(suma_najbogatszych);
            System.out.println(suma_najbiedniejszych);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
