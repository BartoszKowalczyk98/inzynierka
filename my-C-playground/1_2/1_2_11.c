#include<stdio.h>
#include<math.h>
int main(int argc, char const *argv[])
{
    printf("Podaj liczbe nieujemna i wymierna: ");
    float liczba;
    scanf("%f",&liczba);
   
    printf("podana liczba w zaokragleniu do 2 miejsc po przecinku to %.2f \n", liczba);
    return 0;
}
