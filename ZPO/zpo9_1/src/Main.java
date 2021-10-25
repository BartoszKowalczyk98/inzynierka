import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;

public class Main {
    private static int number = 10;
    private static List<HelloFromTaskX> list = new ArrayList<>();

    public static void main(String[] args) {
        for(int i = 0;i<number;i++){
            list.add(new HelloFromTaskX(i,list));
            list.get(i).start();
        }

    }

    private static class HelloFromTaskX extends Thread {
        int id;
        List<HelloFromTaskX> lista;
        public HelloFromTaskX(int id,List<HelloFromTaskX> lista) {
            this.id = id;
            this.lista = lista;
        }

        @Override
        public void run() {
            if(id != number-1){
                try {
                    lista.get(id+1).join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("hello from task " + this.id);
        }
    }
}
