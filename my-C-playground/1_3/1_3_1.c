#include <stdio.h>
int main(int argc, char const *argv[])
{
    printf("Podaj liczbe calkowita: ");
    int n;
    scanf("%d", &n);
    if (n < 0)
    {
        n *= -1;
    }
    printf("Wartosc bezwzgledna z podanej wartosci to: %d\n", n);
    return 0;
}
