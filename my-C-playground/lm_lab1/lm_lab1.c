#include<stdio.h>
int main(int argc, char const *argv[])
{
    int tablica_przejsc[25][3] = {
        {1,2,5},
        {2,3,6},
        {3,4,7},
        {4,5,8},
        {5,6,9},
        {6,7,10},
        {7,8,11},
        {8,9,12},
        {9,10,13},
        {10,11,14},
        {11,12,15},
        {12,13,16},
        {13,14,17},
        {14,15,18},
        {15,16,19},
        {16,17,20},
        {17,18,21},
        {18,19,22},
        {19,20,23},
        {20,21,24},
        {20,20,20},
        {21,21,21},
        {22,22,22},
        {23,23,23},
        {24,24,24}
    };
    int aktualny_stan = 0;
    int wrzucona_moneta = 0;
    int iterator = 0;
    int tablica_wykonanych_przejsc[25]= {};
    printf("Wrzuc monete (1, 2, 5):  ");
    while (aktualny_stan != 20 && aktualny_stan != 21 && aktualny_stan != 22 && aktualny_stan != 23 && aktualny_stan != 24 )
    {
        scanf("%d", &wrzucona_moneta);
        // funkcja przejscia
        if (wrzucona_moneta == 1)
        {
            tablica_wykonanych_przejsc[iterator++]=aktualny_stan;
            aktualny_stan = tablica_przejsc[aktualny_stan][0];

        }
        else if (wrzucona_moneta==2)
        {
            tablica_wykonanych_przejsc[iterator++]=aktualny_stan;
            aktualny_stan = tablica_przejsc[aktualny_stan][1];
        }
        else if (wrzucona_moneta==5)
        {
            tablica_wykonanych_przejsc[iterator++]=aktualny_stan;
            aktualny_stan = tablica_przejsc[aktualny_stan][2];
        }
        else
        {
            printf("blad wrzucanych monet!");
            break;
        }
        printf("aktualna ilosc kredytow: %d\n", aktualny_stan);
        
    }
    if (aktualny_stan==20)
    {
        tablica_wykonanych_przejsc[iterator++]=aktualny_stan;
        printf("wydaje bilet i %dzl reszty\n", 0);
    }
    else if (aktualny_stan==21)
    {
        tablica_wykonanych_przejsc[iterator++]=aktualny_stan;
        printf("wydaje bilet i %dzl reszty\n", 1);
    }
    else if (aktualny_stan==22)
    {
        tablica_wykonanych_przejsc[iterator++]=aktualny_stan;
        printf("wydaje bilet i %dzl reszty\n", 2);
    }else if (aktualny_stan==23)
    {
        tablica_wykonanych_przejsc[iterator++]=aktualny_stan;
        printf("wydaje bilet i %dzl reszty\n", 3);
    }else if (aktualny_stan==24)
    {
        tablica_wykonanych_przejsc[iterator++]=aktualny_stan;
        printf("wydaje bilet i %dzl reszty\n", 4);
    }
    else{
        printf("automat zakonczyl w blednym stanie\n");
        return 1;
    }
    for (int i = 0; i<iterator-1; i++)
    {
        printf("q%d -> ", tablica_wykonanych_przejsc[i]);
    }
    printf("q%d\n", tablica_wykonanych_przejsc[iterator-1]);
    
    return 0;
}
