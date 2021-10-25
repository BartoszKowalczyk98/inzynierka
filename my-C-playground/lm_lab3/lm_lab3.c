#include<stdio.h>
#include <string.h>
#include <stdlib.h>


int main(int argc, char const *argv[])
{
    char tablica_przejsc[4][3][10] = {
        {{"-,1,L"},{"-,1,L"},{"-,3,-"}},//q0 stan
        {{"-,3,-"},{"-,3,-"},{"1,2,-"}},//q1
        {{"-,-,-"},{"-,-,-"},{"-,-,-"}},//q2 stan akceptujacy
        {{"-,-,-"},{"-,-,-"},{"-,-,-"}} //q3 stan bledu
    };
    //za znak pusty theta odpowiada litera "t" 
    printf("podaj liczbe binarna jednocyfrowa (\"1\" lub \"0\")do zwiekszenia o 2: ");
    char liczba[2];
    scanf("%s", &liczba);
    if(strlen(liczba)>1){
        printf("blad wprowadzonej liczby binarnej liczba nie jest jednocyfrowa!\n");
        return -1;
    }
    //potrzebne zeby dokleic fragment ze znakiem pustym aby uzytkownik wpisywał tylko jednocyfrową liczbę binarną
    char tasma[20] = "t";
    strcat(tasma,liczba);

    //skoro zaczynamy analizę od prawej strony to ustawiam index na ostatni znak
    int indeks_na_ktory_wskazuje_glowica = strlen(tasma)-1;
    // rozpoczynamy dzialanie od stanu  q0
    int aktualny_stan=0;
    do
    {

        printf("aktualny stan  %d  ||| aktualnie analizowana litera %c |||",aktualny_stan,tasma[indeks_na_ktory_wskazuje_glowica]);
        if(aktualny_stan==3)
        {
            printf("\nautomat znalazl sie w stanie q4 czyli w stanie bledu!\n");
            return -1;
        }

        // funkcja przejścia
        if(tasma[indeks_na_ktory_wskazuje_glowica]=='0'){
            if(tablica_przejsc[aktualny_stan][0][0]!='-'){
                tasma[indeks_na_ktory_wskazuje_glowica]=tablica_przejsc[aktualny_stan][0][0];
                printf(" maszyna wpisuje znak %c |||", tasma[indeks_na_ktory_wskazuje_glowica]);
            }
            else
            {
                printf(" maszyna nie wpisuje znaku |||");
            }
            if(tablica_przejsc[aktualny_stan][0][4]!='-'){
                indeks_na_ktory_wskazuje_glowica--;
            }
            if(tablica_przejsc[aktualny_stan][0][2]!='-'){
                aktualny_stan = (int)tablica_przejsc[aktualny_stan][0][2]-48;
                printf(" maszyna przechodzi do stanu %d |||", aktualny_stan);
            }
            else
            {
                printf(" maszyna zostaje w tym samym stanie |||");
            }
        }
        else if (tasma[indeks_na_ktory_wskazuje_glowica]=='1')
        {
            if(tablica_przejsc[aktualny_stan][1][0]!='-'){
                tasma[indeks_na_ktory_wskazuje_glowica]=tablica_przejsc[aktualny_stan][1][0];
                printf(" maszyna wpisuje znak %c |||", tasma[indeks_na_ktory_wskazuje_glowica]);
            }
            else
            {
                printf(" maszyna nie wpisuje znaku |||");
            }
            if(tablica_przejsc[aktualny_stan][1][4]!='-'){
                indeks_na_ktory_wskazuje_glowica--;
            }
            if(tablica_przejsc[aktualny_stan][1][2]!='-'){
                aktualny_stan = (int)tablica_przejsc[aktualny_stan][1][2]-48;
                printf(" maszyna przechodzi do stanu %d |||", aktualny_stan);
            }
            else
            {
                printf(" maszyna zostaje w tym samym stanie |||");
            }
            
            
        }
        //t - theta znak pusty 
        else if(tasma[indeks_na_ktory_wskazuje_glowica]=='t')
        {
            
            if(tablica_przejsc[aktualny_stan][2][0]!='-'){
                tasma[indeks_na_ktory_wskazuje_glowica]=tablica_przejsc[aktualny_stan][2][0];
                printf(" maszyna wpisuje znak %c |||", tasma[indeks_na_ktory_wskazuje_glowica]);
            }
            else
            {
                printf(" maszyna nie wpisuje znaku |||");
            }
            if(tablica_przejsc[aktualny_stan][2][4]!='-'){
                indeks_na_ktory_wskazuje_glowica--;
            }
            if(tablica_przejsc[aktualny_stan][2][2]!='-'){
                aktualny_stan = (int)tablica_przejsc[aktualny_stan][2][2]-48;
                printf(" maszyna przechodzi do stanu %d |||", aktualny_stan);
            }
            else
            {
                printf(" maszyna zostaje w tym samym stanie |||");
            }
        }
        // jezeli pojawi sie na wejsciu inny znak niz obslugiwany przez maszyne
        else
        {
            if (indeks_na_ktory_wskazuje_glowica == -1)
            {
                printf("blad wprowadzonej liczby");
                return -1;
            }
            printf(" automat napotkal nierozpoznany znak ");
            return -1;                        
        }
        printf("\n");
        
    }while(aktualny_stan !=2 );
    printf("maszyna zakonczyla dzialanie w stanie %d a ciag wyjsciowy to:\n%s\n",aktualny_stan,tasma);

}
