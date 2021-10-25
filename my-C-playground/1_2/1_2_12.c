#include<stdio.h>
#include<math.h>
int main(int argc, char const *argv[])
{
    printf("Podaj liczbe nieujemna i wymierna: ");
    float liczba;
    scanf("%f",&liczba);
    printf("wartosc podanej liczby w notacji naukowej to %e \n", liczba);
    return 0;
}
