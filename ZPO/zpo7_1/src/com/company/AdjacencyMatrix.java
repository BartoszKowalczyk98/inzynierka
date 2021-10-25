package com.company;

import org.jacop.constraints.Constraint;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.core.Var;

import java.util.Set;

public class AdjacencyMatrix {
    boolean[][] a;

    IntVar[] intVar;
    AdjacencyMatrix(IntVar [] arg1, Store store) {
        intVar = arg1;
        a = new boolean[arg1.length][arg1.length];
        Set<Constraint> constraints = store.getConstraints();

        for (Constraint c : constraints)
        {
            Set<Var> help = c.arguments();
            int x1 = 0, x2 = 0, counter = 0;
            for (Var x : help)
            {
                if (counter == 0)
                    x1 = x.index;
                else
                    x2 = x.index;
                counter++;
            }
            addEdge(x1, x2);
        }
    }
    void addEdge(int i, int j) {
        a[i][j] = true;
        a[j][i] = true;
    }
    void removeEdge(int i, int j) {
        a[i][j] = false;
        a[j][i] = false;
    }
    boolean hasEdge(int i, int j) {
        return a[i][j];
    }

    @Override
    public String toString() {
        String result = "";
        for(int i =0;i<intVar.length;i++){
            for(int j =0;j<intVar.length;j++){
                if(a[i][j])
                    result+="x ";
                else
                    result+=". ";
            }
            result+="\n";
        }
        return result;
    }
}
