import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        MyAlmostPrivateClass obiektouno = new MyAlmostPrivateClass(true, "nazwa", 1.34);
        MyAlmostPrivateClass obiektodos = new MyAlmostPrivateClass(true, "nazwa", 1.34);
        MyAlmostPrivateClass obiektotres = new MyAlmostPrivateClass(true, "innanazwa", 1.34);

        System.out.println(obiektouno.equals(obiektodos));
        System.out.println(obiektodos.equals(obiektotres));
        try {
            obiektouno.equals2(obiektodos);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
