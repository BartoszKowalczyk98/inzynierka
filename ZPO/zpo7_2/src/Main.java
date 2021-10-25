import java.io.*;
import java.text.Collator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class Main {

    static int n;
    static double mediana, odchyleniestd;
    static Random random = new Random();

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("blad argumentow wejsciowych!");
            return;
        }
        n = Integer.valueOf(args[0]);
        mediana = Double.valueOf(args[1]);
        odchyleniestd = Double.valueOf(args[2]);
        assert odchyleniestd>0;
        try {
            wpisywaniedopliku();
            przepisywaniedotxt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void wpisywaniedopliku() throws IOException {
        File file = new File("plikbinarny.bin");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        for (int i = 0; i < n; i++) {
            dataOutputStream.writeDouble(random.nextGaussian() * odchyleniestd + mediana);
        }
        dataOutputStream.close();
    }
    static void przepisywaniedotxt() throws IOException {
        File filebin = new File("plikbinarny.bin");
        if (!filebin.exists()) {
            throw new FileNotFoundException();
        }
        File filetxt = new File("pliktextowy.txt");
        if (filetxt.exists()) {
            filetxt.delete();
        }
        filetxt.createNewFile();

        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filebin));
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(filetxt));


        //znaki separacji
        Locale locale = Locale.getDefault(Locale.Category.FORMAT);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern("#.####");
        Double inputline;
        while(dataInputStream.available()>0){
            inputline = dataInputStream.readDouble();
            System.out.println(inputline);
            dataOutputStream.writeBytes(decimalFormat.format(inputline));
            dataOutputStream.writeChar('\n');
        }
        dataInputStream.close();
        dataOutputStream.close();
    }
}
