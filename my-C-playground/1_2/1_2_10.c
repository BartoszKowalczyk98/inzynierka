#include<stdio.h>
#include<math.h>
int main(int argc, char const *argv[])
{
    printf("Podaj liczbe nieujemna i wymierna: ");
    float liczba;
    scanf("%f",&liczba);
    liczba = fabs(liczba);
    printf("wartosc bezwzgledna z podanej liczby to %f \n", liczba);
    return 0;
}
