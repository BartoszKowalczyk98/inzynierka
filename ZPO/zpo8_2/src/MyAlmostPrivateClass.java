import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class MyAlmostPrivateClass {
    private boolean trueorfalse;
    private String name;
    private Dog goodboi;

    private Double number;

    public MyAlmostPrivateClass(boolean trueorfalse, String name, Double number) {
        this.trueorfalse = trueorfalse;
        this.name = name;
        this.number = number;
        this.goodboi = new Dog("puszek", 12);

    }
/*

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyAlmostPrivateClass that = (MyAlmostPrivateClass) o;
        return trueorfalse == that.trueorfalse &&
                Objects.equals(name, that.name) &&
                Objects.equals(goodboi, that.goodboi) ;
    }
*/


    @Override
    public boolean equals(Object o) {
        try {
            Method[] methods = o.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (m.getName().startsWith("get")) {
                    if (m.getAnnotation(IgnoreEquals.class) != null) {
                        System.out.println("debug");
                    }

                    if (!(m.invoke(this) == m.invoke((MyAlmostPrivateClass) o))) {
                        return false;
                    }
                } else if (m.getName().startsWith("is")) {
                    if (!(m.invoke(this) == m.invoke(o))) {
                        return false;
                    }
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean equals2(Object obj) throws InvocationTargetException, IllegalAccessException {
        Class cls = obj.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().contains("get") || method.getName().contains("is")) {
                if (method.getAnnotation(IgnoreEquals.class) != null) {
                    System.out.println("debug");
                } else {
                    if (method.invoke(this) != method.invoke(obj)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(trueorfalse, name, goodboi, number);
    }


    ////////////////////////////////////////////////////////gettery i settery
    public boolean isTrueorfalse() {
        return trueorfalse;
    }

    public String getName() {
        return name;
    }


    @IgnoreEquals
    public Dog getGoodboi() {
        return goodboi;
    }


    public Double getNumber() {
        return number;
    }

    public void setTrueorfalse(boolean trueorfalse) {
        this.trueorfalse = trueorfalse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGoodboi(Dog goodboi) {
        this.goodboi = goodboi;
    }

    public void setNumber(Double number) {
        this.number = number;
    }
}
