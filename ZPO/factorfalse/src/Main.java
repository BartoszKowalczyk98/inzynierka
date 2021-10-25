import javassist.*;
import javassist.runtime.Cflow;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Main extends ClassLoader {
    private static ClassPool classPool = new ClassPool();

    public static void main(String[] args) {
        try {
            classPool.insertClassPath("C:\\Users\\kowal\\IdeaProjects\\zpo10_2\\src");
            Class temp = new Main().znajdzKlase("Factorial");
            temp.getDeclaredMethod("main", new Class[]{String[].class}).invoke(null, new Object[]{args});


        } catch (NotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public Class znajdzKlase(String nazwa) {
        try {
            CtClass ctClass = classPool.getCtClass(nazwa);
            CtMethod[] ctMethods = ctClass.getDeclaredMethods();

            for (CtMethod m : ctMethods) {
                if (m.getName().startsWith("fact")) {

                    m.useCflow(m.getName());
                    m.insertBefore("if ($cflow(" +
                            m.getName() +
                            ") == 0)"
                            + "    System.out.println(\"fact \" + $1);");
                }
            }

            byte[] b = ctClass.toBytecode();
            return defineClass(nazwa, b, 0, b.length);
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
