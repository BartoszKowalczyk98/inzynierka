package com.company;

import org.jacop.constraints.XneqY;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.*;

public class Main {

    public static void main(String[] args) {
        Store store = new Store();  // define FD store
        int size = 9;
        // define finite domain variables
        IntVar[] v = new IntVar[size];
        for (int i=0; i<size; i++)
            v[i] = new IntVar(store, "v"+i, 1, size);
        // define constraints
        store.impose( new XneqY(v[0], v[1]) );
        store.impose( new XneqY(v[0], v[2]) );
        store.impose( new XneqY(v[0], v[3]) );
        store.impose( new XneqY(v[0], v[4]) );
        store.impose( new XneqY(v[0], v[7]) );
        store.impose( new XneqY(v[1], v[2]) );
        store.impose( new XneqY(v[2], v[3]) );
        store.impose( new XneqY(v[2], v[6]) );
        store.impose( new XneqY(v[3], v[4]) );
        store.impose( new XneqY(v[3], v[5]) );
        store.impose( new XneqY(v[3], v[6]) );
        store.impose( new XneqY(v[4], v[5]) );
        store.impose( new XneqY(v[4], v[7]) );
        store.impose( new XneqY(v[5], v[6]) );
        store.impose( new XneqY(v[5], v[7]) );
        store.impose( new XneqY(v[7], v[8]) );

        AdjacencyMatrix matrix = new AdjacencyMatrix(v,store);
        System.out.println(matrix);

        // search for a solution and print results
        Search<IntVar> search = new DepthFirstSearch<IntVar>();
        SelectChoicePoint<IntVar> select =
                new InputOrderSelect<IntVar>(store, v,
                        new IndomainMin<IntVar>());
        boolean result = search.labeling(store, select);

        if (result)
            wyswietlanie(v);
        else
            System.out.println("cos poszlo nie tak");



    }
    static void wyswietlanie(IntVar[] vars)
    {
        String[] kolory = {"CZERWONY", "CZARNY", "NIEBIESKI", "ZIELONY", "ZOLTY"};
        String result = "";
        for (IntVar v : vars)
        {
            result += v.id + " kolor: " + kolory[v.domain.value()-1] + "\n";
        }
        System.out.println(result);
    }
}
