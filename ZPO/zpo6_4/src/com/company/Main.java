package com.company;


import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class Main {

    //-5x^2 -4.5x + 2 moja funkcja mejsca zerowe -1.226 0.326


    static List<Double> doubleList = Lists.newArrayList();
    static final float EPSILON = (float)0.001;
    static final double pierwszypar = -5;
    static final double drugipar = -4.5;
    static final double trzecipar = 2;
    static double func(double x) {
        return (pierwszypar* x * x ) + (drugipar*  x)  + trzecipar;
    }
    static void bisection(double a, double b) {
        if (func(a) * func(b) >= 0.0) {
            System.out.println("zly przedial");
            return;
        }

        double c = a;
        while ((b - a) >= EPSILON) {
            c = (a + b) / 2;
            if (func(c) == 0.0)
                break;
            else if (func(c) * func(a) < 0)
                b = c;
            else
                a = c;
        }
        doubleList.add(c);
    }

    // Driver program to test above function
    public static void main(String[] args) {
        // Initial values assumed
        double a = -1.6, b = -0.76;
        bisection(a, b);
        System.out.println("miejsce zerowe przy punktach startowych "+ a+"  "+b+" to "+doubleList.get(0));
        List<Double> temp = ImmutableList.copyOf(Lists.transform(doubleList, new Function<Double, Double>() {
            @Override
            public Double apply(Double aDouble) {
                return null;
            }
        }));

    }
    // This code is contributed by Nirmal Patel
}


