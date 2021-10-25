import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        int [] arr = {4,10,3,7,4,1,6,2};
        try {
            MaxSearchAlgorithms maxSearchAlgorithms = new MaxSearchAlgorithms();
            Method[] methods =  maxSearchAlgorithms.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (m.getName().contains("Scan")){
                    System.out.println( m.invoke(maxSearchAlgorithms,arr));
                }

            }
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } 
    }
}
